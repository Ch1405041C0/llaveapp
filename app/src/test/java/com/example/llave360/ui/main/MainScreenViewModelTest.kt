package com.example.llave360.ui.main

import com.example.llave360.model.AppState
import com.example.llave360.model.kioskProducts
import org.junit.Assert.assertEquals
import org.junit.Test

class AppStateTest {
  @Test fun `calculates remaining capital`() {
    val counter = kioskProducts.first { it.id == "counter" }
    val state = AppState(capital = 1_000_000, quantities = mapOf(counter.id to 1))
    assertEquals(580_000, state.remaining)
  }
}
