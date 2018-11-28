package io.sarl.akka.eventstream

import akka.actor.ActorRef
import akka.actor.ActorSystem

import java.io.IOException

object AkkaEventStream {
    @JvmStatic
    fun main(args: Array<String>) {
        val system = ActorSystem.create("event-stream-akka")
        try {
            val publisher = system.actorOf(PublisherActor.props())
            val subscriber1 = system.actorOf(SubscriberActor.props())
            val subscriber2 = system.actorOf(SubscriberActor.props())

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
