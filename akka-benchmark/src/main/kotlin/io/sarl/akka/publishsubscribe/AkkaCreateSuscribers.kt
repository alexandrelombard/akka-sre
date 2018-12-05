package io.sarl.akka.publishsubscribe

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.cluster.pubsub.DistributedPubSub
import akka.cluster.pubsub.DistributedPubSubMediator
import akka.pattern.PatternsCS.ask
import com.typesafe.config.ConfigFactory
import java.io.IOException

object AkkaCreateSuscribers {
    @JvmStatic
    fun main(args: Array<String>) {
        val config = ConfigFactory.parseResources(javaClass, "application_create_subscriber.conf")
        val system = ActorSystem.create("publish-subscribe-akka", config)
        try {
            //#create-actors
            val subscriber1 = system.actorOf(Props.create(Subscriber::class.java), "subscriber1")
            system.actorOf(Props.create(Subscriber::class.java), "subscriber2")
            system.actorOf(Props.create(Subscriber::class.java), "subscriber3")
            val mediatorObserver = system.actorOf(Props.create(MediatorObserver::class.java), "mediator-observer")
            //#create-actors

            // Ask the mediator for content topics
            mediatorObserver.tell("getTopics", ActorRef.noSender())

            println(">>> Press ENTER to send message to subscriber 1 <<<")
            System.`in`.read()

            subscriber1.tell(Subscriber.TellAll("Hello world"), ActorRef.noSender())

            println(">>> Press ENTER to exit <<<")
            System.`in`.read()
        } catch (ignored: IOException) {
        } finally {
            system.terminate()
        }
    }
}
