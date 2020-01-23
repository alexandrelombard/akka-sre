import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "io.sarl.akka"
version = "1.0.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.61"
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
        implementation("com.typesafe.akka:akka-actor_$akka:$akkaVersion")
        implementation("com.typesafe.akka:akka-remote_$akka:$akkaVersion")
        implementation("com.typesafe.akka:akka-cluster_$akka:$akkaVersion")
        implementation("com.typesafe.akka:akka-cluster-tools_$akka:$akkaVersion")
    }
}
