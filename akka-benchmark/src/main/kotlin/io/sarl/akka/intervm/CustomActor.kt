package io.sarl.akka.intervm

import akka.actor.AbstractActor
import akka.japi.pf.ReceiveBuilder

class CustomActor : AbstractActor() {
    override fun createReceive(): AbstractActor.Receive {
        return ReceiveBuilder().build()
    }
}
