package io.sarl.akka.sample

import java.io.IOException

import akka.actor.ActorRef
import akka.actor.ActorSystem

object AkkaHelloWorld {
    @JvmStatic
    fun main(args: Array<String>) {
        val system = ActorSystem.create("hello-akka")
        try {
            //#create-actors
            val printerActor = system.actorOf(Printer.props(), "printerActor")
            val howdyGreeter = system.actorOf(Greeter.props("Howdy", printerActor), "howdyGreeter")
            val helloGreeter = system.actorOf(Greeter.props("Hello", printerActor), "helloGreeter")
            val goodDayGreeter = system.actorOf(Greeter.props("Good day", printerActor), "goodDayGreeter")
            //#create-actors

            //#main-send-messages
            howdyGreeter.tell(Greeter.WhoToGreet("Akka"), ActorRef.noSender())
            howdyGreeter.tell(Greeter.Greet(), ActorRef.noSender())

            howdyGreeter.tell(Greeter.WhoToGreet("Lightbend"), ActorRef.noSender())
            howdyGreeter.tell(Greeter.Greet(), ActorRef.noSender())

            helloGreeter.tell(Greeter.WhoToGreet("Java"), ActorRef.noSender())
            helloGreeter.tell(Greeter.Greet(), ActorRef.noSender())

            goodDayGreeter.tell(Greeter.WhoToGreet("Play"), ActorRef.noSender())
            goodDayGreeter.tell(Greeter.Greet(), ActorRef.noSender())
            //#main-send-messages

            println(">>> Press ENTER to exit <<<")
            System.`in`.read()
        } catch (ignored: IOException) {
        } finally {
            system.terminate()
        }
    }
}
