package io.sarl.akka.serializer.kryo

import akka.actor.AbstractActor
import akka.japi.pf.ReceiveBuilder
import java.io.Serializable

class ReceiverActor : AbstractActor() {
    override fun createReceive(): Receive {
        return ReceiveBuilder()
                .match(EventA::class.java) { println(it.message) }
                .match(EventB::class.java) { println(it.message) }
                .match(EventC::class.java) { println(it.lambda.invoke()) }
                .build()
    }

    class EventA(val message: String)
    class EventB(val message: String) : Serializable
    class EventC(val lambda: () -> String) : Serializable

}