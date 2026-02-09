

pluginManagement {
    val kotlinVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion
    }
}

rootProject.name = "compositeLessons"

include(":m1l1-first")
include(":m2l1-dsl")
include(":m2l2-coroutines")