package org.kmptictactoe.project.dependency_injection

import org.kmptictactoe.project.ui.GameBoardViewModel
import org.kmptictactoe.project.utils.ContextUtils
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::ContextUtils)
    viewModelOf(::GameBoardViewModel)
}