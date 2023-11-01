package com.muammarahlnn.urflix

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file ProjectExtensions, 01/11/2023 22.58 by Muammar Ahlan Abimanyu
 */
val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")