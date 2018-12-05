package io.sarl.akka.publishsubscribe

import akka.actor.AbstractActor
import akka.cluster.pubsub.DistributedPubSub
import akka.cluster.pubsub.DistributedPubSubMediator
import akka.japi.pf.FI
import akka.japi.pf.ReceiveBuilder

class MediatorObserver : AbstractActor() {

    val mediator = DistributedPubSub.get(context.system).mediator()

    override fun createReceive(): Receive {
        return ReceiveBuilder()
                .match(String::class.java, FI.TypedPredicate<String> { it == "getTopics" }, FI.UnitApply {
                    println("Sending getTopicsInstance message to mediator...")
                    mediator.tell(DistributedPubSubMediator.getTopicsInstance(), self)
                })
                .match(DistributedPubSubMediator.CurrentTopics::class.java) {
                    println("Received topics instances...")
                    println(it)
                }
                .build()
    }

}