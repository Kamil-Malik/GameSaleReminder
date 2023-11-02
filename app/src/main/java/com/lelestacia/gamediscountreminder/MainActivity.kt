package com.lelestacia.gamediscountreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.lelestacia.gamediscountreminder.data.source.remote.dto.deal.DealDto
import com.lelestacia.gamediscountreminder.domain.state_and_event.DealScreenState
import com.lelestacia.gamediscountreminder.domain.viewmodel.DealsViewModel
import com.lelestacia.gamediscountreminder.domain.viewmodel.LoadingViewModel
import com.lelestacia.gamediscountreminder.ui.screen.CustomLoadingScreen
import com.lelestacia.gamediscountreminder.ui.screen.deal.GameDealScreen
import com.lelestacia.gamediscountreminder.ui.theme.GameSaleReminderTheme
import com.lelestacia.gamediscountreminder.util.DataState
import com.lelestacia.gamediscountreminder.util.Destination
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GameSaleReminderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val scope = rememberCoroutineScope()
                    val navController: NavHostController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Destination.LoadingScreen.route
                    ) {

                        composable(Destination.LoadingScreen.route) {

                            val vm: LoadingViewModel = hiltViewModel<LoadingViewModel>()

                            LaunchedEffect(key1 = Unit) {
                                vm.setupStore()
                                vm.setupStore.onEach { result ->
                                    when (result) {
                                        DataState.None -> Unit
                                        is DataState.Failed -> Unit
                                        DataState.Loading -> Unit
                                        is DataState.Success -> {
                                            navController.navigate(Destination.GameDealScreen.route) {
                                                popUpTo(Destination.LoadingScreen.route) {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    }
                                }.launchIn(lifecycleScope)
                            }

                            CustomLoadingScreen()
                        }

                        composable(Destination.GameDealScreen.route) {
                            val vm = hiltViewModel<DealsViewModel>()
                            val deals: LazyPagingItems<DealDto> =
                                vm.deals.collectAsLazyPagingItems()
                            val screenState: DealScreenState by vm.dealScreenState.collectAsState()
                            val sheetState: BottomSheetScaffoldState =
                                rememberBottomSheetScaffoldState(
                                    bottomSheetState = SheetState(
                                        skipPartiallyExpanded = false,
                                        initialValue = SheetValue.Hidden
                                    )
                                )

                            GameDealScreen(
                                deals = deals,
                                sheetState = sheetState,
                                screenState = screenState,
                                onEvent = vm::onEvent
                            )
                        }
                    }
                }
            }
        }
    }
}