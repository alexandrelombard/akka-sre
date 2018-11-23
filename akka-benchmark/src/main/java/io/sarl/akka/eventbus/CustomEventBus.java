package io.sarl.akka.eventbus;

import akka.actor.ActorRef;
import akka.event.japi.LookupEventBus;

public class CustomEventBus extends LookupEventBus<Message, ActorRef, String> {

    @Override
    public int mapSize() {
        return 128;
    }

    @Override
    public int compareSubscribers(ActorRef a, ActorRef b) {
        return a.compareTo(b);
    }

    @Override
    public String classify(Message event) {
        return event.getChannel();
    }

    @Override
    public void publish(Message event, ActorRef subscriber) {
        subscriber.tell(event, ActorRef.noSender());
    }
}
