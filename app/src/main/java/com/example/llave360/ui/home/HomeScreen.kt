package com.example.llave360.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.llave360.R

@Composable
fun HomeScreen(onStart: () -> Unit) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .clickable(onClick = onStart)
  ) {
    // Fondo de pantalla completa con la imagen limpia (llaves desenfocadas)
    Image(
      painter = painterResource(R.drawable.llave360_fondo_llaves),
      contentDescription = null,
      modifier = Modifier.fillMaxSize(),
      contentScale = ContentScale.Crop
    )

    // Capa negra semitransparente
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.Black.copy(alpha = 0.65f))
    )

    // Contenido de la pantalla de inicio
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 28.dp, vertical = 48.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      // Logo nativo centralizado superior (210.dp de ancho aproximado)
      Column(
        modifier = Modifier
          .width(210.dp)
          .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Text(
            text = "LLAVE",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 6.sp,
            modifier = Modifier.padding(end = 8.dp)
          )
          Box(
            modifier = Modifier
              .size(54.dp)
              .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "360",
              color = MaterialTheme.colorScheme.primary,
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 0.sp
            )
          }
        }
        Text(
          text = "IDEAS QUE ABREN TU MUNDO",
          color = MaterialTheme.colorScheme.primary,
          fontSize = 10.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 2.sp
        )
      }

      // Slogan y Descripción intermedia
      Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text("Tu negocio,\nlisto para abrir.", color = Color.White, style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Black, textAlign = TextAlign.Center)
        Spacer(Modifier.height(18.dp))
        Text("Diseñá tu punto de venta y recibí una propuesta a tu medida.", style = MaterialTheme.typography.titleMedium, color = Color.White.copy(alpha = 0.85f), textAlign = TextAlign.Center)
      }

      // Botón inferior
      Text("TOCÁ PARA COMENZAR", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp, modifier = Modifier.padding(bottom = 16.dp))
    }
  }
}
