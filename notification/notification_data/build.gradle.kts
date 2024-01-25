plugins {
    `android-library`
    `kotlin-android`
    id("com.google.gms.google-services")
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.aayar94.notification_data"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.notificationDomain))

    implementation (platform("com.google.firebase:firebase-bom:27.1.0"))

    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-messaging")
}