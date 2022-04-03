import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "fr.o80.plugin"
version = "1.0-SNAPSHOT"

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.1.2")
}

gradlePlugin {
    plugins {
        create("Configurator") {
            id = "fr.o80.plugin.configurator"
            implementationClass = "fr.o80.plugin.Configurator"
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}