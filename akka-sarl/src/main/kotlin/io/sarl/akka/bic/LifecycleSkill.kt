package io.sarl.akka.bic

import io.sarl.akka.AkkaAgent
import io.sarl.core.Lifecycle
import io.sarl.lang.core.Agent
import io.sarl.lang.core.AgentContext
import io.sarl.lang.core.Skill
import java.util.*

class LifecycleSkill(val akkaAgent: AkkaAgent, owner: Agent) : Skill(owner), Lifecycle {
    override fun spawn(agentType: Class<out Agent>?, vararg params: Any?): UUID {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun spawn(nbAgents: Int, agentType: Class<out Agent>?, vararg params: Any?): MutableIterable<UUID> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun killMe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun spawnInContextWithID(agentClass: Class<out Agent>?, agentID: UUID?, context: AgentContext?, vararg params: Any?): UUID {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun spawnInContext(agentClass: Class<out Agent>?, context: AgentContext?, vararg params: Any?): UUID {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun spawnInContext(nbAgents: Int, agentClass: Class<out Agent>?, context: AgentContext?, vararg params: Any?): MutableIterable<UUID> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}