plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.aayar94.workmanager"
}

dependencies {
    implementation(project(Modules.core))
}