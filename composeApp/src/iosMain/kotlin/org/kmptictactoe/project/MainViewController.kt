package org.kmptictactoe.project

import androidx.compose.ui.window.ComposeUIViewController
import org.kmptictactoe.project.dependency_injection.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}