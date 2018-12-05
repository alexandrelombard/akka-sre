package io.sarl.akka.publishsubscribe

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.cluster.pubsub.DistributedPubSub
import akka.cluster.pubsub.DistributedPubSubMediator


class Publisher : AbstractActor() {
    // activate the extension
    private val mediator: ActorRef = DistributedPubSub.get(context.system()).mediator()

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
                .match(String::class.java) {
                    mediator.tell(DistributedPubSubMediator.Publish("content", it), self)
                }
                .match(DistributedPubSubMediator.SubscribeAck::class.java) {
                    println("Publisher is registered (SubscribeAck)")
                }
                .build()
    }
}