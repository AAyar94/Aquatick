plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.aayar94.settings_domain"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreui))
    implementation(project(Modules.aquaTrackerDomain))
}