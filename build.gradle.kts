// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    kotlin("kapt") version "1.9.22" apply false
    id("androidx.navigation.safeargs") version "2.5.3" apply false
    id("org.jetbrains.kotlin.plugin.parcelize") version "2.0.0-Beta3" apply false
}