plugins {
    id(Plugins.android_library)
    id(Plugins.android_kotlin)
}

android {
    namespace = "com.example.feature_payment_received"
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
    implementation(project(":core_ui"))

    //Google Navigation
    implementation(Libs.navigation_fragment)
    implementation(Libs.navigation_ui)

    implementation(Libs.androidx_ktx)
    implementation(Libs.androidx_appcompat)
    implementation(Libs.material)
    implementation(Libs.constraint)
}