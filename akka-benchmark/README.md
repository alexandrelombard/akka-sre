# Akka Benchmark

This projects contains benchmark and tests using Akka actor model to evaluate the performance and the adequation of the
solution as a runtime environment for the Sarl language.

## Tests

### io.sarl.akka.sample

The hello world of Akka.io. Actors are created and messages are sent to them.

### io.sarl.akka.hierarchy

Testing hierarchies of actor and communication within a hierarchy. A parent actor is created,
it then creates 10 children. When the parent receives the message "Start", it sends the message
"PrintSomething" to all of its children which then print something.

### io.sarl.akka.eventstream

Testing the event stream for peer-to-many communication between actors.

### io.sarl.akka.eventbus

Testing a custom event bus for peer-to-many (but not peer-to-anyone) communication between actors.

### io.sarl.akka.intervm

Testing communication between actors on two VM using network. To start the test _AkkaCreateActor_ must be
started, then _AkkaSendEvent_ must be started. The first one creates an actor, the second one send an event
to the actor of the first one. The two apps must be started on the same host (because they are refering to
each other using the IP address 127.0.0.1)
_application_create_actor.conf_ contains configuration about the VM that will create the actor.
_application_send_event.conf_ contains configuration about the VM that will send the event.
The binding ports are different between the two configurations to allow the two applications to be started
from a single host.

### io.sarl.akka.publishsubscribe

Testing communication between actors on two VM using network and the publish/subscribe feature of the akka-cluster-tools.
First, start _AkkaCreateSubscribers_, then _AkkaCreatePublisher_ when the first one is initialized.
Possibilities for sending messages using _DistributedPubSub_:
- Publish: the message is directed toward a topic, all subscribers registered for a topic will receive the message
- Send: the message is directed toward a path (system/actor), and sent to a single actor
- SendToAll: the message is directed toward a path, and sent to all matching actors

Possibilities for getting the list of topics:
- Sending getTopicsInstance() to a mediator actor and waiting for the CurrentTopics reply