package io.sarl.akka.bic

import io.sarl.akka.AkkaAgent
import io.sarl.core.Behaviors
import io.sarl.core.ExternalContextAccess
import io.sarl.lang.core.*
import io.sarl.lang.util.SynchronizedIterable
import java.util.*

class ExternalContextAccessSkill(val akkaAgent: AkkaAgent, owner: Agent) : Skill(owner), ExternalContextAccess {
    override fun getUniverseContext(): AgentContext {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun leave(contextID: UUID?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isInSpace(event: Event?, space: Space?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isInSpace(event: Event?, spaceID: SpaceID?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isInSpace(event: Event?, spaceID: UUID?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllContexts(): SynchronizedIterable<AgentContext> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getContext(contextID: UUID?): AgentContext {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun join(contextID: UUID?, expectedDefaultSpaceID: UUID?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun emit(space: EventSpace?, event: Event?, scope: Scope<Address>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}