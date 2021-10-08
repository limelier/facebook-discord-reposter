import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.5.31"
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

kotlin {
    explicitApi()
}

group = "me.limelier"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib", "1.5.31"))

    implementation("dev.kord:kord-core:0.8.0-M5")
    implementation("io.javalin:javalin:4.1.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.2")

    // logging
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.1.0")
    implementation("org.apache.logging.log4j:log4j-api:2.14.1")
    implementation("org.apache.logging.log4j:log4j-core:2.14.1")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.14.1") // log4j facade for slf4j

    // tests
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    archiveBaseName.set(project.name)
    mergeServiceFiles()
    manifest {
        attributes(mapOf("Main-Class" to "me.limelier.AppKt"))
    }
}

task("stage") {
    dependsOn("shadowJar")
}