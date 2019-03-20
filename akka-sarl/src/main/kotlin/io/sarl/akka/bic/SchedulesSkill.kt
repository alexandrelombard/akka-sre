package io.sarl.akka.bic

import io.sarl.akka.AkkaAgent
import io.sarl.core.AgentTask
import io.sarl.core.Schedules
import io.sarl.lang.core.Agent
import io.sarl.lang.core.Skill
import io.sarl.lang.util.SynchronizedSet
import org.eclipse.xtext.xbase.lib.Procedures

class SchedulesSkill(val akkaAgent: AkkaAgent, owner: Agent) : Skill(owner), Schedules {
    override fun every(task: AgentTask?, period: Long, procedure: Procedures.Procedure1<in Agent>?): AgentTask {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun task(name: String?): AgentTask {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun at(task: AgentTask?, time: Long, procedure: Procedures.Procedure1<in Agent>?): AgentTask {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancel(task: AgentTask?, mayInterruptIfRunning: Boolean): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun execute(task: AgentTask?, procedure: Procedures.Procedure1<in Agent>?): AgentTask {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun atFixedDelay(task: AgentTask?, delay: Long, procedure: Procedures.Procedure1<in Agent>?): AgentTask {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setName(task: AgentTask?, name: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCanceled(task: AgentTask?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun `in`(task: AgentTask?, delay: Long, procedure: Procedures.Procedure1<in Agent>?): AgentTask {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getActiveTasks(): SynchronizedSet<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}