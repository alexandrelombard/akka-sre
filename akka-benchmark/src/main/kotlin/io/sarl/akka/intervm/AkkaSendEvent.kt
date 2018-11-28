package io.sarl.akka.intervm

import akka.actor.ActorRef
import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

object AkkaSendEvent {

    @JvmStatic
    fun main(args: Array<String>) {
        val config = ConfigFactory.parseResources(javaClass, "application_send_event.conf")
        val system = ActorSystem.create("intervm-akka", config)
        val selection = system.actorSelection("akka.tcp://intervm-akka@127.0.0.1:2552/user/intervm-akka-actor")
        selection.tell(CustomEvent("Hello remote actor"), ActorRef.noSender())
    }
}