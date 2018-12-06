package io.sarl.akka.concurrency

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props

object AkkaCounter {
    @JvmStatic
    fun main(args: Array<String>) {
        val system = ActorSystem.create("akka-counter")
        try {
            val counterRef = system.actorOf(Props.create(Counter::class.java))

            for(i in 0..1000000) {
                counterRef.tell(Counter.Add(), ActorRef.noSender());
                counterRef.tell(Counter.Sub(), ActorRef.noSender());
            }

            println("Press enter to leave...")

            System.`in`.read();
        } finally {
            system.terminate()
        }
    }
}