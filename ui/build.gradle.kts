plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.kotlin.serialization)
    kotlin("kapt")
}

android {
    namespace = "com.sujith.ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(project(":domain"))
    //Core
    implementation(libs.bundles.core)
    implementation(platform(libs.androidx.compose.bom))
    //Hilt
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.compiler)
    //Navigation
    implementation(libs.bundles.navigation)
    //Coil
    implementation(libs.coil.compose)
    //Lottie
    implementation(libs.lottie)
    //Unit test
    testImplementation(libs.bundles.unitTest)
    androidTestImplementation(libs.bundles.uiTest)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.bundles.debugImpl)
    implementation (libs.ui.tooling.preview)
}