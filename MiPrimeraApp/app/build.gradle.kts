plugins {
        alias(libs.plugins.android.application)
        alias(libs.plugins.kotlin.android)
        // CAMBIA LA LÍNEA QUE DIO ERROR POR ESTA:
        alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.example.miprimeraapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.miprimeraapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    // Extensiones para Activity y ViewModel (Clase 9 y 10)
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // --- NUEVA LIBRERÍA CLASE 10 (CORRUTINAS) ---
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Necesario para el listado de la Clase 6
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}