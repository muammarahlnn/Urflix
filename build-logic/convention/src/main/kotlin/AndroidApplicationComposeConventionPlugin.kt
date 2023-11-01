import com.android.build.api.dsl.ApplicationExtension
import com.muammarahlnn.urflix.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file AndroidApplicationComposeConventionPlugin, 01/11/2023 23.00 by Muammar Ahlan Abimanyu
 */
class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
        }
    }
}