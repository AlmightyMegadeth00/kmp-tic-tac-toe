package org.kmptictactoe.project.utils

import com.diamondedge.logging.logging

interface LoggingUtils {
    fun printToLogInfo(output: String)
}

class LoggingUtilsImpl: LoggingUtils {
    companion object {
        private val TAG = LoggingUtilsImpl::class.simpleName
        private val log = logging(TAG)
    }

    override fun printToLogInfo(output: String) {
        log.i { output }
    }
}