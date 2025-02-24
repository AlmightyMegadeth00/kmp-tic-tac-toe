package org.kmptictactoe.project.utils

import org.kmptictactoe.project.Difficulty

class SkynetCPU {
    companion object {
        private val TAG = SkynetCPU::class.simpleName
    }

    private var difficulty: Difficulty = Difficulty.HARD

    fun setDifficulty(difficulty: Difficulty) {
        this.difficulty = difficulty
    }

    // TODO: split this logic into internal methods for difficulty and supply parameters
    //  between the viewmodel and ROOM storage
    fun getNextMove(
        moveIndexesRemaining: Set<Int>,
        currentPlayerMovesSet: Set<Int>,
        otherPlayerMovesSet: Set<Int>): Int? {
        if (moveIndexesRemaining.isEmpty()) return null
        return when (difficulty) {
            Difficulty.EASY -> {
                0
            }
            Difficulty.MEDIUM -> {
                0
            }
            Difficulty.HARD -> {
                0
            }

        }
    }
}