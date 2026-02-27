plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
}

group = "kotlinOtisProj.rendzuBattleProject"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

ext {
    val specDir = layout.projectDirectory.dir("../specs")
    set("rendzuDuel", specDir.file("rendzuDuel.yaml").toString())
//    set("rendzuDuel-v2", specDir.file("rendzuDuel.yaml").toString())
}

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
    }
}