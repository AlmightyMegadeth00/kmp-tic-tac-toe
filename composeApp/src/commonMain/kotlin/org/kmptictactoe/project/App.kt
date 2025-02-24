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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
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
    MaterialTheme {
        KoinContext {
            val loggingUtils = koinInject<LoggingUtils>()
            NavHost(
                navController = rememberNavController(),
                startDestination = "home"
            ) {
                composable(route = "home") {
                    val viewModel = koinViewModel<GameBoardViewModel>()
                    Column(
                        Modifier.fillMaxWidth().padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            loggingUtils.printToLogInfo(
                                "current player is: ${viewModel.currentPlayer}")
                            loggingUtils.printBoardToLog(
                                arrayOf(
                                    "-",
                                    "-",
                                    "-",
                                    "-",
                                    "-",
                                    "-",
                                    "-",
                                    "-",
                                    "-"
                                )
                            )

                        }, Modifier.padding(10.dp)) {
                            Text(
                                text = "generate turn",
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}