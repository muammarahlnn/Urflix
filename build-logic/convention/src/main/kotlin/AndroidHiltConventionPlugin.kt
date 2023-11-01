import com.muammarahlnn.urflix.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file AndroidHiltConventionPlugin, 01/11/2023 23.06 by Muammar Ahlan Abimanyu
 */
class AndroidHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
                apply("org.jetbrains.kotlin.kapt")
            }

            dependencies {
                "implementation"(libs.findLibrary("hilt.android").get())
                "kapt"(libs.findLibrary("hilt.compiler").get())
                "kaptAndroidTest"(libs.findLibrary("hilt.compiler").get())
            }
        }
    }
}