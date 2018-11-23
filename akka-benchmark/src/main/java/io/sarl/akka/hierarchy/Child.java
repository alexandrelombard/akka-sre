package io.sarl.akka.hierarchy;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Child extends AbstractActor {
    static public Props props() {
        return Props.create(Child.class, () -> new Child());
    }

    static public class PrintSomething {
        public final String something;

        public PrintSomething(String something) {
            this.something = something;
        }
    }

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public Child() {
        //
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PrintSomething.class, event -> {
                    log.info(event.something);
                })
                .build();
    }
}
