package com.example.llave360.ui.confirmation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.llave360.model.AppState
import com.example.llave360.ui.components.*

@Composable fun ConfirmationScreen(state: AppState, back: () -> Unit) {
  val context = LocalContext.current
  Column(Modifier.fillMaxSize()) {
    AppHeader("05 / REMITO")
    LazyColumn(Modifier.weight(1f).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
      item { Text("Tu propuesta está lista.", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold); Text("Revisá el equipamiento antes de solicitarla.", color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 8.dp)) }
      item { Text("${state.businessModel} · ${state.space}", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(top = 12.dp)) }
      if (state.businessModel == "Tu idea" && state.ideaBrief.isNotBlank()) item { Card(Modifier.fillMaxWidth()) { Text(state.ideaBrief, Modifier.padding(16.dp), color = MaterialTheme.colorScheme.onSurfaceVariant) } }
      items(state.selectedProducts, key = { it.first.id }) { (product, quantity) -> Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("$quantity × ${product.name}", Modifier.weight(1f)); Text(money(product.price * quantity), fontWeight = FontWeight.SemiBold) } }
      item { Card(Modifier.fillMaxWidth().padding(top = 12.dp)) { Row(Modifier.fillMaxWidth().padding(18.dp), horizontalArrangement = Arrangement.SpaceBetween) { Column { Text("Inversión estimada", style = MaterialTheme.typography.labelMedium); Text(money(state.total), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold) } } } }
    }
    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
      OutlinedButton(back, Modifier.fillMaxWidth()) { Text("EDITAR PEDIDO") }
      Button(onClick = {
        val detail = state.selectedProducts.joinToString("\n") { (product, quantity) -> "• $quantity x ${product.name}: ${money(product.price * quantity)}" }
        val idea = state.ideaBrief.takeIf { it.isNotBlank() }?.let { "\n\nMi idea: $it" }.orEmpty()
        val message = Uri.encode("Hola, quiero solicitar una propuesta de Llave 360.\n\nModelo: ${state.businessModel}\nEspacio: ${state.space}$idea\n\n$detail\n\nInversión estimada: ${money(state.total)}")
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/541164627789?text=$message")))
      }, modifier = Modifier.fillMaxWidth()) { Text("SOLICITAR PROPUESTA") }
    }
  }
}
