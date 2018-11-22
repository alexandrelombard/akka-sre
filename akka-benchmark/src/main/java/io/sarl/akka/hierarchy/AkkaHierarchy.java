package io.sarl.akka.hierarchy;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import io.sarl.akka.sample.Greeter;
import io.sarl.akka.sample.Greeter.Greet;
import io.sarl.akka.sample.Greeter.WhoToGreet;
import io.sarl.akka.sample.Printer;

import java.io.IOException;

public class AkkaHierarchy {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("hierarchy-akka");
        try {
            final ActorRef parent = system.actorOf(Parent.props());

            parent.tell(new Parent.Start(), ActorRef.noSender());

            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException ignored) {
        } finally {
            system.terminate();
        }
    }
}
