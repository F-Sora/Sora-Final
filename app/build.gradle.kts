plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "jp.ac.gifu_u.sora.vsai_drawing"
    compileSdk = 35

    defaultConfig {
        applicationId = "jp.ac.gifu_u.sora.vsai_drawing"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Firebase BoM（バージョン管理）
    implementation(platform("com.google.firebase:firebase-bom:33.15.0"))
    implementation("com.google.firebase:firebase-analytics")

    // ML Kit: 画像ラベリング用（クラウド版）
    implementation("com.google.mlkit:image-labeling:17.0.7")
}
