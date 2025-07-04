import org.jetbrains.kotlin.gradle.dsl.JvmTarget

import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "2.1.21"
    id("maven-publish")
    id("com.vanniktech.maven.publish") version "0.30.0"
}

group = "io.github.alderfurtado"
version = "1.0.1"


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

mavenPublishing {
    coordinates(
        groupId = "io.github.alderfurtado",
        artifactId = "openaikmp",
        version = "1.0.1"
    )

    // Configure POM metadata for the published artifact
    pom {
        name.set("OpenAi KMP")
        description.set("OpenAi KMP is a lightweight library that streamlines integration with the OpenAI API using a simple and developer-friendly approach.")
        inceptionYear.set("2025")
        url.set("https://github.com/AlderFurtado/openai-kmp")

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        // Specify developers information
        developers {
            developer {
                id.set("alder-furtado")
                name.set("Alder Furtado")
                url.set("https://github.com/AlderFurtado")
            }
        }

        // Specify SCM information
        scm {
            url.set("https://github.com/AlderFurtado/openai-kmp")
            connection = "https://github.com/AlderFurtado/openai-kmp"
            developerConnection = "https://github.com/AlderFurtado/openai-kmp"
        }
    }
    // Configure publishing to Maven Central
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    // Enable GPG signing for all publications
    signAllPublications()
}
