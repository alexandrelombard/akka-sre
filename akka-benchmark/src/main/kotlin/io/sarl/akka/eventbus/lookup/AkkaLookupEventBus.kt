package io.sarl.akka.eventbus.lookup

import akka.actor.ActorRef
import akka.actor.ActorSystem

import java.io.IOException

object AkkaLookupEventBus {
    @JvmStatic
    fun main(args: Array<String>) {
        val system = ActorSystem.create("event-bus-akka")
        try {
            val customEventBus = CustomEventBus()
            val publisher = system.actorOf(PublisherActor.props(customEventBus))
            val subscriber1 = system.actorOf(SubscriberActor.props())
            val subscriber2 = system.actorOf(SubscriberActor.props())

            customEventBus.subscribe(subscriber1, "default-channel")
            customEventBus.subscribe(subscriber2, "default-channel")

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
