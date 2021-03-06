import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
}

group = "dev.fstudio"
version = "0.1"

repositories {
    mavenCentral()
}

application {
    mainClass.set("dev.fstudio.mcworldstats.MainKt")
}

val createBuildClassFile = task("createBuildClassFile") {
    doFirst {
        val packagePath = "dev${File.separator}fstudio${File.separator}mcworldstats"
        val path =
            "src${File.separator}" +
                    "main${File.separator}" +
                    "kotlin${File.separator}" +
                    "$packagePath${File.separator}"

        File(path, "Build.kt").writeText(
            "package dev.fstudio.mcworldstats\n\n" +
                    "object Build {\n" +
                    "    const val VERSION = $version\n" +
                    "}"
        )
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<ShadowJar> {
    dependsOn(createBuildClassFile)
    manifest {
        attributes["Main-Class"] = "dev.fstudio.mcworldstats.MainKt"
    }
}

dependencies {
    /*   TOML Configuration file   */
    implementation("net.peanuuutz:tomlkt:0.1.0")

    /*   Ktor API   */
    implementation("io.ktor:ktor-server-core:1.6.7")
    implementation("io.ktor:ktor-server-netty:1.6.7")
    implementation("io.ktor:ktor-serialization:1.6.7")
    implementation("io.ktor:ktor-html-builder:1.6.7")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-css:+")


    /*   Logger   */
    implementation("ch.qos.logback:logback-classic:1.2.10")

    /*   Exposed   */
    implementation("org.jetbrains.exposed:exposed-core:0.37.3")
    implementation("org.jetbrains.exposed:exposed-dao:0.37.3")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.37.3")
    implementation("org.jetbrains.exposed:exposed-java-time:0.37.3")

    /*   MySQL Connector   */
    implementation("mysql:mysql-connector-java:8.0.25")
}