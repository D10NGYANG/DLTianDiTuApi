plugins {
    kotlin("multiplatform") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    id("maven-publish")
}

group = "com.github.D10NGYANG"
version = "0.2"

repositories {
    mavenCentral()
    maven("https://jitpack.io" )
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                // 协程
                val kotlin_coroutines_ver = "1.6.1"
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_coroutines_ver")
                // 网络请求封装库
                implementation("com.github.D10NGYANG:DLHttpUtil:0.3")
                // JSON序列化
                val kotlin_serialization_json = "1.3.2"
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlin_serialization_json")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}