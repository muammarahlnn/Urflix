plugins {
    id("urflix.android.feature")
    id("urflix.android.library.compose")
}

android {
    namespace = "com.muammarahlnn.urflix.feature.genres"
}

dependencies {
    implementation(libs.androidx.activity.compose)
}