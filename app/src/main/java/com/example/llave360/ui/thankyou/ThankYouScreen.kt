package com.example.llave360.ui.thankyou

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
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
import com.example.llave360.ui.components.FacebookIcon
import com.example.llave360.ui.components.InstagramIcon
import com.example.llave360.ui.components.TikTokIcon

@Composable
fun ThankYouScreen(onReturnHome: () -> Unit) {
  Box(modifier = Modifier.fillMaxSize()) {
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

    // Contenido estructurado con Compose nativo
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 28.dp, vertical = 48.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      // 1. Logo nativo superior
      Column(
        modifier = Modifier
          .width(210.dp)
          .padding(top = 16.dp),
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

      // 2. Icono de check circular dorado, título y descripción central
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
      ) {
        Box(
          modifier = Modifier
            .size(72.dp)
            .border(2.5.dp, MaterialTheme.colorScheme.primary, CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(40.dp)
          )
        }

        Text(
          text = "¡Gracias por elegir LLAVE360!",
          color = Color.White,
          style = MaterialTheme.typography.headlineMedium,
          fontWeight = FontWeight.Black,
          textAlign = TextAlign.Center
        )

        Text(
          text = "Tu propuesta está lista. Nuestro equipo se comunicará con vos a la brevedad para acompañarte en el próximo paso.",
          color = Color.White.copy(alpha = 0.85f),
          style = MaterialTheme.typography.titleMedium,
          textAlign = TextAlign.Center,
          lineHeight = 24.sp
        )
      }

      // 3. Sección de Redes y Botón inferior
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
          ) {
            Box(Modifier.weight(1f).height(1.dp).background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)))
            Text(
              text = "SEGUINOS EN REDES",
              color = MaterialTheme.colorScheme.primary,
              style = MaterialTheme.typography.labelLarge,
              fontWeight = FontWeight.Bold,
              letterSpacing = 2.sp,
              modifier = Modifier.padding(horizontal = 12.dp)
            )
            Box(Modifier.weight(1f).height(1.dp).background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)))
          }
          
          Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            // Instagram Icon Circle
            Box(
              modifier = Modifier
                .size(44.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.7f), CircleShape),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = InstagramIcon,
                contentDescription = "Instagram",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
              )
            }

            // Facebook Icon Circle
            Box(
              modifier = Modifier
                .size(44.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.7f), CircleShape),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = FacebookIcon,
                contentDescription = "Facebook",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
              )
            }

            // TikTok Icon Circle
            Box(
              modifier = Modifier
                .size(44.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.7f), CircleShape),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = TikTokIcon,
                contentDescription = "TikTok",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
              )
            }
          }
        }


        OutlinedButton(
          onClick = onReturnHome,
          modifier = Modifier.fillMaxWidth().height(50.dp),
          colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
          border = borderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
        ) {
          Text(
            text = "VOLVER AL INICIO",
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
        }
      }
    }
  }
}

@Composable
private fun borderStroke(width: androidx.compose.ui.unit.Dp, color: Color) = androidx.compose.foundation.BorderStroke(width, color)
