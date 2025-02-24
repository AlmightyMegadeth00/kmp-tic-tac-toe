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

    /**
     * EASY: Computer will move using just random numbers remaining from moves list
     * MEDIUM: Computer will pick moves using random numbers, but complete the game when 2 in a row are found
     * HARD: Computer will favor moves that offer inherent advantages, play defensively if the player has
     * 2 cells in a row, and complete the game if it has 2 cells in a row
     */
    private var difficulty: Difficulty = Difficulty.HARD
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
                getWinningMoveIndexOrNext(null, moveIndexesRemaining, currentPlayerMovesSet)
            }
            Difficulty.HARD -> {
                getStrategicMoveIndexOrNext(moveIndexesRemaining, currentPlayerMovesSet, otherPlayerMovesSet)
            }

        }
    }

    /**
     * Consider moves that win the game, block opponent, favor moves that are part of more combinations
     * in order to press a strategic advantage
     */
    private fun getStrategicMoveIndexOrNext(
        moveIndexesRemaining: Set<Int>,
        currentPlayerMovesSet: Set<Int>,
        otherPlayerMovesSet: Set<Int>): Int {
        // generate default random move
        var nextMoveIndex = getRandomNextMoveIndex(moveIndexesRemaining)
        // check if a strategic move is available
        if (moveIndexesRemaining.contains(4)) {
            nextMoveIndex = moveIndexesRemaining.indexOf(4)
        } else {
            val basicStrategicMovesRemaining =
                (moveIndexesRemaining.filter { it % 2 == 0 }).toMutableSet()
            if (basicStrategicMovesRemaining.size > 0) {
                nextMoveIndex = basicStrategicMovesRemaining.elementAt(
                    generateRandomMoveIndex(basicStrategicMovesRemaining.size)
                )
            }
        }
        // check if the other player will win in the next turn
        nextMoveIndex = getWinningMoveIndexOrNext(nextMoveIndex, moveIndexesRemaining, otherPlayerMovesSet)
        // check if winning is possible, highest priority
        nextMoveIndex = getWinningMoveIndexOrNext(nextMoveIndex, moveIndexesRemaining, currentPlayerMovesSet)
        return nextMoveIndex
    }

    /**
     * Consider moves that win the game
     */
    private fun getWinningMoveIndexOrNext(nextMoveIndex: Int ?= null, moveIndexesRemaining: Set<Int>, playerMovesSet: Set<Int>): Int {
        var nextMoveIndexOutput = nextMoveIndex ?: run {
            getRandomNextMoveIndex(moveIndexesRemaining)
        }
        for (value in moveIndexesRemaining) {
            if (canWinWithNextTurn(value, playerMovesSet)) {
                nextMoveIndexOutput = value
                break
            }
        }
        return nextMoveIndexOutput
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
        val nextMoveIndex = generateRandomMoveIndex(movesIndexesRemaining.size)
        val mutableMovesRemainingSet = movesIndexesRemaining.toMutableSet()
        val nextMoveValue = mutableMovesRemainingSet.elementAt(nextMoveIndex)
        return nextMoveValue
    }

    /**
     * Generate a random move index with the supplied maxRange
     */
    private fun generateRandomMoveIndex(maxRange: Int): Int {
        return Random.nextInt(maxRange)
    }
}