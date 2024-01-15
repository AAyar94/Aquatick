plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.aayar94.onboarding_presentation"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreui))
    implementation(project(Modules.onboardingDomain))
}