plugins {
    id("urflix.android.library")
    id("urflix.android.hilt")
    id("urflix.android.room")
}

android {
    namespace = "com.muammarahlnn.urflix.core.database"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}
