package com.example.llave360

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.llave360.ui.AppViewModel
import com.example.llave360.ui.builder.BuilderScreen
import com.example.llave360.ui.capital.CapitalScreen
import com.example.llave360.ui.confirmation.ConfirmationScreen
import com.example.llave360.ui.home.HomeScreen
import com.example.llave360.ui.model.ModelScreen
import com.example.llave360.ui.space.SpaceScreen

@Composable
fun MainNavigation() {
  val backStack = rememberNavBackStack(Home)
  val appViewModel: AppViewModel = viewModel()

  NavDisplay(
    backStack = backStack,
    onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
    entryProvider =
      entryProvider {
        entry<Home> { HomeScreen { backStack.add(BusinessModel) } }
        entry<BusinessModel> { ModelScreen(appViewModel.state, appViewModel::selectModel, appViewModel::updateIdeaBrief, { backStack.add(Capital) }) { backStack.removeLastOrNull() } }
        entry<Capital> { CapitalScreen(appViewModel.state, appViewModel::selectCapital, { backStack.add(Space) }) { backStack.removeLastOrNull() } }
        entry<Space> { SpaceScreen(appViewModel.state, appViewModel::selectSpace, { backStack.add(Builder) }) { backStack.removeLastOrNull() } }
        entry<Builder> { BuilderScreen(appViewModel.state, appViewModel::addProduct, appViewModel::removeProduct, { backStack.add(Confirmation) }) { backStack.removeLastOrNull() } }
        entry<Confirmation> { ConfirmationScreen(appViewModel.state) { backStack.removeLastOrNull() } }
      },
  )
}
