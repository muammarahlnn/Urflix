pluginManagement {
    includeBuild("build-logic")
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
    }
}

rootProject.name = "Urflix"
include(":app")

include(":core:designsystem")
include(":core:network")
include(":core:model")
include(":core:data")
include(":core:common")
include(":core:datastore")

include(":feature")
include(":feature:homenavigator")
include(":feature:home")
include(":feature:profile")
include(":feature:search")
include(":feature:bookmarks")
include(":feature:camera")

include(":feature:filmdetails")
