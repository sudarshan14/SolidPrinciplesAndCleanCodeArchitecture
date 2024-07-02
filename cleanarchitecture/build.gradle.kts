plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
//    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias((libs.plugins.hiltPlugin))
}

android {
    namespace = "com.amlavati.cleanarchitecture"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.amlavati.cleanarchitecture"
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
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data-remote"))
    implementation(project(":data-local"))
    implementation(project(":data-repository"))
    implementation(project(":shimmer"))
    implementation(project(":presentation-common"))
    implementation(project(":presentation-post"))
    implementation(project(":presentation-user"))

    implementation(project(":presentation-common-mvi"))
    implementation(project(":presentation-post-mvi"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.material3)
//    implementation("androidx.compose.material:material:1.4.1")
//    implementation("com.github.nanihadesuka:LazyColumnScrollbar:2.1.0")
//    implementation("com.github.BILLyTheLiTTle:LazyColumns:0.3.7")
    implementation(libs.hilt)
    ksp(libs.hiltKsp)
    implementation(libs.hiltNavigation)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}