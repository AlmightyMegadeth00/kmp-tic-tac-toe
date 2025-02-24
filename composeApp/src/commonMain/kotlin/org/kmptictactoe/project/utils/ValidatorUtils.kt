package org.kmptictactoe.project.utils

import org.kmptictactoe.project.hasCompleteIntersect

object ValidatorUtils {
    private val TAG = ValidatorUtils::class.simpleName

    private val horizontalWinningCombos: List<List<Int>> = listOf(
        listOf(0, 1, 2),
        listOf(3, 4, 5),
        listOf(6, 7, 8))
    private val verticalWinningCombos: List<List<Int>> = listOf(
        listOf(0, 3, 6),
        listOf(1, 4, 7),
        listOf(2, 5, 8)
    )
    private val diagonalWinningCombos: List<List<Int>> = listOf(
        listOf(0, 4, 8),
        listOf(2, 4, 6)
    )

    fun checkForWinner(completedMovesSet: Set<Int>): Boolean {
        var isWinner = false
        for (completedMove in completedMovesSet) {
            isWinner = horizontalWinningCombos.hasCompleteIntersect(completedMovesSet)
                    || diagonalWinningCombos.hasCompleteIntersect(completedMovesSet)
                    || verticalWinningCombos.hasCompleteIntersect(completedMovesSet)
            if (isWinner) break
        }
        return isWinner
    }

}