package io.sarl.akka;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class AkkaAgent extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().build();
    }
}
