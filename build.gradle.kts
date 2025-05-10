plugins {
    kotlin("jvm") version "2.1.10"
}

group = "ru.akimov1712"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven (url = "https://jitpack.io")
}

dependencies {
    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.javatime)
    implementation(libs.mysql.connector)
    implementation(libs.env)
    implementation(libs.telegram.bot)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(18)
}