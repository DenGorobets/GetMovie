buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-serialization:2.1.0-RC2")
    }
}
plugins {
    id("com.android.application") version "8.7.2" apply false
    id("org.jetbrains.kotlin.android") version "2.1.0-RC2" apply false
    id("com.google.devtools.ksp") version "2.1.0-RC2-1.0.28" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0-RC2" apply false
}