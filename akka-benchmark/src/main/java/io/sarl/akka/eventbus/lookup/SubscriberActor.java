package io.sarl.akka.eventbus.lookup;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class SubscriberActor extends AbstractActor {
    static public Props props() {
        return Props.create(SubscriberActor.class, SubscriberActor::new);
    }

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public SubscriberActor() {
        //
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Message.class, message -> {
                    log.info(message.getContent());
                })
                .build();
    }
}
