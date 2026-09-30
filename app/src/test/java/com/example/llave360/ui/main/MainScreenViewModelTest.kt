package com.example.llave360.ui.main

import com.example.llave360.model.AppState
import com.example.llave360.model.kioskProducts
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AppStateTest {
  @Test fun `calculates accumulated investment`() {
    val counter = kioskProducts.first { it.id == "counter" }
    val state = AppState(capital = 1_000_000, quantities = mapOf(counter.id to 1))
    assertEquals(counter.price, state.total)
  }

  @Test fun `can continue when total exceeds investment reference`() {
    val counter = kioskProducts.first { it.id == "counter" }
    val state = AppState(capital = 1, quantities = mapOf(counter.id to 1))
    assertTrue(state.canContinue)
  }
}
