plugins {
    id(Plugins.android_application)
    id(Plugins.android_kotlin)
    id(Plugins.google_daggerHilt)
    id(Plugins.kotlin_kapt)
}

android {
    namespace = "com.example.testbooking"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = "com.example.testbooking"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

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
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }
    kotlinOptions {
        jvmTarget = ConfigData.jvmTarget
    }
}

dependencies {
    implementation(project(":core_network"))

    //retrofit
    implementation(Libs.retrofit)
    implementation(Libs.converter_gson)
    implementation(Libs.okhttp)
    implementation(Libs.logging_interceptor)

    //Dagger - Hilt
    implementation(Libs.hilt_android)
    kapt(Libs.hilt_android_compiler)
    kapt(Libs.androidx_hilt_compiler)

    // Coroutine Lifecycle Scopes
    implementation(Libs.androidx_lifecycle_viewmodel_ktx)
    implementation(Libs.androidx_lifecycle_runtime_ktx)

    implementation(Libs.androidx_ktx)
    implementation(Libs.androidx_appcompat)
    implementation(Libs.material)
    implementation(Libs.constraint)
}