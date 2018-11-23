package io.sarl.akka.eventbus.lookup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.IOException;

public class AkkaLookupEventBus {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("event-bus-akka");
        try {
            final CustomEventBus customEventBus = new CustomEventBus();
            final ActorRef publisher = system.actorOf(PublisherActor.props(customEventBus));
            final ActorRef subscriber1 = system.actorOf(SubscriberActor.props());
            final ActorRef subscriber2 = system.actorOf(SubscriberActor.props());

            customEventBus.subscribe(subscriber1, "default-channel");
            customEventBus.subscribe(subscriber2, "default-channel");

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
