plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.aayar94.notification_domain"
}

dependencies{
    implementation(project(Modules.core))
    implementation(Coroutines.coroutines)
}