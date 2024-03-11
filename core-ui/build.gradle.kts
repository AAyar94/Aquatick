plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.aayar94.core_ui"
}

dependencies {
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.2")
    implementation("androidx.datastore:datastore-preferences:1.0.0")

}