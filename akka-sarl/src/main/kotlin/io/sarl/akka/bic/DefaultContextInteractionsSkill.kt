package io.sarl.akka.bic

import io.sarl.akka.AkkaAgent
import io.sarl.akka.space.AkkaEventSpace
import io.sarl.core.DefaultContextInteractions
import io.sarl.lang.core.*
import java.util.*

class DefaultContextInteractionsSkill(val akkaAgent: AkkaAgent, owner: Agent) : Skill(owner), DefaultContextInteractions {

    override fun getDefaultContext(): AgentContext {
        return akkaAgent.agentContext
    }

    override fun getDefaultSpace(): EventSpace {
        return akkaAgent.agentContext.defaultSpace
    }

    override fun getDefaultAddress(): Address {
        TODO("Not implemented")
    }

    override fun emit(e: Event, scope: Scope<Address>) {
        if (e.source == null) {
            e.source = defaultSpace.getAddress(id);
        }

        defaultSpace.emit(e.source.uuid, e, scope)
    }

    override fun emit(e: Event) {
        if (e.source == null) {
            e.source = defaultSpace.getAddress(id);
        }
        defaultSpace.emit(e);
    }

    @Deprecated("")
    override fun receive(receiver: UUID, e: Event) {
        willReceive(receiver, e);
    }

    override fun isDefaultSpace(space: Space): Boolean {
        return space.id == defaultSpace.id;
    }

    override fun isDefaultSpace(space: SpaceID): Boolean {
        return space.equals(defaultSpace.getID());
    }

    override fun isDefaultSpace(space: UUID): Boolean {
        return space == defaultSpace.id.id;
    }

    override fun isInDefaultSpace(event: Event): Boolean {
        return isDefaultSpace(event.source.spaceId);
    }

    override fun isDefaultContext(context: AgentContext): Boolean {
        return context.id == defaultContext.id;
    }

    override fun isDefaultContext(contextID: UUID): Boolean {
        return contextID == defaultContext.id;
    }

    override fun spawn(aAgent: Class<out io.sarl.lang.core.Agent>, vararg params: Any): UUID {
        TODO("Not implemented")
    }

    override fun willReceive(receiver: UUID, event: Event) {
//        if (event.source == null) {
//            event.source = defaultSpace.getAddress(id);
//        }
//        defaultSpace.emit(event,
//                Scopes.addresses(defaultSpace.getAddress(receiver)));
        TODO("Not implemented")
    }

}