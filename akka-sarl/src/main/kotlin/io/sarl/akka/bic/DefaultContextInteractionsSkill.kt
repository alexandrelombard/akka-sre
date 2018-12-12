package io.sarl.akka.bic

import io.sarl.akka.AkkaAgent
import io.sarl.core.DefaultContextInteractions
import io.sarl.lang.core.*
import java.util.*

//class DefaultContextInteractionsSkill(val akkaAgent: AkkaAgent, owner: Agent) : Skill(owner), DefaultContextInteractions {
//
//    override fun getDefaultContext(): AgentContext {
//        return TMSarlAgent.this.getDefaultSpace().getAgentContext();
//    }
//
//    override fun getDefaultSpace(): EventSpace {
//        return TMSarlAgent.this.getDefaultSpace();
//    }
//
//    override fun getDefaultAddress(): Address {
//        return TMSarlAgent.this.getDefaultSpace().getAddress(getSarlAgent().getID());
//    }
//
//    override fun emit(e: Event, scope: Scope<Address>) {
//        if (e.getSource() == null) {
//            e.setSource(getDefaultSpace().getAddress(getID()));
//        }
//        TMSarlAgent.this.getDefaultSpace().emit(e, scope);
//    }
//
//    override fun emit(e: Event) {
//        if (e.getSource() == null) {
//            e.setSource(getDefaultSpace().getAddress(getID()));
//        }
//        TMSarlAgent.this.getDefaultSpace().emit(e);
//    }
//
//    @Deprecated("")
//    override fun receive(receiver: UUID, e: Event) {
//        willReceive(receiver, e);
//    }
//
//    override fun isDefaultSpace(space: Space): Boolean {
//        return space.getID().equals(TMSarlAgent.this.getDefaultSpace().getID());
//    }
//
//    override fun isDefaultSpace(space: SpaceID): Boolean {
//        return space.equals(TMSarlAgent.this.getDefaultSpace().getID());
//    }
//
//    override fun isDefaultSpace(space: UUID): Boolean {
//        return space.equals(TMSarlAgent.this.getDefaultSpace().getID().getID());
//    }
//
//    override fun isInDefaultSpace(event: Event): Boolean {
//        return isDefaultSpace(event.getSource().getSpaceId());
//    }
//
//    override fun isDefaultContext(context: AgentContext): Boolean {
//        return context.getID().equals(TMSarlAgent.this.getDefaultSpace().getAgentContext().getID());
//    }
//
//    @Override
//    override fun isDefaultContext(contextID: UUID): Boolean {
//        return contextID.equals(TMSarlAgent.this.getDefaultSpace().getAgentContext().getID());
//    }
//
//    override fun spawn(aAgent: Class<io.sarl.lang.core.Agent>, vararg params: Any): UUID {
//        return TMSarlAgent.this.getDefaultSpace().spawn(aAgent, getOwner().getID(), null, params);
//    }
//
//    override fun willReceive(receiver: UUID, event: Event) {
//        if (event.getSource() == null) {
//            event.setSource(getDefaultSpace().getAddress(getID()));
//        }
//        TMSarlAgent.this.getDefaultSpace().emit(event,
//                Scopes.addresses(TMSarlAgent.this.getDefaultSpace().getAddress(receiver)));
//    }
//
//}