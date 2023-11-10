val bds100MavenUsername: String by project
val bds100MavenPassword: String by project

plugins {
    kotlin("multiplatform") version "1.9.20"
    kotlin("plugin.serialization") version "1.9.20"
    id("maven-publish")
    id("com.github.ben-manes.versions") version "0.49.0"
}

group = "com.github.D10NGYANG"
version = "0.5.5"

repositories {
    mavenCentral()
    maven("https://raw.githubusercontent.com/D10NGYANG/maven-repo/main/repository")
}

kotlin {
    jvm {
        jvmToolchain(8)
        withJava()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                // 协程
                val kotlinCoroutinesVer = "1.7.3"
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVer")
                // 网络请求封装库
                implementation("com.github.D10NGYANG:DLHttpUtil:0.9.0")
                // JSON序列化
                val kotlinSerializationJsonVer = "1.6.0"
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationJsonVer")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

publishing {
    repositories {
        maven {
            url = uri("/Users/d10ng/project/kotlin/maven-repo/repository")
        }
        maven {
            credentials {
                username = bds100MavenUsername
                password = bds100MavenPassword
            }
            setUrl("https://nexus.bds100.com/repository/maven-releases/")
        }
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}