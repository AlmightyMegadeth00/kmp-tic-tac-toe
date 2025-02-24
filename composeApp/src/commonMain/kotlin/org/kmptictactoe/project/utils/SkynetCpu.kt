package org.kmptictactoe.project.utils

import org.kmptictactoe.project.Difficulty
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

    private var difficulty: Difficulty = Difficulty.EASY

    override fun setDifficulty(difficulty: Difficulty) {
        this.difficulty = difficulty
    }

    // TODO: split this logic into internal methods for difficulty and supply parameters
    //  between the viewmodel and ROOM storage
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
                0
            }
            Difficulty.HARD -> {
                0
            }

        }
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