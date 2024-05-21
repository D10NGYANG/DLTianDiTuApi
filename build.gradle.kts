val bds100MavenUsername: String by project
val bds100MavenPassword: String by project

plugins {
    kotlin("multiplatform") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.23"
    id("maven-publish")
    id("com.github.ben-manes.versions") version "0.51.0"
}

group = "com.github.D10NGYANG"
version = "1.0.1"

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
        val kotlinKtorVer = "2.3.11"
        val kotlinSerializationJsonVer = "1.6.3"
        val kotlinCoroutinesVer = "1.8.1"
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                // 协程
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVer")
                // JSON序列化
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationJsonVer")
                // ktor客户端
                implementation("io.ktor:ktor-client-core:$kotlinKtorVer")
                implementation("io.ktor:ktor-client-cio:$kotlinKtorVer")
                implementation("io.ktor:ktor-client-logging:$kotlinKtorVer")
                implementation("io.ktor:ktor-client-content-negotiation:$kotlinKtorVer")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$kotlinKtorVer")
                // 网络请求封装库
                implementation("com.github.D10NGYANG:DLHttpUtil:1.0.1")
                // 通用工具
                implementation("com.github.D10NGYANG:DLCommonUtil:0.1.2")
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