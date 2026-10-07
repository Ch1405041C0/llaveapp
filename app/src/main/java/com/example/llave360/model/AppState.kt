package com.example.llave360.model

data class AppState(
  val businessModel: String = "",
  val space: String = "Local a la calle",
  val capital: Int = 1_500_000,
  val ideaBrief: String = "",
  val quantities: Map<String, Int> = emptyMap(),
  val businesses: List<BusinessModel> = emptyList(),
  val products: List<Product> = emptyList(),
  val catalogLoading: Boolean = true,
  val catalogError: String = "",
  val events: List<BusinessEvent> = emptyList(),
) {
  val selectedProducts = productsFor(businessModel, businesses, products).mapNotNull { product -> quantities[product.id]?.takeIf { it > 0 }?.let { product to it } }
  val total = selectedProducts.sumOf { (product, quantity) -> product.price * quantity }
  val remaining = capital - total
  val canContinue = selectedProducts.isNotEmpty() && remaining >= 0
  fun recordEvent(event: BusinessEvent): AppState = copy(events = events + event)
}
