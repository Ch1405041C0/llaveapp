package com.example.llave360.ui.builder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.llave360.model.AppState
import com.example.llave360.model.Product
import com.example.llave360.ui.components.AppHeader
import com.example.llave360.ui.components.money

@Composable
fun BuilderScreen(
  state: AppState,
  add: (String) -> Unit,
  remove: (String) -> Unit,
  continueToConfirmation: () -> Unit,
  back: () -> Unit,
) {
  Column(Modifier.fillMaxSize()) {
    AppHeader("04 / ARMÁ TU PROPUESTA")

    Card(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp)) {
      Row(
        Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
      ) {
        Column {
          Text("INVERSIÓN ESTIMADA", style = MaterialTheme.typography.labelMedium)
          Text(
            money(state.total),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
          )
          if (state.total > state.capital) {
            Text(
              "Superaste la inversión de referencia. Podés seguir sumando equipamiento.",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
          }
        }
      }
    }

    LazyColumn(
      modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      item {
        Card(Modifier.fillMaxWidth()) {
          Column(
            Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            Text(state.businessModel.uppercase(), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text(state.space, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(
              "Vista previa del espacio. El equipamiento que agregues se incluirá en tu propuesta.",
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
          }
        }
      }
      item {
        Text("Equipamiento", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("Sumá los módulos que quieras. La inversión estimada se actualiza automáticamente.", color = MaterialTheme.colorScheme.onSurfaceVariant)
      }
      items(state.currentProducts, key = { it.id }) { product ->
        ProductCard(product, state.quantities[product.id] ?: 0, add, remove)
      }
    }

    Row(
      Modifier.fillMaxWidth().padding(16.dp),
      horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      OutlinedButton(onClick = back, modifier = Modifier.weight(1f)) { Text("VOLVER") }
      Button(
        onClick = continueToConfirmation,
        modifier = Modifier.weight(1f),
        enabled = state.canContinue,
      ) { Text("VER REMITO") }
    }
  }
}

@Composable
private fun ProductCard(
  product: Product,
  quantity: Int,
  add: (String) -> Unit,
  remove: (String) -> Unit,
) {
  Card(Modifier.fillMaxWidth()) {
    Column {
      product.imageRes?.let {
        Image(
          painter = painterResource(it),
          contentDescription = null,
          modifier = Modifier.fillMaxWidth().height(140.dp),
          contentScale = ContentScale.Crop
        )
      }
      Row(
        Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Column(Modifier.weight(1f)) {
          Text(product.category.uppercase(), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
          Text(product.name, fontWeight = FontWeight.Bold)
          Text(product.description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
          Text(money(product.price), fontWeight = FontWeight.SemiBold)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
          IconButton(onClick = { remove(product.id) }, enabled = quantity > 0) {
            Text("−", style = MaterialTheme.typography.titleLarge)
          }
          Text(quantity.toString(), fontWeight = FontWeight.Bold)
          IconButton(onClick = { add(product.id) }) {
            Text("+", style = MaterialTheme.typography.titleLarge)
          }
        }
      }
    }
  }
}
