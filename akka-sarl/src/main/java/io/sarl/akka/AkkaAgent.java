package io.sarl.akka;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import io.sarl.akka.bic.LoggingSkill;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Initialize;
import io.sarl.core.Logging;
import io.sarl.eventdispatching.BehaviorGuardEvaluator;
import io.sarl.eventdispatching.BehaviorGuardEvaluatorRegistry;
import io.sarl.lang.core.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class AkkaAgent extends AbstractActor  implements EventListener {

    static public Props props(Class<? extends Agent> agentClass) {
        return Props.create(AkkaAgent.class, () -> new AkkaAgent(agentClass));
    }

    private final BehaviorGuardEvaluatorRegistry evaluatorRegistry = new BehaviorGuardEvaluatorRegistry();

    private Class<? extends Agent> agentClass;
    private Agent agent;

    private LoggingSkill loggingSkill;
//    private DefaultContextInteractionsSkill spaceSkill;

    public AkkaAgent(Class<? extends Agent> agentClass) {
        // Instantiate the agent
        this.agentClass = agentClass;
        try {
            Constructor<? extends Agent> cons = ((Class<? extends io.sarl.lang.core.Agent>) this.agentClass).getConstructor(
                    UUID.class, UUID.class);
            this.agent = cons.newInstance(null, this.getID());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        // Initialize all attributes
        this.evaluatorRegistry.register(getSarlAgent());
        this.loggingSkill = new LoggingSkill();

        try {
            Method method = Agent.class.getDeclaredMethod("setSkill", Class.class, Skill.class); //$NON-NLS-1$
            boolean isAcc = method.isAccessible();
            try {
                method.setAccessible(true);
                method.invoke(getSarlAgent(), Logging.class, this.loggingSkill);    // Register the logging skill
            } finally {
                method.setAccessible(isAcc);
            }
        } catch (Exception e) {
            throw new Error(e);
        }

        // Fire initialize event
        final Initialize initializeEvent = new Initialize(null);
        receiveEvent(initializeEvent);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().build();
    }

    @Override
    public void receiveEvent(Event event) {
//        if (event.getSource() == null) {
//            event.setSource(getDefaultSpace().getAddress(this.sarlAgent.getID()));
//        }
        fireEvent(event);
    }

    void fireEvent(Event event) {
        final Iterable<BehaviorGuardEvaluator> evaluators = this.evaluatorRegistry.getBehaviorGuardEvaluators(event);
        final Collection<Runnable> handlers = new ArrayList<>();
        for (BehaviorGuardEvaluator evaluator : evaluators) {
            try {
                evaluator.evaluateGuard(event, handlers);
            } catch (Throwable e) {
                this.loggingSkill.error(e.getLocalizedMessage(), e);
            }
        }
        for (Runnable handler : handlers) {
            try {
                handler.run();
            }  catch (Throwable e) {
                this.loggingSkill.error(e.getLocalizedMessage(), e);
            }
        }
    }

    @Override
    public UUID getID() {
        return null;
    }

    public Agent getSarlAgent() {
        return this.agent;
    }

//    private class DefaultContextInteractionsSkill extends Skill implements DefaultContextInteractions {
//
//        private final AkkaAgent akkaAgent;
//
//        DefaultContextInteractionsSkill(AkkaAgent akkaAgent) {
//            this.akkaAgent = akkaAgent;
//        }
//
//        @Override
//        public AgentContext getDefaultContext() {
//            return TMSarlAgent.this.getDefaultSpace().getAgentContext();
//        }
//
//        @Override
//        public EventSpace getDefaultSpace() {
//            return TMSarlAgent.this.getDefaultSpace();
//        }
//
//        @Override
//        public Address getDefaultAddress() {
//            return TMSarlAgent.this.getDefaultSpace().getAddress(getSarlAgent().getID());
//        }
//
//        @Override
//        public void emit(Event e, Scope<Address> scope) {
//            if (e.getSource() == null) {
//                e.setSource(getDefaultSpace().getAddress(getID()));
//            }
//            TMSarlAgent.this.getDefaultSpace().emit(e, scope);
//        }
//
//        @Override
//        public void emit(Event e) {
//            if (e.getSource() == null) {
//                e.setSource(getDefaultSpace().getAddress(getID()));
//            }
//            TMSarlAgent.this.getDefaultSpace().emit(e);
//        }
//
//        @Override
//        @Deprecated
//        public void receive(UUID receiver, Event e) {
//            willReceive(receiver, e);
//        }
//
//        @Override
//        public boolean isDefaultSpace(Space space) {
//            return space.getID().equals(TMSarlAgent.this.getDefaultSpace().getID());
//        }
//
//        @Override
//        public boolean isDefaultSpace(SpaceID space) {
//            return space.equals(TMSarlAgent.this.getDefaultSpace().getID());
//        }
//
//        @Override
//        public boolean isDefaultSpace(UUID space) {
//            return space.equals(TMSarlAgent.this.getDefaultSpace().getID().getID());
//        }
//
//        @Override
//        public boolean isInDefaultSpace(Event event) {
//            return isDefaultSpace(event.getSource().getSpaceId());
//        }
//
//        @Override
//        public boolean isDefaultContext(AgentContext context) {
//            return context.getID().equals(TMSarlAgent.this.getDefaultSpace().getAgentContext().getID());
//        }
//
//        @Override
//        public boolean isDefaultContext(UUID contextID) {
//            return contextID.equals(TMSarlAgent.this.getDefaultSpace().getAgentContext().getID());
//        }
//
//        @Override
//        public UUID spawn(Class<? extends io.sarl.lang.core.Agent> aAgent, Object... params) {
//            return TMSarlAgent.this.getDefaultSpace().spawn(aAgent, getOwner().getID(), null, params);
//        }
//
//        @Override
//        public void willReceive(UUID receiver, Event event) {
//            if (event.getSource() == null) {
//                event.setSource(getDefaultSpace().getAddress(getID()));
//            }
//            TMSarlAgent.this.getDefaultSpace().emit(event,
//                    Scopes.addresses(TMSarlAgent.this.getDefaultSpace().getAddress(receiver)));
//        }
//
//    }
}
