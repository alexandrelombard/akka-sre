import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "io.sarl.akka"
version = "1.0.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.61"
    `maven-publish`
}

val akka = "2.13"
val akkaVersion = "2.6.1"

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    sourceSets["main"].dependencies {
        implementation("commons-cli:commons-cli:1.4")
        implementation("com.typesafe.akka:akka-actor_$akka:$akkaVersion")
        implementation("com.typesafe.akka:akka-remote_$akka:$akkaVersion")
        implementation("com.typesafe.akka:akka-cluster_$akka:$akkaVersion")
        implementation("com.typesafe.akka:akka-cluster-tools_$akka:$akkaVersion")
    }
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