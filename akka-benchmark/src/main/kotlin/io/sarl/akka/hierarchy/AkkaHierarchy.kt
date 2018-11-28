package io.sarl.akka.hierarchy

import akka.actor.ActorRef
import akka.actor.ActorSystem

import java.io.IOException

object AkkaHierarchy {
    @JvmStatic
    fun main(args: Array<String>) {
        val system = ActorSystem.create("hierarchy-akka")
        try {
            val parent = system.actorOf(Parent.props())

            parent.tell(Parent.Start(), ActorRef.noSender())

            println(">>> Press ENTER to exit <<<")
            System.`in`.read()
        } catch (ignored: IOException) {
        } finally {
            system.terminate()
        }
    }
}
