package com.aguilar.practicateoria

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aguilar.practicateoria.adapter.MascotaAdapter
import com.aguilar.practicateoria.model.Mascota
import com.aguilar.practicateoria.viewModel.MascotasViewModel
import androidx.fragment.app.viewModels



class MascotasFragment : Fragment() {

    private val viewModel: MascotasViewModel by viewModels()
    private lateinit var reciclerView: RecyclerView
    private lateinit var progressVar: View
    private lateinit var adapter: MascotaAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_mascotas, container, false)
        reciclerView = view.findViewById(R.id.recyclerView)
        reciclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MascotaAdapter(emptyList())
        reciclerView.adapter=adapter
        observaViewModel()
        progressVar = view.findViewById(R.id.progress_bar)
        return view
    }
    private fun observaViewModel(){
        viewModel.mascotas.observe(viewLifecycleOwner){mascotas ->
            adapter.actualizarDatos(mascotas)
        }
        viewModel.isLoading.observe(viewLifecycleOwner){
            isLoading -> progressVar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }


}