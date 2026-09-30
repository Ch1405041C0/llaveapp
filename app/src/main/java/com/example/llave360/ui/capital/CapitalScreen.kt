package com.example.llave360.ui.capital

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.llave360.model.AppState
import com.example.llave360.ui.components.AppHeader
import com.example.llave360.ui.components.money

private val options = listOf(750_000, 1_500_000, 2_500_000, 4_000_000)

@Composable
fun CapitalScreen(state: AppState, select: (Int) -> Unit, continueToSpace: () -> Unit, back: () -> Unit) = Column(Modifier.fillMaxSize()) {
  AppHeader("02 / CAPITAL")
  Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.SpaceBetween) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
      Text("¿Con qué capital contás?", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
      Text("Partimos de tu inversión para diseñar una propuesta posible.", color = MaterialTheme.colorScheme.onSurfaceVariant)
      options.forEach { amount ->
        val isSelected = state.capital == amount
        Card(
          modifier = Modifier.fillMaxWidth().selectable(selected = isSelected, onClick = { select(amount) }, role = Role.RadioButton),
          border = if (isSelected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
          else BorderStroke(1.dp, Color(0xFF333333)), // Gris oscuro
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
          Row(
            Modifier.fillMaxWidth().padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Column(Modifier.weight(1f)) {
              Text(money(amount), fontWeight = FontWeight.Bold)
              Text(
                if (amount == 1_500_000) "Kiosko inicial recomendado" else "Presupuesto de inversión",
                style = MaterialTheme.typography.bodySmall,
                color = if (amount == 1_500_000) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
            Box(
              modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .then(
                  if (isSelected) Modifier.background(MaterialTheme.colorScheme.primary)
                  else Modifier.border(1.dp, Color(0xFF333333), CircleShape)
                ),
              contentAlignment = Alignment.Center
            ) {
              if (isSelected) {
                Icon(Icons.Default.Check, null, Modifier.size(16.dp), tint = MaterialTheme.colorScheme.onBackground)
              }
            }
          }
        }
      }
    }
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
      OutlinedButton(back, Modifier.weight(1f)) { Text("VOLVER") }
      Button(continueToSpace, Modifier.weight(1f), contentPadding = PaddingValues(vertical = 16.dp)) { Text("CONTINUAR") }
    }
  }
}
