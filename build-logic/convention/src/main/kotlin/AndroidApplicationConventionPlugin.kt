import com.android.build.api.dsl.ApplicationExtension
import com.muammarahlnn.urflix.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file AndroidApplicationConventionPlugin, 01/11/2023 23.04 by Muammar Ahlan Abimanyu
 */
class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with (pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
            }
        }
    }
}