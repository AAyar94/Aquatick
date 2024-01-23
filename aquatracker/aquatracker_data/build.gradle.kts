import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    `android-library`
    `kotlin-android`
}

val firebaseToken: String = gradleLocalProperties(rootDir).getProperty("FirebaseToken")


apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.aayar94.aquatracker_data"

    buildFeatures {
        buildConfig = true
    }

    buildTypes.forEach {
        it.buildConfigField("String", "FirebaseToken", firebaseToken)
    }
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.aquaTrackerDomain))

    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.moshiConverter)

    "kapt"(Room.roomCompiler)
    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)

    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-database")
}