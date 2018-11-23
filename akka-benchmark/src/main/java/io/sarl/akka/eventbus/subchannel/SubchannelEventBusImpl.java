package io.sarl.akka.eventbus.subchannel;

import akka.actor.ActorRef;
import akka.event.japi.SubchannelEventBus;
import akka.util.Subclassification;

public class SubchannelEventBusImpl extends SubchannelEventBus<Message, ActorRef, String> {

    @Override public Subclassification<String> subclassification() {
        return new StartsWithSubclassification();
    }

    @Override public String classify(Message event) {
        return event.getChannel();
    }

    @Override public void publish(Message event, ActorRef subscriber) {
        subscriber.tell(event, ActorRef.noSender());
    }

    public class StartsWithSubclassification implements Subclassification<String> {
        @Override public boolean isEqual(String x, String y) {
            return x.equals(y);
        }

        @Override public boolean isSubclass(String x, String y) {
            return x.startsWith(y);
        }
    }
}

