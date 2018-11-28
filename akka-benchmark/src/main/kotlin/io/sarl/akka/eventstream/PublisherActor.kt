package io.sarl.akka.eventstream

import akka.actor.AbstractActor
import akka.actor.Props
import akka.event.Logging
import akka.event.LoggingAdapter

class PublisherActor : AbstractActor() {


    private val log = Logging.getLogger(context.system, this)

    class TriggerEmit//

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
                .match(TriggerEmit::class.java) { event -> context.system.eventStream().publish(Message("This is a message")) }
                .build()
    }

    companion object {
        fun props(): Props {
            return Props.create(PublisherActor::class.java) { PublisherActor() }
        }
    }
}//
