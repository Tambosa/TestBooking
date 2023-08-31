// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.android_application) version PluginsVersion.androidApplicationPlugin apply false
    id(Plugins.android_kotlin) version PluginsVersion.androidKotlinPlugin apply false
    id(Plugins.android_library) version PluginsVersion.androidLibraryPlugin apply false
    id(Plugins.google_daggerHilt) version PluginsVersion.googleDaggerHiltPlugin apply false
}