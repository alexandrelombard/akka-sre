package io.sarl.akka.eventbus.subchannel;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.IOException;

public class AkkaSubchannelEventBus {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("event-bus-akka");
        try {
            final SubchannelEventBusImpl subchannelEventBusImpl = new SubchannelEventBusImpl();
            final ActorRef publisher = system.actorOf(PublisherActor.props(subchannelEventBusImpl));
            final ActorRef subscriber1 = system.actorOf(SubscriberActor.props(), "subscriber1");
            final ActorRef subscriber2 = system.actorOf(SubscriberActor.props(), "subscriber2");
            final ActorRef subscriber3 = system.actorOf(SubscriberActor.props(), "subscriber3");

            // Note: the publisher will send its message it default-channel-2
            subchannelEventBusImpl.subscribe(subscriber1, "default-channel");
            subchannelEventBusImpl.subscribe(subscriber2, "default-channel");
            subchannelEventBusImpl.subscribe(subscriber3, "default-channel-2");

            // We trigger the emission of the message: the publisher will send a message to all subscribers
            // As there are two subscribers, the message should be written twice
            publisher.tell(new PublisherActor.TriggerEmit(), ActorRef.noSender());

            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException ignored) {
        } finally {
            system.terminate();
        }
    }
}
