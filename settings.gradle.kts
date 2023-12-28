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
