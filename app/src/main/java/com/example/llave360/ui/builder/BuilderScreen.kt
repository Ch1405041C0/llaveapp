package com.example.llave360.ui.builder

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.llave360.model.AppState
import com.example.llave360.model.Product
import com.example.llave360.model.productsFor
import com.example.llave360.ui.components.AppHeader
import com.example.llave360.ui.components.money

@Composable
fun BuilderScreen(
    state: AppState,
    add: (String) -> Unit,
    remove: (String) -> Unit,
    continueToConfirmation: () -> Unit,
    back: () -> Unit,
) {
    val catalog = productsFor(state.businessModel, state.businesses, state.products)
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    Column(Modifier.fillMaxSize()) {
        AppHeader("04 / ARMÁ TU PROPUESTA")
        Card(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp)) {
            Row(
                Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Text("Disponible", style = MaterialTheme.typography.labelMedium)
                    Text(
                        money(state.remaining),
                        color = if (state.remaining < 0) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Seleccionado", style = MaterialTheme.typography.labelMedium)
                    Text(money(state.total), fontWeight = FontWeight.Bold)
                }
            }
        }

        LazyColumn(
            Modifier.weight(1f).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            item {
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(state.businessModel.uppercase(), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                        Text(state.space, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text(
                            "Vista previa del espacio. El equipamiento que agregues se incluirá en tu propuesta.",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
            item {
                Text("Equipamiento", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("Tocá un producto para ver su detalle.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            items(catalog, key = { it.id }) { product ->
                ProductCard(
                    product = product,
                    quantity = state.quantities[product.id] ?: 0,
                    add = add,
                    remove = remove,
                    onOpenDetail = { selectedProduct = product },
                )
            }
        }

        Row(
            Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            OutlinedButton(onClick = back, modifier = Modifier.weight(1f)) { Text("VOLVER") }
            Button(
                onClick = continueToConfirmation,
                modifier = Modifier.weight(1f),
                enabled = state.canContinue,
            ) { Text("VER REMITO") }
        }
    }

    selectedProduct?.let { product ->
        ProductDetailDialog(product = product, onDismiss = { selectedProduct = null })
    }
}

@Composable
private fun ProductCard(
    product: Product,
    quantity: Int,
    add: (String) -> Unit,
    remove: (String) -> Unit,
    onOpenDetail: () -> Unit,
) {
    Card(
        Modifier.fillMaxWidth().clickable(onClick = onOpenDetail),
    ) {
        Row(
            Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            if (product.imageUrl.isNotBlank()) {
                Card(shape = RoundedCornerShape(12.dp)) {
                    AsyncImage(
                        model = product.imageUrl,
                        contentDescription = product.name,
                        modifier = Modifier.size(84.dp),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Column(Modifier.weight(1f)) {
                Text(product.category.uppercase(), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                Text(product.name, fontWeight = FontWeight.Bold)
                Text(product.description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 2)
                Text(money(product.price), fontWeight = FontWeight.SemiBold)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { remove(product.id) },
                    enabled = quantity > 0,
                ) { Text("−", style = MaterialTheme.typography.titleLarge) }
                Text(quantity.toString(), fontWeight = FontWeight.Bold)
                IconButton(onClick = { add(product.id) }) { Text("+", style = MaterialTheme.typography.titleLarge) }
            }
        }
    }
}

@Composable
private fun ProductDetailDialog(product: Product, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onDismiss) { Text("VOLVER") }
        },
        title = {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(product.category.uppercase(), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                Text(product.name, fontWeight = FontWeight.Bold)
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                if (product.imageUrl.isNotBlank()) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        AsyncImage(
                            model = product.imageUrl,
                            contentDescription = product.name,
                            modifier = Modifier.fillMaxWidth().heightIn(min = 220.dp, max = 360.dp),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
                Text(product.description, style = MaterialTheme.typography.bodyLarge)
                Text(money(product.price), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }
        },
    )
}
