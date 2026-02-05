plugins {
    alias(libs.plugins.common.android.library)
    alias(libs.plugins.common.android.hilt)
}

android {
    namespace = "com.flavorfusion.core_data"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit.ext)
}
