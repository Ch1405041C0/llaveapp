package com.example.llave360.ui.builder

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.llave360.model.AppState
import com.example.llave360.model.Product
import com.example.llave360.model.productsFor
import com.example.llave360.ui.components.AppHeader
import com.example.llave360.ui.components.money

@Composable fun BuilderScreen(state:AppState,add:(String)->Unit,remove:(String)->Unit,continueToConfirmation:()->Unit,back:()->Unit){
 val catalog=productsFor(state.businessModel,state.businesses,state.products)
 Column(Modifier.fillMaxSize()){AppHeader("04 / ARMÁ TU PROPUESTA")
  Card(Modifier.fillMaxWidth().padding(horizontal=16.dp,vertical=10.dp)){Row(Modifier.fillMaxWidth().padding(16.dp),horizontalArrangement=Arrangement.SpaceBetween){Column{Text("Disponible",style=MaterialTheme.typography.labelMedium);Text(money(state.remaining),color=if(state.remaining<0)MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,fontWeight=FontWeight.Bold)};Column(horizontalAlignment=Alignment.End){Text("Seleccionado",style=MaterialTheme.typography.labelMedium);Text(money(state.total),fontWeight=FontWeight.Bold)}}}
  LazyColumn(Modifier.weight(1f).padding(horizontal=16.dp),verticalArrangement=Arrangement.spacedBy(10.dp)){item{Card(Modifier.fillMaxWidth()){Column(Modifier.padding(20.dp),verticalArrangement=Arrangement.spacedBy(8.dp)){Text(state.businessModel.uppercase(),color=MaterialTheme.colorScheme.primary,fontWeight=FontWeight.Bold);Text(state.space,style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold);Text("Vista previa del espacio. El equipamiento que agregues se incluirá en tu propuesta.",color=MaterialTheme.colorScheme.onSurfaceVariant)}}};item{Text("Equipamiento",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold);Text("Sumá módulos hasta completar tu inversión.",color=MaterialTheme.colorScheme.onSurfaceVariant)};items(catalog,key={it.id}){product->ProductCard(product,state.quantities[product.id]?:0,add,remove)}}
  Row(Modifier.fillMaxWidth().padding(16.dp),horizontalArrangement=Arrangement.spacedBy(12.dp)){OutlinedButton(onClick=back,modifier=Modifier.weight(1f)){Text("VOLVER")};Button(onClick=continueToConfirmation,modifier=Modifier.weight(1f),enabled=state.canContinue){Text("VER REMITO")}}
 }}
@Composable private fun ProductCard(product:Product,quantity:Int,add:(String)->Unit,remove:(String)->Unit){Card(Modifier.fillMaxWidth()){Row(Modifier.fillMaxWidth().padding(16.dp),verticalAlignment=Alignment.CenterVertically){Column(Modifier.weight(1f)){Text(product.category.uppercase(),style=MaterialTheme.typography.labelSmall,color=MaterialTheme.colorScheme.primary);Text(product.name,fontWeight=FontWeight.Bold);Text(product.description,style=MaterialTheme.typography.bodySmall,color=MaterialTheme.colorScheme.onSurfaceVariant);Text(money(product.price),fontWeight=FontWeight.SemiBold)};Row(verticalAlignment=Alignment.CenterVertically){IconButton(onClick={remove(product.id)},enabled=quantity>0){Text("−",style=MaterialTheme.typography.titleLarge)};Text(quantity.toString(),fontWeight=FontWeight.Bold);IconButton(onClick={add(product.id)}){Text("+",style=MaterialTheme.typography.titleLarge)}}}}}
