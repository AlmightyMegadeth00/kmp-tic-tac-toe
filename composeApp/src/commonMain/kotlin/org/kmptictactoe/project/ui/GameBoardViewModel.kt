package org.kmptictactoe.project.ui

import androidx.lifecycle.ViewModel
import org.kmptictactoe.project.utils.ContextUtil

class GameBoardViewModel(private val contextUtil: ContextUtil): ViewModel() {
    companion object {
        private val TAG = GameBoardViewModel::class.simpleName
    }

}