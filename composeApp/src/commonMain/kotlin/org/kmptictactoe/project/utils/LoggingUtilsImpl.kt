package org.kmptictactoe.project.utils

import com.diamondedge.logging.logging
import org.kmptictactoe.project.Player

interface LoggingUtils {
    fun printToLogInfo(output: String)
    fun printBoardToLog(currentBoardOutputArray: Array<String>)
}

class LoggingUtilsImpl: LoggingUtils {
    companion object {
        private val TAG = LoggingUtilsImpl::class.simpleName
        private val log = logging(TAG)
    }

    override fun printToLogInfo(output: String) {
        log.i { output }
    }

    /**
     * Prints the current board line-by-line as:
     * | O | - | - |
     * | - | - | O |
     * | - | - | X |
     */
    override fun printBoardToLog(currentBoardOutputArray: Array<String>) {
        val stringBuilder = StringBuilder()
        val lineOne = "| ${currentBoardOutputArray[0]} | ${currentBoardOutputArray[1]} | ${currentBoardOutputArray[2]} |"
        val lineTwo = "| ${currentBoardOutputArray[3]} | ${currentBoardOutputArray[4]} | ${currentBoardOutputArray[5]} |"
        val lineThree = "| ${currentBoardOutputArray[6]} | ${currentBoardOutputArray[7]} | ${currentBoardOutputArray[8]} |"
        stringBuilder.append(lineOne)
        stringBuilder.append("\n")
        stringBuilder.append(lineTwo)
        stringBuilder.append("\n")
        stringBuilder.append(lineThree)
        log.i { stringBuilder.toString() }
    }
}