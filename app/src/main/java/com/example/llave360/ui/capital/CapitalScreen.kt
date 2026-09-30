package com.example.llave360.ui.capital

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.llave360.model.AppState
import com.example.llave360.ui.components.AppHeader
import com.example.llave360.ui.components.money

private val options = listOf(750_000, 1_500_000, 2_500_000, 4_000_000)
@Composable fun CapitalScreen(state: AppState, select: (Int) -> Unit, continueToSpace: () -> Unit, back: () -> Unit) = Column(Modifier.fillMaxSize()) {
  AppHeader("02 / CAPITAL")
  Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.SpaceBetween) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
      Text("¿Con qué capital contás?", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
      Text("Partimos de tu inversión para diseñar una propuesta posible.", color = MaterialTheme.colorScheme.onSurfaceVariant)
      options.forEach { amount -> Card(Modifier.fillMaxWidth().selectable(selected = state.capital == amount, onClick = { select(amount) }, role = Role.RadioButton)) {
        Row(Modifier.fillMaxWidth().padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
          RadioButton(state.capital == amount, null); Column(Modifier.padding(start = 12.dp)) { Text(money(amount), fontWeight = FontWeight.Bold); Text(if (amount == 1_500_000) "Kiosko inicial recomendado" else "Presupuesto de inversión", style = MaterialTheme.typography.bodySmall, color = if (amount == 1_500_000) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant) }
        }
      } }
    }
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) { OutlinedButton(back, Modifier.weight(1f)) { Text("VOLVER") }; Button(continueToSpace, Modifier.weight(1f), contentPadding = PaddingValues(vertical = 16.dp)) { Text("CONTINUAR") } }
  }
}
