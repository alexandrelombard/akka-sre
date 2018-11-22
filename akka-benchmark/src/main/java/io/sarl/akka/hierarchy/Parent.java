package io.sarl.akka.hierarchy;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import io.sarl.akka.sample.Printer.Greeting;

import java.util.ArrayList;
import java.util.List;

public class Parent extends AbstractActor {
    static public Props props() {
        return Props.create(Parent.class, Parent::new);
    }

    static public class Start {
        //
    }

    private static final int CHILDREN_COUNT = 10;

    private List<ActorRef> children = new ArrayList<>();

    public Parent() {
        for(int idx = 0; idx < CHILDREN_COUNT; idx++) {
            children.add(getContext().actorOf(Props.create(Child.class)));
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Start.class, message -> {
                    for(ActorRef child : children) {
                        child.tell(new Child.PrintSomething(child.path().toString()), getSelf());
                    }
                })
                .build();
    }
}
