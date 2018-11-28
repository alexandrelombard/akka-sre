package io.sarl.akka.eventbus.subchannel

import akka.actor.ActorRef
import akka.event.japi.SubchannelEventBus
import akka.util.Subclassification

class SubchannelEventBusImpl : SubchannelEventBus<Message, ActorRef, String>() {

    override fun subclassification(): Subclassification<String> {
        return StartsWithSubclassification()
    }

    override fun classify(event: Message): String {
        return event.channel
    }

    override fun publish(event: Message, subscriber: ActorRef) {
        subscriber.tell(event, ActorRef.noSender())
    }

    inner class StartsWithSubclassification : Subclassification<String> {
        override fun isEqual(x: String, y: String): Boolean {
            return x == y
        }

        override fun isSubclass(x: String, y: String): Boolean {
            return x.startsWith(y)
        }
    }
}

