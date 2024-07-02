plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hiltPlugin)
//    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.amlavati.domain"
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
//    composeCompiler {
//        enableStrongSkippingMode = true
//    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.androidx.material3)

//    hilt
    implementation(libs.hilt)
    ksp(libs.hiltKsp)

    testImplementation(libs.coroutineTest)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.mockitoCore)
//    testImplementation(libs.mockitoKotlin)
//    testImplementation(libs.mockitoInline)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}