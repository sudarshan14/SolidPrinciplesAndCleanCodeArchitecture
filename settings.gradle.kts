pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
       maven{ url = uri("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven{ url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Solid Principles"
include(":app")


include(":cleanarchitecture")
include(":domain")
include(":data-repository")
include(":data-remote")
include(":data-local")
include(":shimmer")
include(":presentation-common")
include(":presentation-post")
include(":presentation-user")
include(":presentation-common-mvi")
include(":presentation-post-mvi")
