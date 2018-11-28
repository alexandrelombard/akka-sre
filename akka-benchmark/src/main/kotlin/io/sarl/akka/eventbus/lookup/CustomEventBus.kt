package io.sarl.akka.eventbus.lookup

import akka.actor.ActorRef
import akka.event.japi.LookupEventBus

class CustomEventBus : LookupEventBus<Message, ActorRef, String>() {

    override fun mapSize(): Int {
        return 128
    }

    override fun compareSubscribers(a: ActorRef, b: ActorRef): Int {
        return a.compareTo(b)
    }

    override fun classify(event: Message): String {
        return event.channel
    }

    override fun publish(event: Message, subscriber: ActorRef) {
        subscriber.tell(event, ActorRef.noSender())
    }
}
