object Libs {
    //retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${LibsVersions.converter_gson}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${LibsVersions.converter_gson}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${LibsVersions.ok_http}"
    const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${LibsVersions.ok_http}"

    //Dagger - Hilt
    const val hilt_android = "com.google.dagger:hilt-android:${LibsVersions.hilt_android_compiler}"
    const val hilt_android_compiler =
        "com.google.dagger:hilt-android-compiler:${LibsVersions.hilt_android_compiler}"
    const val androidx_hilt_compiler = "androidx.hilt:hilt-compiler:${LibsVersions.hilt_compiler}"

    // Coroutine Lifecycle Scopes
    const val androidx_lifecycle_runtime_ktx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${LibsVersions.lifecycle_runtime_ktx}"
    const val androidx_lifecycle_viewmodel_ktx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibsVersions.lifecycle_runtime_ktx}"

    //Google Navigation
    const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${LibsVersions.navigation}"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${LibsVersions.navigation}"

    const val material = "com.google.android.material:material:${LibsVersions.material}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${LibsVersions.constraint}"
    const val androidx_appcompat = "androidx.appcompat:appcompat:${LibsVersions.appcompat}"
    const val androidx_ktx = "androidx.core:core-ktx:${LibsVersions.ktx}"
}