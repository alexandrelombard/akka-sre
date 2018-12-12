package io.sarl.akka

import io.sarl.bootstrap.SREBootstrap
import io.sarl.lang.core.Agent
import io.sarl.lang.core.AgentContext

import java.util.UUID

class Bootstrap : SREBootstrap {
    override fun startWithoutAgent(): AgentContext? {
        return null
    }

    @Throws(Exception::class)
    override fun startAgent(agentCls: Class<out Agent>, vararg params: Any): UUID? {
        return null
    }

    @Throws(Exception::class)
    override fun startAgent(nbAgents: Int, agentCls: Class<out Agent>, vararg params: Any): Iterable<UUID>? {
        return null
    }

    override fun getBootAgentIdentifier(): UUID? {
        return null
    }
}
