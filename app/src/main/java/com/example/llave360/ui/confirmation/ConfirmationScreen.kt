package com.example.llave360.ui.confirmation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.llave360.model.AppState
import com.example.llave360.ui.components.*

@Composable
fun ConfirmationScreen(
    state: AppState,
    isSubmitting: Boolean,
    requestError: String?,
    onRequestSubmitted: (
        clientName: String,
        phone: String,
        onSuccess: () -> Unit
    ) -> Unit,
    back: () -> Unit
) {
    val context = LocalContext.current

    var clientName by rememberSaveable {
        mutableStateOf("")
    }

    var phone by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        Modifier.fillMaxSize()
    ) {
        AppHeader("05 / REMITO")

        LazyColumn(
            Modifier
                .weight(1f)
                .padding(16.dp),
            verticalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    "Tu propuesta está lista.",
                    style =
                        MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "Revisá el equipamiento antes de solicitarla.",
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier =
                        Modifier.padding(top = 8.dp)
                )
            }

            item {
                Text(
                    "${state.businessModel} · ${state.space}",
                    style =
                        MaterialTheme.typography.labelLarge,
                    color =
                        MaterialTheme.colorScheme.primary,
                    modifier =
                        Modifier.padding(top = 12.dp)
                )
            }

            if (
                state.businessModel == "Tu idea" &&
                state.ideaBrief.isNotBlank()
            ) {
                item {
                    Card(
                        Modifier.fillMaxWidth()
                    ) {
                        Text(
                            state.ideaBrief,
                            Modifier.padding(16.dp),
                            color =
                                MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            items(
                state.selectedProducts,
                key = { it.first.id }
            ) { (product, quantity) ->

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceBetween,
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {
                    Row(
                        Modifier.weight(1f),
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {
                        product.imageRes?.let {
                            Image(
                                painter =
                                    painterResource(it),
                                contentDescription = null,
                                modifier =
                                    Modifier
                                        .size(48.dp)
                                        .clip(
                                            RoundedCornerShape(
                                                4.dp
                                            )
                                        ),
                                contentScale =
                                    ContentScale.Crop
                            )

                            Spacer(
                                Modifier.width(12.dp)
                            )
                        }

                        Text(
                            "$quantity × ${product.name}"
                        )
                    }

                    Text(
                        money(
                            product.price *
                                quantity
                        ),
                        fontWeight =
                            FontWeight.SemiBold
                    )
                }
            }

            item {
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                "Inversión estimada",
                                style =
                                    MaterialTheme.typography.labelMedium
                            )

                            Text(
                                money(state.total),
                                style =
                                    MaterialTheme.typography.titleLarge,
                                color =
                                    MaterialTheme.colorScheme.primary,
                                fontWeight =
                                    FontWeight.Bold
                            )
                        }
                    }
                }
            }

            item {
                HorizontalDivider(
                    modifier =
                        Modifier.padding(
                            vertical = 8.dp
                        )
                )

                Text(
                    "Tus datos",
                    style =
                        MaterialTheme.typography.titleMedium,
                    fontWeight =
                        FontWeight.Bold
                )

                Text(
                    "Los usamos para identificar tu solicitud.",
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier =
                        Modifier.padding(
                            top = 4.dp,
                            bottom = 12.dp
                        )
                )

                OutlinedTextField(
                    value = clientName,
                    onValueChange = {
                        clientName = it
                    },
                    label = {
                        Text("Nombre y apellido")
                    },
                    singleLine = true,
                    enabled = !isSubmitting,
                    modifier =
                        Modifier.fillMaxWidth()
                )

                Spacer(
                    Modifier.height(10.dp)
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = {
                        phone = it
                    },
                    label = {
                        Text("Teléfono")
                    },
                    singleLine = true,
                    enabled = !isSubmitting,
                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType =
                                KeyboardType.Phone
                        ),
                    modifier =
                        Modifier.fillMaxWidth()
                )

                if (!requestError.isNullOrBlank()) {
                    Text(
                        requestError,
                        color =
                            MaterialTheme.colorScheme.error,
                        style =
                            MaterialTheme.typography.bodySmall,
                        modifier =
                            Modifier.padding(top = 8.dp)
                    )
                }
            }
        }

        Column(
            Modifier.padding(16.dp),
            verticalArrangement =
                Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = back,
                enabled = !isSubmitting,
                modifier =
                    Modifier.fillMaxWidth()
            ) {
                Text("EDITAR PEDIDO")
            }

            Button(
                onClick = {

                    val detail =
                        state.selectedProducts
                            .joinToString("\n") {
                                (product, quantity) ->

                                "• $quantity x ${product.name}: ${
                                    money(
                                        product.price *
                                            quantity
                                    )
                                }"
                            }

                    val idea =
                        state.ideaBrief
                            .takeIf {
                                it.isNotBlank()
                            }
                            ?.let {
                                "\n\nMi idea: $it"
                            }
                            .orEmpty()

                    val message =
                        Uri.encode(
                            "Hola, quiero solicitar una propuesta de Llave 360." +
                                "\n\nNombre: ${clientName.trim()}" +
                                "\nTeléfono: ${phone.trim()}" +
                                "\n\nModelo: ${state.businessModel}" +
                                "\nEspacio: ${state.space}" +
                                idea +
                                "\n\n$detail" +
                                "\n\nInversión estimada: ${money(state.total)}"
                        )

                    onRequestSubmitted(
                        clientName,
                        phone
                    ) {
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(
                                    "https://wa.me/541164627789?text=$message"
                                )
                            )
                        )
                    }
                },
                enabled =
                    !isSubmitting &&
                        clientName.isNotBlank() &&
                        phone.isNotBlank(),
                modifier =
                    Modifier.fillMaxWidth()
            ) {
                if (isSubmitting) {
                    CircularProgressIndicator(
                        modifier =
                            Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )

                    Spacer(
                        Modifier.width(8.dp)
                    )

                    Text("ENVIANDO...")
                } else {
                    Text(
                        "SOLICITAR PROPUESTA"
                    )
                }
            }
        }
    }
}