package com.example.llave360.model

data class Product(val id: String, val name: String, val category: String, val price: Int, val description: String)

data class BusinessModel(val name: String, val description: String, val imageRes: Int?)

val kioskProducts = listOf(
  Product("counter", "Mostrador de venta", "Mobiliario", 420_000, "Módulo frontal con guardado"),
  Product("display", "Exhibidora refrigerada", "Frío", 780_000, "Exhibición vertical para bebidas"),
  Product("shelves", "Góndola metálica", "Exhibición", 195_000, "Cinco estantes regulables"),
  Product("pos", "Punto de cobro QR", "Cobros", 95_000, "Soporte, señalética y cobro digital"),
  Product("security", "Kit de seguridad", "Seguridad", 165_000, "Cámara, cerradura y cartel disuasivo"),
  Product("sign", "Cartel luminoso", "Identidad", 230_000, "Frente retroiluminado personalizable"),
)

val coffeeProducts = listOf(
  Product("bar", "Barra de café", "Mobiliario", 390_000, "Frente de atención con guardado"),
  Product("espresso", "Máquina espresso", "Café", 650_000, "Equipo profesional de dos grupos"),
  Product("pastry", "Vitrina de pastelería", "Exhibición", 320_000, "Exhibición refrigerada de mostrador"),
  Product("coffee_fridge", "Heladera exhibidora", "Frío", 280_000, "Bebidas listas para llevar"),
)

val barberProducts = listOf(
  Product("chair", "Sillón barber", "Puesto", 520_000, "Sillón hidráulico profesional"),
  Product("mirror", "Espejo con estación", "Mobiliario", 265_000, "Espejo, mesada y guardado"),
  Product("wash", "Lavacabezas", "Lavado", 360_000, "Bacha con sillón reclinable"),
  Product("reception", "Módulo de recepción", "Atención", 245_000, "Mostrador compacto de ingreso"),
)

val petProducts = listOf(
  Product("grooming", "Mesa de grooming", "Servicios", 350_000, "Mesa hidráulica para baño y corte"),
  Product("pet_shelf", "Góndola pet", "Exhibición", 215_000, "Módulo para alimento y accesorios"),
  Product("bath", "Bañera profesional", "Servicios", 430_000, "Bañera de acero para mascotas"),
  Product("pet_counter", "Mostrador de atención", "Mobiliario", 285_000, "Frente de venta con guardado"),
)

fun productsFor(model: String) = when (model) {
  "Coffee Point" -> coffeeProducts
  "Barber 360" -> barberProducts
  "Pet Point" -> petProducts
  else -> kioskProducts
}
