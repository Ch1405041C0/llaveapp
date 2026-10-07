package com.example.llave360.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.llave360.data.RemoteBusiness
import com.example.llave360.data.SupabaseCatalog
import com.example.llave360.model.AppState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {

    var state by mutableStateOf(AppState())
        private set

    var isWhatsAppPending by mutableStateOf(false)

    var remoteBusinesses by mutableStateOf<List<RemoteBusiness>>(emptyList())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val businesses = SupabaseCatalog.loadBusinesses()

                remoteBusinesses = businesses

                Log.d(
                    "LLAVE360_SUPABASE",
                    "Negocios recibidos = ${businesses.size}: ${businesses.joinToString { it.name }}"
                )
            } catch (e: Exception) {
                Log.e(
                    "LLAVE360_SUPABASE",
                    "Error consultando Supabase",
                    e
                )
            }
        }
    }

    fun selectModel(model: String) {
        state = state.copy(
            businessModel = model,
            quantities = emptyMap()
        )
    }

    fun selectSpace(space: String) {
        state = state.copy(space = space)
    }

    fun selectCapital(capital: Int) {
        state = state.copy(
            capital = capital,
            quantities = emptyMap()
        )
    }

    fun updateIdeaBrief(brief: String) {
        state = state.copy(ideaBrief = brief)
    }

    fun addProduct(id: String) {
        state = state.copy(
            quantities = state.quantities.toMutableMap().also {
                it[id] = (it[id] ?: 0) + 1
            }
        )
    }

    fun removeProduct(id: String) {
        val quantities = state.quantities.toMutableMap()
        val quantity = quantities[id] ?: return

        if (quantity == 1) {
            quantities.remove(id)
        } else {
            quantities[id] = quantity - 1
        }

        state = state.copy(quantities = quantities)
    }

    fun resetState() {
        state = AppState()
        isWhatsAppPending = false
    }
}