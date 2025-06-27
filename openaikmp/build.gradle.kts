import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "2.1.21"
    id("maven-publish")
}

group = "io.github.alderfurtado"
version = "1.0.0"


kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }
    jvm()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "openaikmp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.kmp.ktor.core)
            implementation(libs.kmp.ktor.cio)
            implementation(libs.kmp.ktor.content.negotiation)
            implementation(libs.kmp.ktor.serialization)
            implementation(libs.kotlin.serialization)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kmp.ktor.core)
            implementation(libs.kmp.ktor.cio)
            implementation(libs.kmp.ktor.content.negotiation)
            implementation(libs.kmp.ktor.serialization)
            implementation(libs.kotlin.serialization)
        }
        iosMain.dependencies {
            implementation(libs.kmp.ktor.darwin)
        }
    }
}

android {
    namespace = "com.github.alder.furtado.openaikmp"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
