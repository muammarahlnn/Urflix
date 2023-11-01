import com.android.build.gradle.LibraryExtension
import com.muammarahlnn.urflix.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file AndroidLibraryComposeConventionPlugin, 01/11/2023 23.06 by Muammar Ahlan Abimanyu
 */
class AndroidLibraryComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
        }
    }
}