package com.example.llave360.ui.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.llave360.model.AppState
import com.example.llave360.ui.components.AppHeader
import com.example.llave360.R

private data class ModelOption(val title: String, val detail: String, val image: Int?)
private val models = listOf(
  ModelOption("Kiosko 360", "Venta, frío, cobros y seguridad.", R.drawable.model_kiosko),
  ModelOption("Coffee Point", "Café, exhibición y atención rápida.", R.drawable.model_coffee),
  ModelOption("Barber 360", "Barbería moderna lista para operar.", R.drawable.model_barber),
  ModelOption("Pet Point", "Servicios y venta para mascotas.", R.drawable.model_pet),
  ModelOption("Tu idea", "Contanos tu idea y armamos una propuesta a medida.", null),
)

@Composable
fun ModelScreen(state: AppState, select: (String) -> Unit, updateIdea: (String) -> Unit, continueToCapital: () -> Unit, back: () -> Unit) {
  Column(Modifier.fillMaxSize()) {
    AppHeader("01 / MODELO")
    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text("¿Qué negocio querés abrir?", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Elegí un modelo y adaptalo a tu espacio.", color = MaterialTheme.colorScheme.onSurfaceVariant)
      }
      LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) { items(models, key = { it.title }) { option ->
        Card(Modifier.fillMaxWidth().selectable(selected = state.businessModel == option.title, onClick = { select(option.title) }, role = Role.RadioButton)) {
          Column {
            option.image?.let { Image(painterResource(it), option.title, Modifier.fillMaxWidth(), contentScale = ContentScale.Crop) }
            Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
              Text(option.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = if (state.businessModel == option.title) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface)
              Text(option.detail, color = MaterialTheme.colorScheme.onSurfaceVariant)
              if (option.title == "Tu idea" && state.businessModel == "Tu idea") {
                OutlinedTextField(value = state.ideaBrief, onValueChange = updateIdea, modifier = Modifier.fillMaxWidth(), label = { Text("Contanos brevemente tu idea") }, minLines = 2)
              }
            }
          }
        }
      } }
      Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedButton(back, Modifier.weight(1f)) { Text("VOLVER") }
        Button(continueToCapital, Modifier.weight(1f)) { Text("CONTINUAR") }
      }
    }
  }
}
