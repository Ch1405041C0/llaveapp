package com.example.llave360.model

import java.time.Instant

data class BusinessEvent(
  val type: String,
  val occurredAt: String = Instant.now().toString(),
  val source: String = "app",
  val data: Map<String, String> = emptyMap(),
)

object BusinessEventType {
  const val MODEL_SELECTED = "model_selected"
  const val SPACE_SELECTED = "space_selected"
  const val CAPITAL_SELECTED = "capital_selected"
  const val IDEA_UPDATED = "idea_updated"
  const val PRODUCT_ADDED = "product_added"
  const val PRODUCT_REMOVED = "product_removed"
}
