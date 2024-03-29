@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.aayar94.aquatick"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
kapt {
    correctErrorTypes = true
}
dependencies {
    implementation(Compose.compiler)
    implementation(Compose.ui)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.hiltNavigationCompose)
    implementation(Compose.material)
    implementation(Compose.runtime)
    implementation(Compose.navigation)
    implementation(Compose.viewModelCompose)
    implementation(Compose.activityCompose)
    implementation(AndroidX.splashScreen)
    implementation(Compose.navigation)

    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))

    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-messaging")

    implementation(Coil.coilCompose)
    implementation(Coil.coilComposeSvg)

    implementation(JetpackGlance.jetpackGlance)
    implementation(JetpackGlance.jetpackGlanceAppWidget)
    implementation(JetpackGlance.jetpackGlanceM3)

    implementation(DaggerHilt.hiltAndroid)
    implementation("androidx.compose.material3:material3:1.2.0")
    kapt(DaggerHilt.hiltCompiler)

    implementation("androidx.annotation:annotation:1.7.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")

    implementation(project(Modules.core))
    implementation(project(Modules.coreui))
    implementation(project(Modules.onboardingPresentation))
    implementation(project(Modules.onboardingDomain))
    implementation(project(Modules.aquaTrackerData))
    implementation(project(Modules.aquaTrackerDomain))
    implementation(project(Modules.aquaTrackerPresentation))
    implementation(project(Modules.settingsDomain))
    implementation(project(Modules.settingsPresentation))
    implementation(project(Modules.notificationData))
    implementation(project(Modules.notificationDomain))
    implementation(project(Modules.notificationPresentation))
    implementation(project(Modules.workManager))


    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)

    implementation(Coil.coilCompose)

    implementation(GoogleMaterial.material)

    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.moshiConverter)

    kapt(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)

    testImplementation(Testing.junit4)
    testImplementation(Testing.junitAndroidExt)
    testImplementation(Testing.truth)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.turbine)
    testImplementation(Testing.composeUiTest)
    testImplementation(Testing.mockk)
    testImplementation(Testing.mockWebServer)

    androidTestImplementation(Testing.junit4)
    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.coroutines)
    androidTestImplementation(Testing.turbine)
    androidTestImplementation(Testing.composeUiTest)
    androidTestImplementation(Testing.mockkAndroid)
    androidTestImplementation(Testing.mockWebServer)
    androidTestImplementation(Testing.hiltTesting)
    kaptAndroidTest(DaggerHilt.hiltCompiler)
    androidTestImplementation(Testing.testRunner)
    androidTestImplementation(Testing.dexMaker)
}