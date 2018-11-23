package io.sarl.akka.eventstream;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.IOException;

public class AkkaEventStream {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("event-stream-akka");
        try {
            final ActorRef publisher = system.actorOf(PublisherActor.props());
            final ActorRef subscriber1 = system.actorOf(SubscriberActor.props());
            final ActorRef subscriber2 = system.actorOf(SubscriberActor.props());

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
