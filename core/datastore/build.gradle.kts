plugins {
    id("urflix.android.library")
    id("urflix.android.hilt")
}

android {
    namespace = "com.muammarahlnn.urflix.core.datastore"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlinx.coroutines.android)
}
