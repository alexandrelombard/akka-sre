# Akka Sarl

Implementation of a Sarl Runtime Environment using Akka.io.

Note: This project is mainly a todo list for planning the mapping of Sarl multiagent concepts to the
Akka's actor model concepts.

## Mapping concepts

| SARL Element | SARL Concept | Akka Implementation |
|----------|:-------------:|------:|
| Identifying the agents | Address | ActorRef? |
| Agent abstraction | Agent | AbstractActor? |
| Exchanged information format for direct interaction | Event | - |
| Receiving SARL events | Behavior units on | override createReceive()? |
| Default context definition | Context | ActorSystem? |
| Default space definition | EventSpace | ActorSystem? |
| Agent life-cycle |  | - |
| Managing platform events | AgentSpawned, AgentKilled | override preStart()? override postStop()? |
| Agent Spawning | spawn functions | - |
| Built-in capacity implementation | BICs | On-top custom implementation |
| | SRE Booting | - |

Note: Check difference in implementation between the SARL Context and SARL EventSpace

**Differences between the agent and the actor:**

Is an actor a thread? Not really, actors are purely reactive, they don't do anything when they are not receiving messages.

Are messages send through their own thread? Asynchronous emission and reception ? It seems so, messages are put in a
message box, and polled in order by the receiving actor. An actor is not waiting for the recipient to manage the
message, however, I don't think a new thread isn't created to populate the message box (it's possible that if
multiples actors are sending a message to a single actor, the synchronization mechanism may pause the actor, this should
be checked).

Are tell() calls equivalent to _emit_? Sort of, but they look directed toward a specific agent.

Holarchy, inner context and so on? An actor is always a child of another actor.