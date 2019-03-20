package io.sarl.akka.bic

import io.sarl.akka.AkkaAgent
import io.sarl.core.Time
import io.sarl.lang.core.Agent
import io.sarl.lang.core.Skill
import java.util.concurrent.TimeUnit

class TimeSkill(val akkaAgent: AkkaAgent, owner: Agent) : Skill(owner), Time {
    override fun toOSTime(timeValue: Double): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fromOSDuration(timeDuration: Double): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getOSTimeFactor(): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fromOSTime(timeValue: Double): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTime(timeUnit: TimeUnit?): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toOSDuration(timeDuration: Double): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}