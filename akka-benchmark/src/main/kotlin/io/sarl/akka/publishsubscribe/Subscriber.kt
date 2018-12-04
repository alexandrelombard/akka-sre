package io.sarl.akka.publishsubscribe

import akka.actor.AbstractActor
import akka.cluster.pubsub.DistributedPubSub
import akka.cluster.pubsub.DistributedPubSubMediator
import akka.event.Logging
import akka.japi.pf.FI


class Subscriber : AbstractActor() {
    var log = Logging.getLogger(context.system(), this)

    init {
        val mediator = DistributedPubSub.get(context.system()).mediator()
        // subscribe to the topic named "content"
        mediator.tell(DistributedPubSubMediator.Subscribe("content", self), self)
    }

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
                .match(String::class.java) { msg -> log.info("Got: {}", msg) }
                .match(DistributedPubSubMediator.SubscribeAck::class.java) { msg -> log.info("subscribed") }
                .build()
    }
}