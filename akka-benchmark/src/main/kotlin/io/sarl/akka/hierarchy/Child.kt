package io.sarl.akka.hierarchy

import akka.actor.AbstractActor
import akka.actor.Props
import akka.event.Logging
import akka.event.LoggingAdapter

class Child : AbstractActor() {

    private val log = Logging.getLogger(context.system, this)

    class PrintSomething(val something: String)

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
                .match(PrintSomething::class.java) { event -> log.info(event.something) }
                .build()
    }

    companion object {
        fun props(): Props {
            return Props.create(Child::class.java) { Child() }
        }
    }
}//
