buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.10")
    }
}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
}