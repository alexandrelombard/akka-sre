package io.sarl.akka.eventbus.subchannel

import akka.actor.AbstractActor
import akka.actor.Props
import akka.event.Logging
import akka.event.LoggingAdapter
import akka.japi.Creator

class SubscriberActor : AbstractActor() {

    private val log = Logging.getLogger(context.system, this)

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
                .match(Message::class.java) { message -> log.info(message.content) }
                .build()
    }

    companion object {
        fun props(): Props {
            return Props.create<SubscriberActor>(SubscriberActor::class.java) { SubscriberActor() }
        }
    }
}//
