package org.kmptictactoe.project

/**
 * Enum class to represent difficulty levels
 * EASY: Computer will move using just random numbers remaining from moves list
 * MEDIUM: Computer will pick moves using random numbers, but complete the game when 2 in a row are found
 * HARD: Computer will favor moves that offer inherent advantages, play defensively if the player has
 * 2 cells in a row, and complete the game if it has 2 cells in a row
 */
enum class Difficulty {
    EASY,
    MEDIUM,
    HARD
}