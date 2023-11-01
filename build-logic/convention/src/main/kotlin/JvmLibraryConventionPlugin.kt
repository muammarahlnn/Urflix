import com.muammarahlnn.urflix.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file JvmLibraryConventionPlugin, 01/11/2023 23.08 by Muammar Ahlan Abimanyu
 */
class JvmLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
            }
            configureKotlinJvm()
        }
    }
}