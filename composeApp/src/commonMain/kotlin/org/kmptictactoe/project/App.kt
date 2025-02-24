package org.kmptictactoe.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diamondedge.logging.logging
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.launch
import org.kmptictactoe.project.ui.GameBoardViewModel
import org.kmptictactoe.project.utils.LoggingUtils
import org.kmptictactoe.project.utils.ValidatorUtils
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    val loggingUtils = koinInject<LoggingUtils>()
    val viewModel = koinViewModel<GameBoardViewModel>()

    (CoroutineScope(Dispatchers.IO)).launch {
        viewModel.completedMovesOutputStateFlow.collectLatest {
            loggingUtils.printToLogInfo("collected")
            loggingUtils.printBoardToLog(it)
        }
    }
    MaterialTheme {
        KoinContext {

            NavHost(
                navController = rememberNavController(),
                startDestination = "home"
            ) {
                composable(route = "home") {
                    Column(
                        Modifier.fillMaxSize().padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            viewModel.generateCpuMove()
                        }, Modifier.padding(10.dp)) {
                            Text(
                                text = "generate turn",
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        Button(onClick = {
                            viewModel.resetBoard()
                        }, Modifier.padding(10.dp)) {
                            Text(
                                text = "reset",
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                }
            }

        }
    }
}