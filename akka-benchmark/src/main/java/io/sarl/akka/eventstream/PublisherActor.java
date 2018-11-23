package io.sarl.akka.eventstream;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PublisherActor extends AbstractActor {
    static public Props props() {
        return Props.create(PublisherActor.class, () -> new PublisherActor());
    }


    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static class TriggerEmit {
        //
    }

    public PublisherActor() {
        //
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(TriggerEmit.class, event -> {
                    getContext().getSystem().eventStream().publish(new Message("This is a message"));
                })
                .build();
    }
}
