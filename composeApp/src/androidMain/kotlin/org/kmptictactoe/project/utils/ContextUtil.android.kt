package org.kmptictactoe.project.utils

import android.content.Context
import android.widget.Toast
import org.kmptictactoe.project.Player

actual class ContextUtils(
    private val context: Context
) {
    actual fun notify(player: Player?) {
        val outputText = player?.let {
            "Player ${it.value} has won"
        } ?: run {
            "The game was a draw!"
        }
        Toast.makeText(context, outputText, Toast.LENGTH_SHORT).show()
    }
}