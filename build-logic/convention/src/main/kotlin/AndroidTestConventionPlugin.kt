import com.android.build.gradle.TestExtension
import com.muammarahlnn.urflix.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file AndroidTestConventionPlugin, 01/11/2023 23.08 by Muammar Ahlan Abimanyu
 */
class AndroidTestConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.test")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<TestExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 31
            }
        }
    }
}