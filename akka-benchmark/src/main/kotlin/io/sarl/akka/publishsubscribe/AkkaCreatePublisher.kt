package io.sarl.akka.publishsubscribe

import akka.actor.ActorSystem
import akka.actor.Props
import com.typesafe.config.ConfigFactory

object AkkaCreatePublisher {

    @JvmStatic
    fun main(args: Array<String>) {
        val config = ConfigFactory.parseResources(javaClass, "application_create_publisher.conf")
        val system = ActorSystem.create("publish-subscribe-akka", config)
        val publisher = system.actorOf(Props.create(Publisher::class.java), "publisher")

        publisher.tell("hello", null)
    }
}