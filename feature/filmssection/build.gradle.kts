plugins {
    id("urflix.android.feature")
    id("urflix.android.library.compose")
}

android {
    namespace = "com.muammarahlnn.urflix.feature.filmssection"
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material)
}