package io.sarl.akka.intervm

import akka.actor.AbstractActor
import akka.actor.Props
import akka.japi.pf.ReceiveBuilder

class CustomActor : AbstractActor() {
    override fun createReceive(): AbstractActor.Receive {
        return ReceiveBuilder()
                .match(CustomEvent::class.java) {
                    println(it.message)
                }.build()
    }

    companion object {
        fun props(): Props {
            return Props.create<CustomActor>(CustomActor::class.java) { CustomActor() }
        }
    }
}
