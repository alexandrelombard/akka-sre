package io.sarl.akka.sample

import akka.actor.AbstractActor
import akka.actor.Props
import akka.event.Logging
import akka.event.LoggingAdapter
import akka.japi.Creator

//#printer-messages
class Printer : AbstractActor() {
    //#printer-messages

    private val log = Logging.getLogger(context.system, this)

    //#printer-messages
    class Greeting(val message: String)

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
                .match(Greeting::class.java) { greeting -> log.info(greeting.message) }
                .build()
    }

    companion object {
        //#printer-messages
        fun props(): Props {
            return Props.create<Printer>(Printer::class.java) { Printer() }
        }
    }
    //#printer-messages
}
//#printer-messages
