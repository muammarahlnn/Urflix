plugins {
    id("urflix.android.library")
    id("urflix.android.hilt")
    id("kotlinx-serialization")
}

val BASE_URL: String by project
val IMG_URL: String by project
val API_KEY: String by project

android {
    namespace = "com.muammarahlnn.urflix.core.network"
    buildFeatures {
        buildConfig = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    defaultConfig {
        buildConfigField("String", "BASE_URL", BASE_URL)
        buildConfigField("String", "IMG_URL", IMG_URL)
        buildConfigField("String", "API_KEY", API_KEY)
    }
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}