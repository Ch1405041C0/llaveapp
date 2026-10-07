package com.example.llave360.model

data class Product(val id: String, val businessId: Long = 0, val name: String, val category: String, val price: Int, val description: String, val imageUrl: String = "")

data class BusinessModel(val id: Long, val name: String, val description: String, val imageUrl: String = "")

fun productsFor(model: String, businesses: List<BusinessModel>, products: List<Product>): List<Product> {
  val businessId = businesses.firstOrNull { it.name == model }?.id ?: return emptyList()
  return products.filter { it.businessId == businessId }
}
