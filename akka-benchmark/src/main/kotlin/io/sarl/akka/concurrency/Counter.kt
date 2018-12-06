package io.sarl.akka.concurrency

import akka.actor.AbstractActor
import akka.japi.pf.ReceiveBuilder

class Counter : AbstractActor() {

    var i = 0

    override fun createReceive(): Receive {
        return ReceiveBuilder()
                .match(Add::class.java) { println(++i) }
                .match(Sub::class.java) { println(--i) }
                .build()
    }

    class Add
    class Sub

}