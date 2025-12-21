@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

rootProject.name = "lscythe-version-catalog"

include(
    ":versions-androidx",
    ":versions-kotlinx", 
    ":versions-compose-jetpack",
    ":versions-compose-multiplatform",
    ":versions-utils",
)