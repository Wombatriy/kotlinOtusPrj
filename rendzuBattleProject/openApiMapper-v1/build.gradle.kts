plugins {
    id("build-jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation(kotlin("stdlib"))
    implementation(projects.openApiHomework)
    implementation(projects.openApiCommon)

    testImplementation(kotlin("test-junit"))
}