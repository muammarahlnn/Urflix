plugins {
    id("urflix.android.library")
    id("urflix.android.hilt")
}

android {
    namespace = "com.muammarahlnn.urflix.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}