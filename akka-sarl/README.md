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
| Managing platform events | AgentSpawned, AgentKilled | - |
| Agent Spawning | spawn functions | - |
| Built-in capacity implementation | BICs | On-top custom implementation |
| | SRE Booting | - |

Note: Check difference in implementation between the SARL Context and SARL EventSpace 