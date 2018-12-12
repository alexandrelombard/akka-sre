package io.sarl.akka

import akka.actor.AbstractActor
import akka.actor.Props
import akka.japi.pf.ReceiveBuilder
import io.sarl.akka.bic.LoggingSkill
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

    var sarlAgent: Agent
        private set

    private val loggingSkill: LoggingSkill

//    private DefaultContextInteractionsSkill spaceSkill;


    init {
        val cons = this.agentClass.getConstructor(UUID::class.java, UUID::class.java)
        this.sarlAgent = cons.newInstance(null, this.id)

        // Initialize all attributes
        this.evaluatorRegistry.register(sarlAgent)
        this.loggingSkill = LoggingSkill(sarlAgent)

        try {
            val method = Agent::class.java.getDeclaredMethod("setSkill", Class::class.java, Skill::class.java) //$NON-NLS-1$
            val isAcc = method.isAccessible
            try {
                method.isAccessible = true
                method.invoke(sarlAgent, Logging::class.java, this.loggingSkill)    // Register the logging skill
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
        return ReceiveBuilder.create().build()
    }

    override fun receiveEvent(event: Event) {
        //        if (event.getSource() == null) {
        //            event.setSource(getDefaultSpace().getAddress(this.sarlAgent.getID()));
        //        }

        fireEvent(event)
    }

    override fun getID(): UUID? {
        return null
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
}
