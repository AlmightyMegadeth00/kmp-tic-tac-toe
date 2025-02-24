package org.kmptictactoe.project.ui

import androidx.lifecycle.ViewModel
import org.kmptictactoe.project.Player
import org.kmptictactoe.project.utils.ContextUtils

class GameBoardViewModel(private val contextUtils: ContextUtils): ViewModel() {
    companion object {
        private val TAG = GameBoardViewModel::class.simpleName
    }

    var currentPlayer = Player.PLAYER_ONE
    private fun nextPlayer(): Player {
        val nextPlayer = if (currentPlayer == Player.PLAYER_ONE) Player.PLAYER_TWO else Player.PLAYER_ONE
        currentPlayer = nextPlayer
        return nextPlayer
    }

}