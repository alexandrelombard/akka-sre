package io.sarl.akka.cluster

import akka.actor.ActorSystem
import akka.cluster.Cluster
import com.typesafe.config.ConfigFactory

object ClusterTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val config = ConfigFactory.parseResources(javaClass, "cluster.conf")
        val actorSystem = ActorSystem.create("cluster-test", config)
        val cluster = Cluster.get(actorSystem)
    }

}