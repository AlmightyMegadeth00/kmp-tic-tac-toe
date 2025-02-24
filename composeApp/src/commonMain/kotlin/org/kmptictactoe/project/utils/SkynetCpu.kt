package org.kmptictactoe.project.utils

import org.kmptictactoe.project.Difficulty
import org.kmptictactoe.project.utils.ValidatorUtils.checkForWinner
import kotlin.random.Random

interface SkynetCpu {
    fun setDifficulty(difficulty: Difficulty)
    fun getNextMove(
        moveIndexesRemaining: Set<Int>,
        currentPlayerMovesSet: Set<Int>,
        otherPlayerMovesSet: Set<Int>): Int?
}

class SkynetCpuImpl: SkynetCpu {
    companion object {
        private val TAG = SkynetCpuImpl::class.simpleName
    }

    private var difficulty: Difficulty = Difficulty.MEDIUM

    /**
     * EASY: Computer will move using just random numbers remaining from moves list
     * MEDIUM: Computer will pick moves using random numbers, but complete the game when 2 in a row are found
     * HARD: Computer will favor moves that offer inherent advantages, play defensively if the player has
     * 2 cells in a row, and complete the game if it has 2 cells in a row
     */
    override fun setDifficulty(difficulty: Difficulty) {
        this.difficulty = difficulty
    }

    override fun getNextMove(
        moveIndexesRemaining: Set<Int>,
        currentPlayerMovesSet: Set<Int>,
        otherPlayerMovesSet: Set<Int>): Int? {
        if (moveIndexesRemaining.isEmpty()) return null
        return when (difficulty) {
            Difficulty.EASY -> {
                getRandomNextMoveIndex(moveIndexesRemaining)
            }
            Difficulty.MEDIUM -> {
                getWinningMoveIndexOrNext(moveIndexesRemaining, currentPlayerMovesSet)
            }
            Difficulty.HARD -> {
                0
            }

        }
    }

    /**
     * Consider moves that win the game
     */
    private fun getWinningMoveIndexOrNext(moveIndexesRemaining: Set<Int>, playerMovesArray: Set<Int>): Int {
        var nextMoveIndex = getRandomNextMoveIndex(moveIndexesRemaining)
        for (value in moveIndexesRemaining) {
            if (canWinWithNextTurn(value, playerMovesArray)) {
                nextMoveIndex = value
                break
            }
        }
        return nextMoveIndex
    }

    /**
     * Determine if next move will win
     */
    private fun canWinWithNextTurn(nextMove: Int, playerMovesSet: Set<Int>): Boolean {
        val exTreatment = playerMovesSet.toMutableSet()
        exTreatment.add(nextMove)
        return checkForWinner(exTreatment)
    }

    /**
     * Random selection of next move
     */
    private fun getRandomNextMoveIndex(movesIndexesRemaining: Set<Int>): Int {
        val nextMoveIndex = Random.nextInt(movesIndexesRemaining.size)
        val mutableMovesRemainingSet = movesIndexesRemaining.toMutableSet()
        val nextMoveValue = mutableMovesRemainingSet.elementAt(nextMoveIndex)
        return nextMoveValue
    }
}