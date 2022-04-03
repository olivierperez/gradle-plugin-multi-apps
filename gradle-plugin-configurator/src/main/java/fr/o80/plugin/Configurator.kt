package fr.o80.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.dependencies
import java.io.File

class Configurator : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create<PokemonPluginExtension>("pokeApp")
        project.afterEvaluate {
            val android = project.extensions.findByType(AppExtension::class.java)
                ?: error("Can't find Android plugin")

            android.apply {
                namespace = extension.namespace.get()
                defaultConfig {
                    applicationId = extension.applicationId.get()
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    compileSdkVersion = "android-31"
                    minSdk = 21
                    targetSdk = 32
                    versionCode = 1
                    versionName = "1.0"
                }
                buildTypes {
                    getByName("release") {
                        minifyEnabled(true)
                        proguardFiles = mutableListOf(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            File("proguard-rules.pro")
                        )
                    }
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }
            }

            val hiltVersion = "2.41"
            val kotlinVersion = "1.6.10"
            project.dependencies {
                add("implementation", "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
                add("implementation", "com.google.dagger:hilt-android:$hiltVersion")
                add("kapt", "com.google.dagger:hilt-compiler:$hiltVersion")

                add("testImplementation", "junit:junit:4.13.2")
                add("androidTestImplementation", "androidx.test.ext:junit:1.1.3")
                add("androidTestImplementation", "androidx.test.espresso:espresso-core:3.4.0")
            }
        }

        project.pluginManager.apply(AppPlugin::class.java)
        project.pluginManager.apply("kotlin-android")
        project.pluginManager.apply("kotlin-kapt")
        project.pluginManager.apply("dagger.hilt.android.plugin")
    }
}
