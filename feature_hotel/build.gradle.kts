plugins {
    id(Plugins.android_library)
    id(Plugins.android_kotlin)
    id(Plugins.google_daggerHilt)
    id(Plugins.kotlin_kapt)
}

android {
    namespace = "com.example.feature_hotel"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion

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
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }
    kotlinOptions {
        jvmTarget = ConfigData.jvmTarget
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core_network"))
    implementation(project(":core_ui"))

    //Dagger - Hilt
    implementation(Libs.hilt_android)
    kapt(Libs.hilt_android_compiler)
    kapt(Libs.androidx_hilt_compiler)

    // Coroutine Lifecycle Scopes
    implementation(Libs.androidx_lifecycle_viewmodel_ktx)
    implementation(Libs.androidx_lifecycle_runtime_ktx)

    //Google Navigation
    implementation (Libs.navigation_fragment)
    implementation (Libs.navigation_ui)

    implementation(Libs.androidx_ktx)
    implementation(Libs.androidx_appcompat)
    implementation(Libs.material)
    implementation(Libs.constraint)
}