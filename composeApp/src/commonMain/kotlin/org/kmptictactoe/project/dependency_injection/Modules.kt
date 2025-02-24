package org.kmptictactoe.project.dependency_injection

import org.kmptictactoe.project.utils.LoggingUtils
import org.kmptictactoe.project.utils.LoggingUtilsImpl
import org.kmptictactoe.project.utils.PlatformUtils
import org.kmptictactoe.project.utils.PlatformUtilsImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    singleOf(::PlatformUtilsImpl).bind<PlatformUtils>()
    singleOf(::LoggingUtilsImpl).bind<LoggingUtils>()
}