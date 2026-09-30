package com.example.llave360.ui.model

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
        val selected = state.businessModel == option.title
        Card(
          modifier = Modifier.fillMaxWidth().selectable(selected = selected, onClick = { select(option.title) }, role = Role.RadioButton),
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          border = BorderStroke(if (selected) 1.5.dp else 1.dp, if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant),
        ) {
          Box {
            Row(Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
              option.image?.let { Image(painterResource(it), option.title, Modifier.size(104.dp).clip(RoundedCornerShape(8.dp)), contentScale = ContentScale.Crop) }
              Column(Modifier.weight(1f).padding(top = 6.dp, bottom = 6.dp, end = 28.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(option.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface)
                Text(option.detail, color = MaterialTheme.colorScheme.onSurfaceVariant)
                if (option.title == "Tu idea" && selected) {
                  OutlinedTextField(value = state.ideaBrief, onValueChange = updateIdea, modifier = Modifier.fillMaxWidth(), label = { Text("Contanos brevemente tu idea") }, minLines = 2)
                }
              }
            }
            Box(
              modifier = Modifier.align(Alignment.TopEnd).padding(10.dp).size(25.dp).border(1.5.dp, if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant, CircleShape),
              contentAlignment = Alignment.Center,
            ) {
              if (selected) Text("✓", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Black)
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
