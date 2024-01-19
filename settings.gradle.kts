pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }
}

rootProject.name = "Aquatick"
include(":app")
include(":settings")
include(":settings:settings_domain")
include(":settings:settings_presentation")
include(":onboarding")
include(":onboarding:onboarding_domain")
include(":onboarding:onboarding_presentation")
include(":core")
include(":core-ui")
include(":aquatracker")
include(":aquatracker:aquatracker_data")
include(":aquatracker:aquatracker_domain")
include(":aquatracker:aquatracker_presentation")
include(":settings")
include(":settings:settings_presentation")
include("settings:settings_domain")

