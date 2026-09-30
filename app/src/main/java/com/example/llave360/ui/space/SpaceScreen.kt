package com.example.llave360.ui.space

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.llave360.model.AppState
import com.example.llave360.ui.components.AppHeader

private val spaces = listOf("Local a la calle" to "12–18 m²", "Galería o paseo" to "8–12 m²", "Interior de empresa" to "6–10 m²")

@Composable
fun SpaceScreen(state: AppState, select: (String) -> Unit, continueToBuilder: () -> Unit, back: () -> Unit) {
  Column(Modifier.fillMaxSize()) {
    AppHeader("03 / ESPACIO")
    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.SpaceBetween) {
      Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("¿Dónde va a funcionar tu negocio?", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Elegí una base para adaptar el equipamiento.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        spaces.forEach { (title, size) -> Card(Modifier.fillMaxWidth().selectable(selected = state.space == title, onClick = { select(title) }, role = Role.RadioButton)) {
          Column(Modifier.padding(18.dp)) { Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold); Text("Tamaño sugerido $size", color = MaterialTheme.colorScheme.primary) }
        } }
      }
      Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedButton(back, Modifier.weight(1f)) { Text("VOLVER") }
        Button(continueToBuilder, Modifier.weight(1f)) { Text("CONTINUAR") }
      }
    }
  }
}
