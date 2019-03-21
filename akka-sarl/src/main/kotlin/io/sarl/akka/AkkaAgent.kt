package io.sarl.akka

import akka.actor.AbstractActor
import akka.actor.Props
import akka.cluster.pubsub.DistributedPubSubMediator
import akka.japi.pf.ReceiveBuilder
import io.sarl.akka.bic.DefaultContextInteractionsSkill
import io.sarl.akka.bic.LoggingSkill
import io.sarl.akka.space.AkkaAgentContext
import io.sarl.core.DefaultContextInteractions
import io.sarl.core.Initialize
import io.sarl.core.Logging
import io.sarl.eventdispatching.BehaviorGuardEvaluator
import io.sarl.eventdispatching.BehaviorGuardEvaluatorRegistry
import io.sarl.lang.core.*

import java.lang.reflect.Constructor
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.ArrayList
import java.util.UUID

class AkkaAgent(private val agentClass: Class<out Agent>) : AbstractActor(), EventListener {

    private val evaluatorRegistry = BehaviorGuardEvaluatorRegistry()

    private var sarlAgent: Agent
    private val loggingSkill: LoggingSkill
    private val spaceSkill: DefaultContextInteractionsSkill

    val agentContext: AkkaAgentContext

    init {
        agentContext = AkkaAgentContext(this)

        val cons = this.agentClass.getConstructor(UUID::class.java, UUID::class.java)
        this.sarlAgent = cons.newInstance(null, agentContext.id)

        // Initialize all attributes
        this.evaluatorRegistry.register(sarlAgent)
        this.loggingSkill = LoggingSkill(sarlAgent)
        this.spaceSkill = DefaultContextInteractionsSkill(this, sarlAgent)

        try {
            val method = Agent::class.java.getDeclaredMethod("setSkill", Class::class.java, Skill::class.java) //$NON-NLS-1$
            val isAcc = method.isAccessible
            try {
                method.isAccessible = true
                method.invoke(sarlAgent, Logging::class.java, this.loggingSkill)    // Register the logging skill
                method.invoke(sarlAgent, DefaultContextInteractions::class.java, this.spaceSkill)   // Register the space skill
            } finally {
                method.isAccessible = isAcc
            }
        } catch (e: Exception) {
            throw Error(e)
        }

        // Fire initialize event
        val initializeEvent = Initialize(null)
        receiveEvent(initializeEvent)
    }

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
                .match(AgentGetIdRequest::class.java) {
                    sender.tell(id, self)
                }.build()
    }

    override fun receiveEvent(event: Event) {
        if (event.source == null) {
            event.source = agentContext.defaultSpace.getAddress(agentContext.id);
        }

        fireEvent(event)
    }

    override fun getID(): UUID {
        return agentContext.id
    }

    private fun fireEvent(event: Event) {
        val evaluators = this.evaluatorRegistry.getBehaviorGuardEvaluators(event)
        val handlers = ArrayList<Runnable>()
        for (evaluator in evaluators) {
            try {
                evaluator.evaluateGuard(event, handlers)
            } catch (e: Throwable) {
                this.loggingSkill.error(e.localizedMessage)
            }

        }
        for (handler in handlers) {
            try {
                handler.run()
            } catch (e: Throwable) {
                this.loggingSkill.error(e.localizedMessage)
            }

        }
    }

    companion object {
        fun props(agentClass: Class<out Agent>): Props {
            return Props.create(AkkaAgent::class.java) { AkkaAgent(agentClass) }
        }
    }

    class AgentGetIdRequest
}
