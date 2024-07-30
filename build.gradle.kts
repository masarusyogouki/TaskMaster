plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17"
    id("com.google.dagger.hilt.android") version "2.50" apply false
}
