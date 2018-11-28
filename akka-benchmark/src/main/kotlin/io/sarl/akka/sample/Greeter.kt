package io.sarl.akka.sample

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.Props

//#greeter-messages
class Greeter(
        //#greeter-messages

        private val message: String, private val printerActor: ActorRef) : AbstractActor() {
    private var greeting = ""

    //#greeter-messages
    class WhoToGreet(val who: String)

    class Greet

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
                .match(WhoToGreet::class.java) { wtg -> this.greeting = message + ", " + wtg.who }
                .match(Greet::class.java) { x ->
                    //#greeter-send-message
                    printerActor.tell(Printer.Greeting(greeting), self)
                    //#greeter-send-message
                }
                .build()
    }

    companion object {
        //#greeter-messages
        fun props(message: String, printerActor: ActorRef): Props {
            return Props.create(Greeter::class.java) { Greeter(message, printerActor) }
        }
    }
    //#greeter-messages
}
//#greeter-messages
