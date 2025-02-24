package org.kmptictactoe.project.ui

import androidx.lifecycle.ViewModel
import org.kmptictactoe.project.utils.ContextUtils

class GameBoardViewModel(private val contextUtils: ContextUtils): ViewModel() {
    companion object {
        private val TAG = GameBoardViewModel::class.simpleName
    }

}