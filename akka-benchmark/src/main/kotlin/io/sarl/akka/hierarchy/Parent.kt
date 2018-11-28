package io.sarl.akka.hierarchy

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.Props
import akka.japi.Creator

import java.util.ArrayList

class Parent : AbstractActor() {

    private val children = ArrayList<ActorRef>()

    class Start//

    init {
        for (idx in 0 until CHILDREN_COUNT) {
            children.add(context.actorOf(Props.create(Child::class.java)))
        }
    }

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
                .match(Start::class.java) { message ->
                    for (child in children) {
                        child.tell(Child.PrintSomething(child.path().toString()), self)
                    }
                }
                .build()
    }

    companion object {
        fun props(): Props {
            return Props.create<Parent>(Parent::class.java) { Parent() }
        }

        private val CHILDREN_COUNT = 10
    }
}
