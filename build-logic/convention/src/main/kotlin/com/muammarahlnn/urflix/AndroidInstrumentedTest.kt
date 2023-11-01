package com.muammarahlnn.urflix

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Project


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file AndroidInstrumentedTest, 01/11/2023 22.57 by Muammar Ahlan Abimanyu
 */
internal fun LibraryAndroidComponentsExtension.disableUnnecessaryAndroidTests(project: Project) {
    beforeVariants {
        it.enableAndroidTest = it.enableAndroidTest && project.projectDir.resolve("src/androidTests").exists()
    }
}