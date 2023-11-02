plugins {
    id("urflix.android.feature")
    id("urflix.android.library.compose")
}

android {
    namespace = "com.muammarahlnn.urflix.feature.search"
}

dependencies {
    implementation(libs.androidx.activity.compose)
}