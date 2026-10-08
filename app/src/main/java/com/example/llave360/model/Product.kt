package com.example.llave360.model

import com.example.llave360.R

data class Product(
  val id: String,
  val name: String,
  val category: String,
  val price: Int,
  val description: String,
  val imageRes: Int? = null,
  val imageUrl: String? = null
)

data class BusinessModel(val name: String, val description: String, val imageRes: Int?)

val kioskProducts = listOf(
  Product("counter", "Mostrador de venta", "Mobiliario", 420_000, "Módulo frontal con guardado", R.drawable.item_counter),
  Product("display", "Exhibidora refrigerada", "Frío", 780_000, "Exhibición vertical para bebidas", R.drawable.item_display),
  Product("shelves", "Góndola metálica", "Exhibición", 195_000, "Cinco estantes regulables", R.drawable.item_shelves),
  Product("pos", "Punto de cobro QR", "Cobros", 95_000, "Soporte, señalética y cobro digital", R.drawable.item_pos),
  Product("security", "Kit de seguridad", "Seguridad", 165_000, "Cámara, cerradura y cartel disuasivo", R.drawable.item_security),
  Product("sign", "Cartel luminoso", "Identidad", 230_000, "Frente retroiluminado personalizable", R.drawable.item_sign),
)

val coffeeProducts = listOf(
  Product("bar", "Barra de café", "Mobiliario", 390_000, "Frente de atención con guardado", R.drawable.item_bar),
  Product("espresso", "Máquina espresso", "Café", 650_000, "Equipo profesional de dos grupos", R.drawable.item_espresso),
  Product("pastry", "Vitrina de pastelería", "Exhibición", 320_000, "Exhibición refrigerada de mostrador", R.drawable.item_pastry),
  Product("coffee_fridge", "Heladera exhibidora", "Frío", 280_000, "Bebidas listas para llevar", R.drawable.item_coffee_fridge),
)

val barberProducts = listOf(
  Product("chair", "Sillón barber", "Puesto", 520_000, "Sillón hidráulico profesional", R.drawable.item_chair),
  Product("mirror", "Espejo con estación", "Mobiliario", 265_000, "Espejo, mesada y guardado", R.drawable.item_mirror),
  Product("wash", "Lavacabezas", "Lavado", 360_000, "Bacha con sillón reclinable", R.drawable.item_wash),
  Product("reception", "Módulo de recepción", "Atención", 245_000, "Mostrador compacto de ingreso", R.drawable.item_reception),
)

val petProducts = listOf(
  Product("grooming", "Mesa de grooming", "Servicios", 350_000, "Mesa hidráulica para baño y corte", R.drawable.item_grooming),
  Product("pet_shelf", "Góndola pet", "Exhibición", 215_000, "Módulo para alimento y accesorios", R.drawable.item_pet_shelf),
  Product("bath", "Bañera profesional", "Servicios", 430_000, "Bañera de acero para mascotas", R.drawable.item_bath),
  Product("pet_counter", "Mostrador de atención", "Mobiliario", 285_000, "Frente de venta con guardado", R.drawable.item_pet_counter),
)

fun productsFor(model: String) = when (model) {
  "Coffee Point" -> coffeeProducts
  "Barber 360" -> barberProducts
  "Pet Point" -> petProducts
  else -> kioskProducts
}
