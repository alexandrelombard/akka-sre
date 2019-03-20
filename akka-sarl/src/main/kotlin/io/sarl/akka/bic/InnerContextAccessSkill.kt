package io.sarl.akka.bic

import io.sarl.akka.AkkaAgent
import io.sarl.core.Behaviors
import io.sarl.core.ExternalContextAccess
import io.sarl.core.InnerContextAccess
import io.sarl.lang.core.*
import io.sarl.lang.util.SynchronizedIterable
import java.util.*

class InnerContextAccessSkill(val akkaAgent: AkkaAgent, owner: Agent) : Skill(owner), InnerContextAccess {
    override fun isInnerDefaultSpace(space: Space?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isInnerDefaultSpace(spaceID: SpaceID?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isInnerDefaultSpace(spaceID: UUID?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasMemberAgent(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getInnerContext(): AgentContext {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMemberAgentCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isInInnerDefaultSpace(event: Event?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMemberAgents(): SynchronizedIterable<UUID> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}