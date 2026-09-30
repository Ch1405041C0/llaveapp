package com.example.llave360.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.llave360.ui.components.AppHeader
import com.example.llave360.R

@Composable
fun HomeScreen(onStart: () -> Unit) {
  Column(Modifier.fillMaxSize()) {
    AppHeader("")
    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.SpaceBetween) {
      Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Tu negocio,\nlisto para abrir.", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Black)
        Text("Diseñá tu punto de venta y recibí una propuesta a medida.", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Card(Modifier.fillMaxWidth().padding(top = 12.dp)) {
          Column {
            Image(painterResource(R.drawable.model_kiosko), "Kiosko 360", Modifier.fillMaxWidth(), contentScale = ContentScale.Crop)
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("KIOSKO 360", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            Text("Venta, frío, cobros y seguridad en una solución lista para operar.", style = MaterialTheme.typography.bodyLarge)
            }
          }
        }
      }
      Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(onStart, Modifier.fillMaxWidth()) { Text("COMENZAR") }
        Text("Kiosko · Coffee · Barber · Pet · Tu idea", modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
      }
    }
  }
}
