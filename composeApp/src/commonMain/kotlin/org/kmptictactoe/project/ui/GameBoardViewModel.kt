package org.kmptictactoe.project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.kmptictactoe.project.Player
import org.kmptictactoe.project.utils.ContextUtils
import org.kmptictactoe.project.utils.LoggingUtils
import org.kmptictactoe.project.utils.SkynetCpu
import org.kmptictactoe.project.utils.ValidatorUtils

class GameBoardViewModel(private val contextUtils: ContextUtils,
                         private val loggingUtils: LoggingUtils,
                         private val skynetCpu: SkynetCpu): ViewModel() {
    companion object {
        private val TAG = GameBoardViewModel::class.simpleName
    }

    private var currentPlayer = Player.PLAYER_ONE

    private val _completedMovesOutput = MutableStateFlow(arrayOf("-", "-", "-", "-", "-", "-", "-", "-", "-"))
    var completedMovesOutputStateFlow = _completedMovesOutput.asStateFlow()

    //TODO: make reactive when the logic works
    private var moveIndexesRemaining = mutableSetOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
    // used for logging the output
    private var completedMovesOutput = arrayOf("-", "-", "-", "-", "-", "-", "-", "-", "-")
    private val playerOneMovesSet = mutableSetOf<Int>()
    private val playerTwoMovesSet = mutableSetOf<Int>()

    fun generateCpuMove() {
        val currentPlayerMovesSet =
            if (currentPlayer == Player.PLAYER_ONE)
                playerOneMovesSet else playerTwoMovesSet
        val otherPlayerMovesSet =
            if (currentPlayer != Player.PLAYER_ONE)
                playerOneMovesSet else playerTwoMovesSet

        val nextMove = skynetCpu.getNextMove(moveIndexesRemaining, currentPlayerMovesSet, otherPlayerMovesSet)
        if (nextMove != null) {
            moveIndexesRemaining.remove(nextMove)
            completedMovesOutput = getBoardMoveOutputAsList(
                currentPlayer,
                nextMove,
                completedMovesOutput
            )
            viewModelScope.launch {
                loggingUtils.printToLogInfo("setting new completed moves set")
                _completedMovesOutput.emit(completedMovesOutput)
            }.start()

            if (currentPlayer == Player.PLAYER_ONE)
                playerOneMovesSet.add(nextMove)
            else
                playerTwoMovesSet.add(nextMove)

            if (ValidatorUtils.checkForWinner(currentPlayerMovesSet)) {
                contextUtils.notify(currentPlayer)
                resetBoard()
                return
            }
            nextPlayer()
        } else {
            // draw game
            contextUtils.notify(null)
            resetBoard()
        }
    }

    fun resetBoard() {
        completedMovesOutput = arrayOf("-", "-", "-", "-", "-", "-", "-", "-", "-")
        moveIndexesRemaining = mutableSetOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
        playerTwoMovesSet.clear()
        playerOneMovesSet.clear()
        _completedMovesOutput.value = completedMovesOutput
        currentPlayer = Player.PLAYER_ONE
    }

    private fun nextPlayer(): Player {
        val nextPlayer = if (currentPlayer == Player.PLAYER_ONE) Player.PLAYER_TWO else Player.PLAYER_ONE
        currentPlayer = nextPlayer
        return nextPlayer
    }

    private fun getBoardMoveOutputAsList(player: Player, nextMoveIndex: Int, currentBoardOutputArray: Array<String>): Array<String> {
        if (currentBoardOutputArray[nextMoveIndex] != "-") return currentBoardOutputArray
        currentBoardOutputArray[nextMoveIndex] = player.value
        loggingUtils.printToLogInfo("Player ${player.value} sets board to ${currentBoardOutputArray.contentToString()}")
        return currentBoardOutputArray
    }

}
