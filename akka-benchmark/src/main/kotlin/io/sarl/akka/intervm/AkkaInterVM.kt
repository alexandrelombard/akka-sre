package io.sarl.akka.intervm

import akka.actor.ActorRef
import akka.actor.ActorSystem
import io.sarl.akka.sample.Greeter
import io.sarl.akka.sample.Printer

import java.io.IOException

object AkkaInterVM {
    @JvmStatic
    fun main(args: Array<String>) {
        val system = ActorSystem.create("intervm-akka")
        try {
            //#create-actors
            val printerActor = system.actorOf(Printer.props(), "intervm-akka-1")
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
