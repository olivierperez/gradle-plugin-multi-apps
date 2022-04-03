# Gradle-plugin for multi-apps projects

This repository is about a [Gradle plugin](gradle-plugin-configurator/src/main/java/fr/o80/plugin/Configurator.kt) used to declare several applications as Gradle modules, see:
- [Prokedex Red app](apps/red/build.gradle)
- [Prokedex Yellow app](apps/red/build.gradle)

```groovy
apply plugin: "fr.o80.plugin.configurator"

pokeApp {
    namespace.set("<YOUR NAMESPACE>")
    applicationId.set("<YOUR APPLICATION ID>")
}

dependencies {
    implementation project(":lib")
}

// and that's it
```