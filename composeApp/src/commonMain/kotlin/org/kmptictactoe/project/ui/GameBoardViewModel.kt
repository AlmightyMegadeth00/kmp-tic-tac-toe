package org.kmptictactoe.project.ui

import androidx.lifecycle.ViewModel
import org.kmptictactoe.project.Player
import org.kmptictactoe.project.utils.ContextUtils
import org.kmptictactoe.project.utils.LoggingUtils

class GameBoardViewModel(private val contextUtils: ContextUtils, private val loggingUtils: LoggingUtils): ViewModel() {
    companion object {
        private val TAG = GameBoardViewModel::class.simpleName
    }

    var currentPlayer = Player.PLAYER_ONE
    // [O, O, X, O, X, X, O, O, X]

    //TODO: make reactive when the logic works
    var moveIndexesRemaining = mutableSetOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
    // used for logging the output
    var completedMovesOutput = arrayOf("-", "-", "-", "-", "-", "-", "-", "-", "-")
    val playerOneMovesSet = mutableSetOf<Int>()
    val playerTwoMovesSet = mutableSetOf<Int>()

    fun generateCpuMove() {
        val currentPlayerMovesSet =
            if (currentPlayer == Player.PLAYER_ONE)
                playerOneMovesSet else playerTwoMovesSet
        val otherPlayerMovesSet =
            if (currentPlayer != Player.PLAYER_ONE)
                playerOneMovesSet else playerTwoMovesSet

        loggingUtils.printToLogInfo("current player = $currentPlayer")
    }

    private fun nextPlayer(): Player {
        val nextPlayer = if (currentPlayer == Player.PLAYER_ONE) Player.PLAYER_TWO else Player.PLAYER_ONE
        currentPlayer = nextPlayer
        return nextPlayer
    }

    private fun resetBoard() {
        completedMovesOutput = arrayOf("-", "-", "-", "-", "-", "-", "-", "-", "-")
        moveIndexesRemaining = mutableSetOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
        playerTwoMovesSet.clear()
        playerOneMovesSet.clear()
    }

}