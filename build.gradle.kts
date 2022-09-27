// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application").version("7.3.0") apply false
    id("com.android.library").version("7.3.0") apply false
    id("org.jetbrains.kotlin.jvm").version("1.7.10") apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}