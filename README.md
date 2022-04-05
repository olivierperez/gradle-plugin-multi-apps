# Gradle-plugin for multi-apps projects

## History

I worked on a white label projects containing a bunch of libraries that are used my several application modules.

Each application's `build.gradle` contains a lot a similar information (plugins,  dependencies, tasks, and so on).
The first approach was to extract things into a `common.gradle` file but it started showing weaknesses when:
- we needed to make a difference between apps for TV and Mobile (`common-tv.gradle` + `common-mobile.gradle`)
- we needed to deliver multiple applications to a given customer (`common-customer-A.gradle` + `common-customer-B.gradle`)

## What is this open-source project?

This a Proof Of Concept to see how it could be possible to factorize the application configurations in
a single source of truth, and still be able to customize it regarding customers needs.

This project is a white label pokedex that can be delivered as **Red pokedex** ou **Yellow pokedex**.
Let see the result in applications `build.gradle` files:
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

## How?

This magic is handled by a [Gradle plugin](gradle-plugin-configurator/src/main/java/fr/o80/plugin/Configurator.kt)
that is responsible to describe the Android application on our behalf.