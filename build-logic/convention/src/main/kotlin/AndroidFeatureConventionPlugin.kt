import com.flavorfusion.gradleplugins.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "flavorfusion.android.library")
            apply(plugin = "flavorfusion.android.hilt")

            dependencies {
                add("api", project(":common:common-ui"))
                add("implementation", project(":common:common-domain"))

                add("implementation", libs.findLibrary("androidx.core.ktx").get())
                add("implementation", libs.findLibrary("appcompat").get())
                add("testImplementation", libs.findLibrary("junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx.junit.ext").get())
                add("androidTestImplementation", libs.findLibrary("androidx.espresso.core").get())
                add("androidTestImplementation", libs.findLibrary("kotlin.coroutines.android").get()
                )
            }
        }
    }
}