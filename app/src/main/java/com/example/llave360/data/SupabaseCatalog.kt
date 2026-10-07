package com.example.llave360.data

import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

data class RemoteBusiness(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String?
)

data class RemoteProduct(
    val id: Long,
    val businessId: Long,
    val name: String,
    val description: String,
    val price: Int,
    val imageUrl: String?
)

object SupabaseCatalog {

    private const val SUPABASE_URL =
        "https://aatnwhjdawuyixoicgib.supabase.co"

    private const val SUPABASE_KEY =
        "sb_publishable_c2SJxQRbofuEBe7U8ZZyzg_S-Bs57qE"

    fun loadBusinesses(): List<RemoteBusiness> {
        val json = get(
            "$SUPABASE_URL/rest/v1/businesses" +
                "?select=id,name,description,image_url" +
                "&active=eq.true" +
                "&order=id.asc"
        )

        val array = JSONArray(json)

        return List(array.length()) { index ->
            val item = array.getJSONObject(index)

            RemoteBusiness(
                id = item.getLong("id"),
                name = item.getString("name"),
                description = item.optString("description", ""),
                imageUrl = item.optString("image_url")
                    .takeIf { it.isNotBlank() && it != "null" }
            )
        }
    }

    fun loadProducts(): List<RemoteProduct> {
        val json = get(
            "$SUPABASE_URL/rest/v1/products" +
                "?select=id,business_id,name,description,price,image_url" +
                "&active=eq.true" +
                "&order=id.asc"
        )

        val array = JSONArray(json)

        return List(array.length()) { index ->
            val item = array.getJSONObject(index)

            RemoteProduct(
                id = item.getLong("id"),
                businessId = item.getLong("business_id"),
                name = item.getString("name"),
                description = item.optString("description", ""),
                price = item.getInt("price"),
                imageUrl = item.optString("image_url")
                    .takeIf { it.isNotBlank() && it != "null" }
            )
        }
    }

    private fun get(endpoint: String): String {
        val connection = URL(endpoint).openConnection() as HttpURLConnection

        return try {
            connection.requestMethod = "GET"
            connection.connectTimeout = 10_000
            connection.readTimeout = 10_000
            connection.setRequestProperty("apikey", SUPABASE_KEY)
            connection.setRequestProperty(
                "Authorization",
                "Bearer $SUPABASE_KEY"
            )
            connection.setRequestProperty("Accept", "application/json")

            val status = connection.responseCode

            val stream =
                if (status in 200..299) {
                    connection.inputStream
                } else {
                    connection.errorStream
                }

            val response = stream
                ?.bufferedReader()
                ?.use { it.readText() }
                .orEmpty()

            if (status !in 200..299) {
                throw IllegalStateException(
                    "Supabase HTTP $status: $response"
                )
            }

            response
        } finally {
            connection.disconnect()
        }
    }
}