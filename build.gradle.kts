plugins {
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
    id("fabric-loom") version "1.0-SNAPSHOT"
    id("io.github.juuxel.loom-quiltflower") version "1.7.4"
    id("org.quiltmc.quilt-mappings-on-loom") version "4.2.1"
}

group = "de.daniel"
version = "1.1"

repositories {
    mavenCentral()
    maven("https://maven.terraformersmc.com/releases/")
    maven("https://maven.shedaniel.me/")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    minecraft("com.mojang:minecraft:1.19.2")
    mappings(loom.layered {
        // addLayer(quiltMappings.mappings("org.quiltmc:quilt-mappings:1.19.2+build.14:v2"))
        officialMojangMappings()
    })

    modImplementation("net.fabricmc:fabric-loader:0.14.10")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.64.0+1.19.2")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.8.5+kotlin.1.7.20")

    modApi("com.terraformersmc:modmenu:4.0.6")
    modApi("me.shedaniel.cloth:cloth-config-fabric:8.2.88") {
        exclude(group= "net.fabricmc.fabric-api")
    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(17)
    }
}

java {
    withSourcesJar()
}