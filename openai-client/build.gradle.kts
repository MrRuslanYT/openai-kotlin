plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id ("maven-publish")
    id("binary-compatibility-validator")
    id("com.diffplug.spotless")
    id("org.jetbrains.dokka")
    id("build-support")
}

kotlin {
    explicitApi()
    jvm()
    jsNode()
    native()

    sourceSets {
        all {
            languageSettings {
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                optIn("okio.ExperimentalFileSystem")
                optIn("com.aallam.openai.api.ExperimentalOpenAI")
                optIn("com.aallam.openai.api.BetaOpenAI")
                optIn("com.aallam.openai.api.InternalOpenAI")
                optIn("com.aallam.openai.api.LegacyOpenAI")
            }
        }
        val commonMain by getting {
            dependencies {
                api(projects.openaiCore)
                api(libs.coroutines.core)
                api(libs.okio)
                implementation(libs.serialization.json)
                api(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.auth)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.serialization.json)
            }
        }
        val jvmMain by getting

        val jsMain by getting {
            dependencies {
                implementation(libs.okio.nodefilesystem)
            }
        }
    }
}
