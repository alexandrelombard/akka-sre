package io.sarl.akka.bic

import io.sarl.akka.AkkaAgent
import io.sarl.core.Behaviors
import io.sarl.lang.core.*
import io.sarl.lang.util.SynchronizedIterable
import org.eclipse.xtext.xbase.lib.Functions

class BehaviorsSkill(val akkaAgent: AkkaAgent, owner: Agent) : Skill(owner), Behaviors {
    override fun unregisterBehavior(attitude: Behavior?): Behavior {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun wake(event: Event?, scope: Scope<Address>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun asEventListener(): EventListener {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasRegisteredBehavior(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRegisteredBehaviors(): SynchronizedIterable<Behavior> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerBehavior(attitude: Behavior?, filter: Functions.Function1<in Event, out Boolean>?, vararg initializationParameters: Any?): Behavior {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}