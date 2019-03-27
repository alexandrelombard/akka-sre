package io.sarl.akka.space

import akka.actor.ActorContext
import akka.actor.ActorRef
import akka.cluster.pubsub.DistributedPubSub
import akka.cluster.pubsub.DistributedPubSubMediator
import io.sarl.akka.AkkaAgent
import io.sarl.lang.core.*
import io.sarl.lang.core.EventListener
import io.sarl.lang.util.SynchronizedSet
import io.sarl.util.OpenEventSpace
import io.sarl.util.OpenEventSpaceSpecification
import java.lang.IllegalArgumentException
import java.util.*

class AkkaEventSpace(val agentContext: AkkaAgentContext) : OpenEventSpace {

    private val mediator = DistributedPubSub.get(agentContext.agent.context.system()).mediator()
    private val spaceId = SpaceID(agentContext.id, UUID.randomUUID(), OpenEventSpaceSpecification::class.java)
    private val topic = spaceId.id.toString()    // Random topic (space identifier)

    override fun register(entity: EventListener): Address {
        if(entity is AkkaAgent) {
            mediator.tell(DistributedPubSubMediator.Subscribe(topic, entity.self), entity.self)
        } else {
            throw IllegalArgumentException("The event listener is not an Akka actor");
        }

        return Address(spaceId, entity.id)
    }

    override fun unregister(entity: EventListener): Address {
        if(entity is AkkaAgent) {
            mediator.tell(DistributedPubSubMediator.Unsubscribe(topic, entity.self), entity.self)
        } else {
            throw IllegalArgumentException("The event listener is not an Akka actor");
        }

        return Address(spaceId, entity.id)
    }

    override fun getParticipants(): SynchronizedSet<UUID> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAddress(id: UUID): Address {
        return Address(spaceId, null)
    }

    override fun getSpaceID(): SpaceID {
        return spaceId
    }

    override fun getID(): SpaceID {
        return spaceId
    }

    override fun emit(eventSource: UUID?, event: Event, scope: Scope<Address>?) {
        // TODO Use the scope
        mediator.tell(DistributedPubSubMediator.Publish(spaceId.id.toString(), event), ActorRef.noSender())
    }
}