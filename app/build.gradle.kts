@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.lelestacia.gamediscountreminder"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lelestacia.gamediscountreminder"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //  Ktx
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    //  Compose Activity
    implementation(libs.compose.activity)

    //  Compose BOM
    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))

    //  Compose
    implementation(libs.compose.material.design)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphic)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)

    //  Hilt
    implementation(libs.hilt)
    implementation(libs.hilt.compose)
    ksp(libs.hilt.compiler)

    //  Ui Test
    androidTestImplementation(libs.ui.test.espresso.core)
    androidTestImplementation(libs.ui.test.android.junit)
    androidTestImplementation(libs.ui.test.compose.junit)
    androidTestImplementation(libs.ui.test.compose.manifest)

    //  Unit Test
    testImplementation(libs.test.junit)
}