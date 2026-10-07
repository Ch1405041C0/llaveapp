package com.example.llave360

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.activity.compose.BackHandler
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
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
import com.example.llave360.ui.thankyou.ThankYouScreen

@Composable
fun MainNavigation() {
  val backStack = rememberNavBackStack(Home)
  val appViewModel: AppViewModel = viewModel()
  val lifecycleOwner = LocalLifecycleOwner.current

  // Monitorear cuando el usuario vuelve de WhatsApp para redirigirlo a ThankYouScreen
  LaunchedEffect(lifecycleOwner) {
    lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
      if (appViewModel.isWhatsAppPending) {
        appViewModel.isWhatsAppPending = false
        backStack.add(ThankYou)
      }
    }
  }

  // Evita que Android cierre la actividad al volver desde cualquiera de las
  // pantallas del configurador: primero vuelve a la pantalla anterior.
  BackHandler(enabled = backStack.size > 1) {
    backStack.removeLastOrNull()
  }

  NavDisplay(
    backStack = backStack,
    onBack = {
      if (backStack.size > 1) backStack.removeLastOrNull()
    },
    entryProvider =
      entryProvider {

        entry<Home> {
          HomeScreen {
            backStack.add(BusinessModel)
          }
        }

        entry<BusinessModel> {
          ModelScreen(
            appViewModel.state,
            appViewModel.remoteBusinesses,
            appViewModel::selectModel,
            appViewModel::updateIdeaBrief,
            { backStack.add(Capital) }
          ) {
            backStack.removeLastOrNull()
          }
        }

        entry<Capital> {
          CapitalScreen(
            appViewModel.state,
            appViewModel::selectCapital,
            { backStack.add(Space) }
          ) {
            backStack.removeLastOrNull()
          }
        }

        entry<Space> {
          SpaceScreen(
            appViewModel.state,
            appViewModel::selectSpace,
            { backStack.add(Builder) }
          ) {
            backStack.removeLastOrNull()
          }
        }

        entry<Builder> {
          BuilderScreen(
            appViewModel.state,
            appViewModel::addProduct,
            appViewModel::removeProduct,
            { backStack.add(Confirmation) }
          ) {
            backStack.removeLastOrNull()
          }
        }

        entry<Confirmation> {
          ConfirmationScreen(
            appViewModel.state,
            {
              appViewModel.isWhatsAppPending = true
            }
          ) {
            backStack.removeLastOrNull()
          }
        }

        entry<ThankYou> {
          ThankYouScreen {
            appViewModel.resetState()

            // Limpia el backstack y vuelve a Home
            while (backStack.size > 1) {
              backStack.removeLastOrNull()
            }
          }
        }
      },
  )
}