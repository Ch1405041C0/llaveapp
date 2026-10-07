package com.example.llave360.data

import com.example.llave360.model.BusinessModel
import com.example.llave360.model.Product
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONArray

object SupabaseCatalog {
  private const val BASE_URL = "https://aatnwhjdawuyixoicgib.supabase.co/rest/v1"
  private const val API_KEY = "sb_publishable_c2SJxQRbofuEBe7U8ZZyzg_S-Bs57qE"

  private fun get(path: String): JSONArray {
    val connection = URL("$BASE_URL/$path").openConnection() as HttpURLConnection
    connection.requestMethod = "GET"
    connection.setRequestProperty("apikey", API_KEY)
    connection.setRequestProperty("Accept", "application/json")
    connection.connectTimeout = 10000
    connection.readTimeout = 10000
    return try {
      val code = connection.responseCode
      if (code !in 200..299) error("Supabase HTTP $code")
      JSONArray(connection.inputStream.bufferedReader().use { it.readText() })
    } finally {
      connection.disconnect()
    }
  }

  fun loadBusinesses(): List<BusinessModel> {
    val json = get("businesses?select=id,name,description,image_url&active=eq.true&order=id.asc")
    return (0 until json.length()).map { i ->
      val row = json.getJSONObject(i)
      BusinessModel(
        id = row.getLong("id"),
        name = row.getString("name"),
        description = row.optString("description", ""),
        imageUrl = row.optString("image_url", ""),
      )
    }
  }

  fun loadProducts(): List<Product> {
    val json = get("products?select=id,business_id,name,description,price,image_url&active=eq.true&order=id.asc")
    return (0 until json.length()).map { i ->
      val row = json.getJSONObject(i)
      Product(
        id = row.getLong("id").toString(),
        businessId = row.getLong("business_id"),
        name = row.getString("name"),
        category = "Equipamiento",
        price = row.getLong("price").coerceAtMost(Int.MAX_VALUE.toLong()).toInt(),
        description = row.optString("description", ""),
        imageUrl = row.optString("image_url", ""),
      )
    }
  }
}
