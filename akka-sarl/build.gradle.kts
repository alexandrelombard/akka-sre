import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.github.jengelman.gradle.plugins.shadow.transformers.AppendingTransformer
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "io.sarl.akka"
version = "1.0.0-SNAPSHOT"

plugins {
    java
    kotlin("jvm") version "1.3.61"
    id("com.github.johnrengelman.shadow") version "5.0.0"
    `maven-publish`
}

val akka = "2.13"
val akkaVersion = "2.6.1"
val sarlVersion = "0.10.0"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

kotlin {
    sourceSets["main"].dependencies {
        implementation("commons-cli:commons-cli:1.4")

        implementation("io.sarl:io.sarl.core:$sarlVersion")
        implementation("io.sarl.lang:io.sarl.lang:$sarlVersion")
        implementation("io.sarl.lang:io.sarl.lang.core:$sarlVersion")
        implementation("io.sarl:io.sarl.util:$sarlVersion")

        implementation("org.eclipse.xtext:org.eclipse.xtext.xbase:2.19.0")

        implementation("com.typesafe.akka:akka-actor_$akka:$akkaVersion")
        implementation("com.typesafe.akka:akka-remote_$akka:$akkaVersion")
        implementation("com.typesafe.akka:akka-cluster_$akka:$akkaVersion")
        implementation("com.typesafe.akka:akka-cluster-tools_$akka:$akkaVersion")
    }
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "io.sarl.akka.Boot"
        attributes("SARL-Runtime-Environment",
                Pair("SRE-Name", "Akka SRE"),
                Pair("SARL-Spec-Version", "0.11"),
                Pair("Standalone-SRE", "true"),
                Pair("VM-Arguments", "-ea"))
        attributes("Program-Arguments",
                Pair("CLI-Show-Logo", ""),
                Pair("CLI-Hide-Logo", ""),
                Pair("CLI-Show-Info", ""),
                Pair("CLI-Hide-Info", ""),
                Pair("CLI-Default-Context-ID", ""),
                Pair("CLI-BootAgent-Context-ID", ""),
                Pair("CLI-Offline", ""),
                Pair("CLI-Embedded", ""))
    }

    from(configurations.compileClasspath.get().map { if(it.isDirectory) it else zipTree(it) }) {
        exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
    }
}

//val shadowJar by tasks.getting(ShadowJar::class) {
//    manifest {
//        attributes["Main-Class"] = "io.sarl.akka.Boot"
//        attributes("SARL-Runtime-Environment",
//                Pair("SRE-Name", "Akka SRE"),
//                Pair("SARL-Spec-Version", "0.11"),
//                Pair("Standalone-SRE", "true"),
//                Pair("VM-Arguments", "-ea"))
//        attributes("Program-Arguments",
//                Pair("CLI-Show-Logo", ""),
//                Pair("CLI-Hide-Logo", ""),
//                Pair("CLI-Show-Info", ""),
//                Pair("CLI-Hide-Info", ""),
//                Pair("CLI-Default-Context-ID", ""),
//                Pair("CLI-BootAgent-Context-ID", ""),
//                Pair("CLI-Offline", ""),
//                Pair("CLI-Embedded", ""))
//    }
//
//    transform(AppendingTransformer::class.java) {
//        resource = "reference.conf"
//    }
////    this.with(jar)
//}