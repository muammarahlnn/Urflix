plugins {
    id("urflix.android.feature")
    id("urflix.android.library.compose")
}

android {
    namespace = "com.muammarahlnn.urflix.feature.home"
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material)
}