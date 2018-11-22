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

### io.sarl.akka.eventbus

Testing the event-bus for peer-to-many communication between actors.