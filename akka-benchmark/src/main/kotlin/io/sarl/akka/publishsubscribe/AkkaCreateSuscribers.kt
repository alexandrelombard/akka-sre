package io.sarl.akka.publishsubscribe

import akka.actor.ActorSystem
import akka.actor.Props
import com.typesafe.config.ConfigFactory
import java.io.IOException

object AkkaCreateSuscribers {
    @JvmStatic
    fun main(args: Array<String>) {
        val config = ConfigFactory.parseResources(javaClass, "application_create_subscriber.conf")
        val system = ActorSystem.create("publish-subscribe-akka", config)
        try {
            //#create-actors
            system.actorOf(Props.create(Subscriber::class.java), "subscriber1")
            system.actorOf(Props.create(Subscriber::class.java), "subscriber2")
            system.actorOf(Props.create(Subscriber::class.java), "subscriber3")
            //#create-actors

            println(">>> Press ENTER to exit <<<")
            System.`in`.read()
        } catch (ignored: IOException) {
        } finally {
            system.terminate()
        }
    }
}
