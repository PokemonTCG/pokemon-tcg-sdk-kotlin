plugins {
    id("java-library")
    id("kotlin")
    id("maven-publish")
}

group = project.properties["GROUP"]!!
version = project.properties["VERSION_NAME"]!!

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api(project(":library"))
    implementation(libs.kotlinx.coroutines.rx2)
}

