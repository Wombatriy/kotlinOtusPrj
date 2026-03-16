plugins {
    id("build-jvm")
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    sourceSets {
        val coroutinesVersion: String by project
        main {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                api("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.serialization.json)
            }
        }
    }
}

group = rootProject.group
version = rootProject.version