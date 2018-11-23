package io.sarl.akka;

import io.sarl.lang.core.EventSpace;
import io.sarl.lang.core.Space;
import io.sarl.lang.core.SpaceSpecification;
import io.sarl.lang.util.SynchronizedIterable;

import java.util.UUID;

public class AkkaContext implements io.sarl.lang.core.AgentContext {
    @Override
    public UUID getID() {
        return null;
    }

    @Override
    public EventSpace getDefaultSpace() {
        return null;
    }

    @Override
    public SynchronizedIterable<? extends Space> getSpaces() {
        return null;
    }

    @Override
    public <S extends Space> SynchronizedIterable<S> getSpaces(Class<? extends SpaceSpecification<S>> spec) {
        return null;
    }

    @Override
    public <S extends Space> S createSpace(Class<? extends SpaceSpecification<S>> spec, UUID spaceUUID, Object... creationParams) {
        return null;
    }

    @Override
    public <S extends Space> S getOrCreateSpaceWithSpec(Class<? extends SpaceSpecification<S>> spec, UUID spaceUUID, Object... creationParams) {
        return null;
    }

    @Override
    public <S extends Space> S getOrCreateSpaceWithID(Class<? extends SpaceSpecification<S>> spec, UUID spaceUUID, Object... creationParams) {
        return null;
    }

    @Override
    public <S extends Space> S getSpace(UUID spaceUUID) {
        return null;
    }
}
