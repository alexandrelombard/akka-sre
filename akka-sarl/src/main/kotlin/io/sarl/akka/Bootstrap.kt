package io.sarl.akka

import io.sarl.bootstrap.SREBootstrap
import io.sarl.lang.core.Agent
import io.sarl.lang.core.AgentContext

import java.util.UUID

class Bootstrap : SREBootstrap {
    override fun startWithoutAgent(): AgentContext? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Throws(Exception::class)
    override fun startAgent(agentCls: Class<out Agent>, vararg params: Any): UUID? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Throws(Exception::class)
    override fun startAgent(nbAgents: Int, agentCls: Class<out Agent>, vararg params: Any): Iterable<UUID>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> getService(p0: Class<T>?): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startAgentWithID(p0: Class<out Agent>?, p1: UUID?, vararg p2: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun shutdown(p0: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBootAgentIdentifier(): UUID? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
