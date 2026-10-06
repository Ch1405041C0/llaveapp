package com.example.llave360.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.llave360.model.AppState
import com.example.llave360.model.BusinessEvent
import com.example.llave360.model.BusinessEventType

class AppViewModel : ViewModel() {
  var state by mutableStateOf(AppState())
    private set

  private fun update(next: AppState, event: BusinessEvent) {
    state = next.recordEvent(event)
  }

  fun selectModel(model: String) {
    update(
      state.copy(businessModel = model, quantities = emptyMap()),
      BusinessEvent(BusinessEventType.MODEL_SELECTED, data = mapOf("model" to model)),
    )
  }

  fun selectSpace(space: String) {
    update(
      state.copy(space = space),
      BusinessEvent(BusinessEventType.SPACE_SELECTED, data = mapOf("space" to space)),
    )
  }

  fun selectCapital(capital: Int) {
    update(
      state.copy(capital = capital, quantities = emptyMap()),
      BusinessEvent(BusinessEventType.CAPITAL_SELECTED, data = mapOf("capital" to capital.toString())),
    )
  }

  fun updateIdeaBrief(brief: String) {
    update(
      state.copy(ideaBrief = brief),
      BusinessEvent(BusinessEventType.IDEA_UPDATED, data = mapOf("length" to brief.length.toString())),
    )
  }

  fun addProduct(id: String) {
    val quantities = state.quantities.toMutableMap().also { it[id] = (it[id] ?: 0) + 1 }
    update(
      state.copy(quantities = quantities),
      BusinessEvent(BusinessEventType.PRODUCT_ADDED, data = mapOf("product_id" to id, "quantity" to quantities[id].toString())),
    )
  }

  fun removeProduct(id: String) {
    val quantities = state.quantities.toMutableMap()
    val quantity = quantities[id] ?: return
    if (quantity == 1) quantities.remove(id) else quantities[id] = quantity - 1
    update(
      state.copy(quantities = quantities),
      BusinessEvent(BusinessEventType.PRODUCT_REMOVED, data = mapOf("product_id" to id, "quantity" to (quantities[id] ?: 0).toString())),
    )
  }
}
