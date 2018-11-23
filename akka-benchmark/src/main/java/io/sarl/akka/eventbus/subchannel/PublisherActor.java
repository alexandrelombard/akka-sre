package io.sarl.akka.eventbus.subchannel;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PublisherActor extends AbstractActor {
    static public Props props(SubchannelEventBusImpl eventBus) {
        return Props.create(PublisherActor.class, () -> new PublisherActor(eventBus));
    }

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private final SubchannelEventBusImpl eventBus;

    public static class TriggerEmit {
        //
    }

    public PublisherActor(SubchannelEventBusImpl eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(TriggerEmit.class, event -> {
                    log.info("Received TriggerEmit event: PublisherActor will publish a message...");
                    eventBus.publish(new Message("default-channel-2", "This is a message"));
                })
                .build();
    }
}
