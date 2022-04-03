package fr.o80.plugin

import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input

interface PokemonPluginExtension {
    @get:Input
    val applicationId: Property<String>
    @get:Input
    val namespace: Property<String>
}
