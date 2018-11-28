package io.sarl.akka.eventbus.subchannel

import akka.actor.ActorRef
import akka.actor.ActorSystem

import java.io.IOException

object AkkaSubchannelEventBus {
    @JvmStatic
    fun main(args: Array<String>) {
        val system = ActorSystem.create("event-bus-akka")
        try {
            val subchannelEventBusImpl = SubchannelEventBusImpl()
            val publisher = system.actorOf(PublisherActor.props(subchannelEventBusImpl))
            val subscriber1 = system.actorOf(SubscriberActor.props(), "subscriber1")
            val subscriber2 = system.actorOf(SubscriberActor.props(), "subscriber2")
            val subscriber3 = system.actorOf(SubscriberActor.props(), "subscriber3")

            // Note: the publisher will send its message it default-channel-2
            subchannelEventBusImpl.subscribe(subscriber1, "default-channel")
            subchannelEventBusImpl.subscribe(subscriber2, "default-channel")
            subchannelEventBusImpl.subscribe(subscriber3, "default-channel-2")

            // We trigger the emission of the message: the publisher will send a message to all subscribers
            // As there are two subscribers, the message should be written twice
            publisher.tell(PublisherActor.TriggerEmit(), ActorRef.noSender())

            println(">>> Press ENTER to exit <<<")
            System.`in`.read()
        } catch (ignored: IOException) {
        } finally {
            system.terminate()
        }
    }
}
