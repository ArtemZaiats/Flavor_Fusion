import com.android.build.api.dsl.ApplicationExtension
import com.flavorfusion.gradleplugins.configureKotlinAndroid
import com.flavorfusion.gradleplugins.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureDefaultConfig()
            }
        }
    }
}

private fun ApplicationExtension.configureDefaultConfig() {
    defaultConfig {
        applicationId = "com.flavorfusion.flavorfusion"
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }
}