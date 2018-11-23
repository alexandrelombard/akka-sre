package io.sarl.akka.eventbus.lookup;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PublisherActor extends AbstractActor {
    static public Props props(CustomEventBus eventBus) {
        return Props.create(PublisherActor.class, () -> new PublisherActor(eventBus));
    }

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private final CustomEventBus eventBus;

    public static class TriggerEmit {
        //
    }

    public PublisherActor(CustomEventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(TriggerEmit.class, event -> {
                    log.info("Received TriggerEmit event: PublisherActor will publish a message...");
                    eventBus.publish(new Message("default-channel", "This is a message"));
                })
                .build();
    }
}
