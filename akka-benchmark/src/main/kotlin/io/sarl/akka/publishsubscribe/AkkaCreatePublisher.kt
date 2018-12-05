package io.sarl.akka.publishsubscribe

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import com.typesafe.config.ConfigFactory

object AkkaCreatePublisher {

    @JvmStatic
    fun main(args: Array<String>) {
        val config = ConfigFactory.parseResources(javaClass, "application_create_publisher.conf")
        val system = ActorSystem.create("publish-subscribe-akka", config)

        // Wait input (should wait for the cluster to be formed)
        println(">>> Press ENTER to create actor <<<")
        System.`in`.read()
        println("Creating Publisher actor...")

        val publisher = system.actorOf(Props.create(Publisher::class.java), "publisher")

        println(">>> Press ENTER to say 'hello' <<<")
        System.`in`.read()
        println("Sending 'hello'...")

        publisher.tell("hello", ActorRef.noSender())

        println(">>> Press ENTER to exit <<<")
        System.`in`.read()

        system.terminate()
    }
}