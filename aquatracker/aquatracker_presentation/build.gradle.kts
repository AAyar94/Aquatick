plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.aayar94.aquatracker_presentation"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreui))
    implementation(project(Modules.aquaTrackerDomain))

    implementation(Coil.coilCompose)
    implementation(Coil.coilComposeSvg)

    implementation(VicoCharts.vicoCore)
    implementation(VicoCharts.vicoCompose)
    implementation(VicoCharts.vicoComposeM3)
}
