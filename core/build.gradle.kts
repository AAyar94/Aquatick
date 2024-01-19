plugins {
    `android-library`
    `kotlin-android`
    id("com.google.gms.google-services")
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.aayar94.core"
}
dependencies{
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
    implementation("com.google.firebase:firebase-analytics")
}