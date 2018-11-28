package io.sarl.akka.eventbus.subchannel

import akka.actor.AbstractActor
import akka.actor.Props
import akka.event.Logging
import akka.event.LoggingAdapter

class PublisherActor(private val eventBus: SubchannelEventBusImpl) : AbstractActor() {

    private val log = Logging.getLogger(context.system, this)

    class TriggerEmit//

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
                .match(TriggerEmit::class.java) { event ->
                    log.info("Received TriggerEmit event: PublisherActor will publish a message...")
                    eventBus.publish(Message("default-channel-2", "This is a message"))
                }
                .build()
    }

    companion object {
        fun props(eventBus: SubchannelEventBusImpl): Props {
            return Props.create(PublisherActor::class.java) { PublisherActor(eventBus) }
        }
    }
}
