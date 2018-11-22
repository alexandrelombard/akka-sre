package io.sarl.akka;

import io.sarl.bootstrap.SREBootstrap;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AgentContext;

import java.util.UUID;

public class Bootstrap implements SREBootstrap {
    @Override
    public AgentContext startWithoutAgent() {
        return null;
    }

    @Override
    public UUID startAgent(Class<? extends Agent> agentCls, Object... params) throws Exception {
        return null;
    }

    @Override
    public Iterable<UUID> startAgent(int nbAgents, Class<? extends Agent> agentCls, Object... params) throws Exception {
        return null;
    }

    @Override
    public UUID getBootAgentIdentifier() {
        return null;
    }
}
