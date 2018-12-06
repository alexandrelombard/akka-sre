package io.sarl.akka.serializer.kryo

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import com.typesafe.config.ConfigFactory


object AkkaSerializer {
    @JvmStatic
    fun main(args: Array<String>) {
        val config = ConfigFactory.parseResources(javaClass, "application_serializer.conf")
        val system = ActorSystem.create("akka-serializer", config)
        try {
            val receiverRef = system.actorOf(Props.create(ReceiverActor::class.java))

            receiverRef.tell(ReceiverActor.EventA("EventA"), ActorRef.noSender())
            receiverRef.tell(ReceiverActor.EventB("EventB"), ActorRef.noSender())
            val str = "EventC"
            receiverRef.tell(ReceiverActor.EventC { str }, ActorRef.noSender())

            println("Press enter to leave...")

            System.`in`.read();
        } finally {
            system.terminate()
        }
    }
}