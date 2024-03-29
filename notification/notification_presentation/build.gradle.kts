plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.aayar94.notification_presentation"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreui))
    implementation(project(Modules.notificationDomain))

    implementation(Coil.coilCompose)
    implementation(Coil.coilComposeSvg)
}