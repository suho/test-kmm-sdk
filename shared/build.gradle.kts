plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("maven-publish")
}

group = "co.nimblehq.kmmsdk"
version = "0.1.0"

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        publishLibraryVariants("release", "debug")
        publishLibraryVariantsGroupedByFlavor = true
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "Shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "co.nimblehq.kmmsdk"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

publishing {
    repositories {
        maven {
            setUrl("https://maven.pkg.github.com/suho/test-kmm-sdk/")
            credentials {
                username = (System.getenv("GITHUB_USER") ?: project.properties["GITHUB_USER"])?.toString()
                password = (System.getenv("GITHUB_TOKEN") ?: project.properties["GITHUB_TOKEN"])?.toString()
            }
        }
    }
}
