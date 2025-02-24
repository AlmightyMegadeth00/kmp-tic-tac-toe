package org.kmptictactoe.project.utils

import com.diamondedge.logging.logging

class LoggingUtils {
    companion object {
        private val TAG = LoggingUtils::class.simpleName
        private val log = logging(TAG)
    }

    fun printToLogInfo(output: String) {
        log.i { output }
    }
}