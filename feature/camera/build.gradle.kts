plugins {
    id("urflix.android.feature")
    id("urflix.android.library.compose")
}

android {
    namespace = "com.muammarahlnn.urflix.feature.camera"
}

dependencies {

    implementation(libs.androidx.activity.compose)
    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.extensions)
}