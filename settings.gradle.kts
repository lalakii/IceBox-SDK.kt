pluginManagement {
    repositories {
        gradlePluginPortal()
        maven(url = "https://jitpack.io")
        maven(url = "https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven(url = "https://mirrors.lalaki.cn/IceBox-SDK.kt")
        maven(url = "https://jitpack.io")
        maven(url = "https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")
    }
}
rootProject.name = "IceBox-SDK.kt"
include(":sample")
include(":sdk")
