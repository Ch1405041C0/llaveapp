package com.example.llave360.ui.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.llave360.R
import com.example.llave360.model.AppState
import com.example.llave360.ui.components.AppHeader

private fun localImage(name:String)=when(name){"Kiosco 360"->R.drawable.model_kiosko;"Coffee Point"->R.drawable.model_coffee;"Barber 360"->R.drawable.model_barber;"Pet Point"->R.drawable.model_pet;else->null}

@Composable fun ModelScreen(state:AppState,select:(String)->Unit,updateIdea:(String)->Unit,continueToCapital:()->Unit,back:()->Unit){
 Column(Modifier.fillMaxSize()){AppHeader("01 / MODELO");Column(Modifier.fillMaxSize().padding(24.dp),verticalArrangement=Arrangement.spacedBy(12.dp)){
  Text("¿Qué negocio querés abrir?",style=MaterialTheme.typography.headlineMedium,fontWeight=FontWeight.Bold)
  if(state.catalogLoading) LinearProgressIndicator(Modifier.fillMaxWidth())
  if(state.catalogError.isNotEmpty()) Text("No se pudo cargar el catálogo: ${state.catalogError}",color=MaterialTheme.colorScheme.error)
  LazyColumn(Modifier.weight(1f),verticalArrangement=Arrangement.spacedBy(12.dp)){items(state.businesses,key={it.id}){business->Card(Modifier.fillMaxWidth().selectable(selected=state.businessModel==business.name,onClick={select(business.name)},role=Role.RadioButton)){Column{localImage(business.name)?.let{Image(painterResource(it),business.name,Modifier.fillMaxWidth(),contentScale=ContentScale.Crop)};Column(Modifier.padding(18.dp),verticalArrangement=Arrangement.spacedBy(6.dp)){Text(business.name,style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold,color=if(state.businessModel==business.name)MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface);Text(business.description,color=MaterialTheme.colorScheme.onSurfaceVariant)}}}}}
  Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(12.dp)){OutlinedButton(back,Modifier.weight(1f)){Text("VOLVER")};Button(continueToCapital,Modifier.weight(1f),enabled=state.businessModel.isNotEmpty()){Text("CONTINUAR")}}
 }}
}
