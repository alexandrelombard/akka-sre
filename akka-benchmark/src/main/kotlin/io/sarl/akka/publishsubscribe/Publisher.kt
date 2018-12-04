package io.sarl.akka.publishsubscribe

import akka.actor.AbstractActor
import akka.cluster.pubsub.DistributedPubSubMediator
import akka.actor.Nobody.tell
import akka.cluster.pubsub.DistributedPubSub



class Publisher : AbstractActor() {
    // activate the extension
    var mediator = DistributedPubSub.get(context.system()).mediator()

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
                .match(String::class.java) { `in` ->
                    val out = `in`.toUpperCase()
                    mediator.tell(DistributedPubSubMediator.Publish("content", out),
                            self)
                }
                .build()
    }
}