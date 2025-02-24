package org.kmptictactoe.project.utils

import org.kmptictactoe.project.Player

expect class ContextUtils {
    fun notify(player: Player?)
}