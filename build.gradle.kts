val bds100MavenUsername: String by project
val bds100MavenPassword: String by project
val ktorVersion = "3.2.2"

plugins {
    kotlin("multiplatform") version "2.2.0"
    kotlin("plugin.serialization") version "2.2.0"
    id("maven-publish")
    id("com.github.ben-manes.versions") version "0.52.0"
}

group = "com.github.D10NGYANG"
version = "1.2.0"

repositories {
    google {
        mavenContent {
            includeGroupAndSubgroups("androidx")
            includeGroupAndSubgroups("com.android")
            includeGroupAndSubgroups("com.google")
        }
    }
    mavenCentral()
    maven("https://raw.githubusercontent.com/D10NGYANG/maven-repo/main/repository")
}

kotlin {
    jvmToolchain(8)
    jvm {
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js(IR) { nodejs() }
    iosArm64()
    iosSimulatorArm64()
    iosX64()
    macosArm64()
    macosX64()
    linuxX64()
    linuxArm64()

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.js.ExperimentalJsExport")
            }
        }
        commonMain {
            dependencies {
                // serialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
                // ktor客户端
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        jvmTest {
            dependencies {
                // 协程
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
                // ktor客户端
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
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

tasks.withType<Test> {
    systemProperty("myTiandituToken", project.findProperty("myTiandituToken") ?: "")
}