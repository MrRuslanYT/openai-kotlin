import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.kotlin.gradle.targets.js.testing.KotlinJsTest
import org.jetbrains.kotlin.gradle.targets.jvm.tasks.KotlinJvmTest
import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeTest

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.multiplaform) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.kotlinx.binary.validator) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.dokka)
    id ("maven-publish")
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    configure<SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            trimTrailingWhitespace()
            endWithNewline()
        }
    }
    tasks.configureEach {
        if (name == "lint") {
            enabled = false
        }
        if (name.contains("Test")) {
            enabled = false
        }
    }
}

tasks.withType<DokkaMultiModuleTask>() {
    outputDirectory.set(projectDir.resolve("docs"))
}

tasks.configureEach {
    if (name == "lint") {
        enabled = false
    }
    if (name.contains("Test")) {
        enabled = false
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.pandacorp"
            artifactId = "openai-kotlin"
        }
    }
}