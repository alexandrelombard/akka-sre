package io.sarl.akka.intervm

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import java.io.IOException

object AkkaCreateActor {
    @JvmStatic
    fun main(args: Array<String>) {
        val config = ConfigFactory.parseResources(javaClass, "application_create_actor.conf")
        val system = ActorSystem.create("intervm-akka", config)
        try {
            //#create-actors
            val customActor = system.actorOf(CustomActor.props(), "intervm-akka-actor")
            //#create-actors

            println(">>> Press ENTER to exit <<<")
            System.`in`.read()
        } catch (ignored: IOException) {
        } finally {
            system.terminate()
        }
    }
}
