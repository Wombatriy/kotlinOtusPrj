plugins {
    kotlin("jvm")
}

val coroutinesVersion: String by project

dependencies {
    testImplementation(kotlin("test-junit5"))

    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    // Homework Hard
    implementation("com.squareup.okhttp3:okhttp:4.12.0") // http client
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.20.0") // from string to object

}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}