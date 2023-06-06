val bds100MavenUsername: String by project
val bds100MavenPassword: String by project

plugins {
    kotlin("multiplatform") version "1.8.21"
    kotlin("plugin.serialization") version "1.8.21"
    id("maven-publish")
}

group = "com.github.D10NGYANG"
version = "0.5.1"

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
                val kotlinCoroutinesVer = "1.7.1"
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVer")
                // 网络请求封装库
                implementation("com.github.D10NGYANG:DLHttpUtil:0.8.2")
                // JSON序列化
                val kotlinSerializationJsonVer = "1.5.1"
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