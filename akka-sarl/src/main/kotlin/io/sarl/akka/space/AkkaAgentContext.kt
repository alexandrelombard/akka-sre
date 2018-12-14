package io.sarl.akka.space

import io.sarl.akka.AkkaAgent
import io.sarl.lang.core.AgentContext
import io.sarl.lang.core.EventSpace
import io.sarl.lang.core.Space
import io.sarl.lang.core.SpaceSpecification
import io.sarl.lang.util.SynchronizedIterable
import java.util.*

class AkkaAgentContext(val agent: AkkaAgent) : AgentContext {

    private val id = UUID.randomUUID()
    private val defaultSpace = AkkaEventSpace(this)
    private val spaces = hashMapOf<UUID, Space>()

    init {
        spaces[defaultSpace.id.id] = defaultSpace
    }

    override fun getSpaces(): SynchronizedIterable<out Space> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <S : Space?> getSpaces(spec: Class<out SpaceSpecification<S>>?): SynchronizedIterable<S> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <S : Space?> getOrCreateSpaceWithSpec(spec: Class<out SpaceSpecification<S>>?, spaceUUID: UUID?, vararg creationParams: Any?): S {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getID(): UUID {
        return this.id
    }

    override fun <S : Space?> createSpace(spec: Class<out SpaceSpecification<S>>?, spaceUUID: UUID?, vararg creationParams: Any?): S {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <S : Space?> getSpace(spaceUUID: UUID): S {
        return spaces[spaceUUID] as S   // TODO Issue?
    }

    override fun getDefaultSpace(): EventSpace {
        return defaultSpace
    }

    override fun <S : Space?> getOrCreateSpaceWithID(spec: Class<out SpaceSpecification<S>>?, spaceUUID: UUID?, vararg creationParams: Any?): S {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}