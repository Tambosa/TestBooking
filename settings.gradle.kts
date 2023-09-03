pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "TestBooking"
include(":app")
include(":core_network")
include(":core_ui")
include(":feature_hotel")
include(":feature_hotel_details")
include(":feature_booking")
