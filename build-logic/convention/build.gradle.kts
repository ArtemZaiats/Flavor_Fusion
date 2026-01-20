plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose")  {
            id = libs.plugins.common.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidApplication") {
            id = libs.plugins.common.android.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidHilt") {
            id = libs.plugins.common.android.hilt.get().pluginId
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidLibrary") {
            id = libs.plugins.common.android.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidFeature") {
            id = libs.plugins.common.android.feature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = libs.plugins.common.android.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("jvmLibrary") {
            id = libs.plugins.common.jvm.library.get().pluginId
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}