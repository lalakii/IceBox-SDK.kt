import com.android.build.gradle.internal.tasks.AarMetadataTask

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    `maven-publish`
    signing
}
android {
    namespace = "com.catchingnow.icebox.sdk"
    compileSdk = 34
    version = 1.1
    buildTypes {
        named("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions.jvmTarget = "17"
    buildToolsVersion = "35.0.0 rc4"
    defaultConfig {
        minSdk = 19
    }
}
dependencies {
    implementation("androidx.annotation:annotation-jvm:1.8.0")
    implementation("androidx.core:core-ktx:1.13.1")
}
tasks.withType<AarMetadataTask> {
    isEnabled = false
}
tasks.configureEach {
    if (name.contains("checkDebugAndroidTestAarMetadata", ignoreCase = true)) {
        enabled = false
    }
}
publishing {
    repositories {
        maven {
            name = "localPluginRepository"
            val publishToLocal = true
            if (publishToLocal) {
                url = uri("D:\\repo\\")
            } else {
                url = uri("https://localhost:80")
                credentials {
                    username = "iamverycute"
                    password = System.getenv("my_final_password")
                }
            }
        }
    }
    publications {
        create<MavenPublication>("release") {
            val githubUrl = "https://github.com/lalakii/IceBox-SDK.kt"
            artifactId = "IceBox-SDK.kt"
            groupId = "cn.lalaki"
            afterEvaluate { artifact(tasks.named("bundleReleaseAar")) }
            pom.withXml {
                val dependenciesNode = asNode().appendNode("dependencies")
                configurations.implementation.get().dependencies.forEach { dependency ->
                    if (dependency.version != "unspecified" && dependency.name != "unspecified") {
                        val dependencyNode = dependenciesNode.appendNode("dependency")
                        dependencyNode.appendNode("groupId", dependency.group)
                        dependencyNode.appendNode("artifactId", dependency.name)
                        dependencyNode.appendNode("version", dependency.version)
                    } else {
                        println(">>> [WARN] Excluded module: " + dependency.group + ":" + dependency.name)
                    }
                }
            }
            pom {
                name = "IceBox-SDK.kt"
                description = "IceBox-SDK.kt."
                url = githubUrl
                licenses {
                }
                developers {
                    developer {
                        name = "lalakii"
                        email = "dazen@189.cn"
                    }
                }
                scm {
                    url = githubUrl
                    connection = "scm:git:$githubUrl.git"
                    developerConnection = "scm:git:$githubUrl.git"
                }
            }
        }
    }
}
