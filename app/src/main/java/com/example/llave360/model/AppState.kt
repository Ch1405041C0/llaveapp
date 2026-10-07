package com.example.llave360.model

data class AppState(
    val businessModel: String = "Kiosko 360",
    val space: String = "Local a la calle",
    val capital: Int = 1_500_000,
    val ideaBrief: String = "",
    val quantities: Map<String, Int> = emptyMap(),
    val availableProducts: List<Product> = emptyList()
) {
    val currentProducts: List<Product>
        get() = if (availableProducts.isNotEmpty()) {
            availableProducts
        } else {
            productsFor(businessModel)
        }

    val selectedProducts: List<Pair<Product, Int>>
        get() = currentProducts.mapNotNull { product ->
            quantities[product.id]
                ?.takeIf { it > 0 }
                ?.let { product to it }
        }

    val total: Int
        get() = selectedProducts.sumOf { (product, quantity) ->
            product.price * quantity
        }

    val canContinue: Boolean
        get() = selectedProducts.isNotEmpty()
}