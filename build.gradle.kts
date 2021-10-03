plugins {
    kotlin("jvm") version "1.5.31"
}

group = "me.limelier"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib", "1.5.31"))
    implementation("dev.kord:kord-core:0.8.0-M5")
    implementation("io.javalin:javalin:4.0.1")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    implementation("io.github.microutils:kotlin-logging:2.0.11")
    implementation("org.slf4j:slf4j-simple:1.7.32")
    implementation("org.apache.logging.log4j:log4j-core:2.14.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.5")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
}

tasks.test {
    useJUnitPlatform()
}