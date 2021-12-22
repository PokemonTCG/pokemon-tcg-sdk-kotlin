plugins {
    id("java-library")
    id("kotlin")
    id("kotlinx-serialization")
    id("maven-publish")
}

group = project.properties["GROUP"]!!
version = project.properties["VERSION_NAME"]!!

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    testImplementation(libs.bundles.testing)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.kotlinx.serialization.json)

    api(libs.networking.retrofit.core)
    api(libs.networking.retrofit.serialization)
    api(libs.networking.okhttp.logging)
}
