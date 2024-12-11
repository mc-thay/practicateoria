package com.aguilar.practicateoria.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aguilar.practicateoria.model.Mascota
import com.google.firebase.firestore.FirebaseFirestore

class MascotasViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _mascotas = MutableLiveData<List<Mascota>>()
    val mascotas: LiveData<List<Mascota>> get() = _mascotas
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        recuperarMascotas()
    }

    fun recuperarMascotas() {
        _isLoading.value = true
        db.collection("mascotas")
            .get()
            .addOnSuccessListener { result ->
                val listaMascotas = result.map { document ->
                    Mascota(
                        nombre = document.getString("nombre") ?: "",
                        color = document.getString("color") ?: "",
                        raza = document.getString( "raza") ?: "",
                        tamano = document.getString("tamano") ?: "",
                        edad = document.getLong( "edad")?.toInt() ?: 0,
                        foto = document.getString("foto") ?: ""
                    )
                }
                _mascotas.value = listaMascotas
                _isLoading.value = false
            }
            .addOnFailureListener{
                _mascotas.value= emptyList()
                _isLoading.value = false
            }
    }
}
