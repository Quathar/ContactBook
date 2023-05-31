// Project name
rootProject.name = "ContactBook"

// Modules
include(":application")
include(":data")

// Repositories for plugin resolution
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

// Repositories for dependencies resolution
//dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
//    repositories {
//        google()
//        mavenCentral()
//    }
//}