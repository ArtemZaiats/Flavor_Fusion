plugins {
    alias(libs.plugins.common.jvm.library)
}

dependencies {
    implementation(libs.kotlin.coroutines.android)
    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}
