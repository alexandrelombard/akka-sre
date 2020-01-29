package io.sarl.akka.space

import akka.actor.ActorContext
import akka.actor.ActorRef
import akka.cluster.pubsub.DistributedPubSub
import akka.cluster.pubsub.DistributedPubSubMediator
import io.sarl.akka.AkkaAgent
import io.sarl.core.OpenEventSpace
import io.sarl.core.OpenEventSpaceSpecification
import io.sarl.lang.core.*
import io.sarl.lang.core.EventListener
import io.sarl.lang.util.SynchronizedSet
import java.lang.IllegalArgumentException
import java.util.*

class AkkaEventSpace(val agentContext: AkkaAgentContext) : OpenEventSpace {

    private val mediator = DistributedPubSub.get(agentContext.agent.context.system()).mediator()
    private val spaceId = SpaceID(agentContext.id, UUID.randomUUID(), OpenEventSpaceSpecification::class.java)
    private val topic = spaceId.id.toString()    // Random topic (space identifier)

    override fun getParticipants(): SynchronizedSet<UUID> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAddress(id: UUID?): Address {
        return Address(this.spaceId, id)
    }

    override fun register(entity: EventListener): Address {
        if(entity is AkkaAgent) {
            mediator.tell(DistributedPubSubMediator.Subscribe(topic, entity.self), entity.self)
        } else {
            throw IllegalArgumentException("The event listener is not an Akka actor");
        }

        return Address(spaceId, entity.id)
    }

    override fun getSpaceID(): SpaceID {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unregister(entity: EventListener): Address {
        if(entity is AkkaAgent) {
            mediator.tell(DistributedPubSubMediator.Unsubscribe(topic, entity.self), entity.self)
        } else {
            throw IllegalArgumentException("The event listener is not an Akka actor");
        }

        return Address(spaceId, entity.id)
    }

    override fun emit(p0: UUID?, p1: Event?, p2: Scope<Address>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}