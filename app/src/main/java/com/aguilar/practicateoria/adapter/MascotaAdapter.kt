package com.aguilar.practicateoria.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aguilar.practicateoria.R
import com.aguilar.practicateoria.model.Mascota
import com.bumptech.glide.Glide

class MascotaAdapter (private var mascota: List<Mascota>):
    RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MascotaAdapter.MascotaViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.icon_mascotas, parent, false)
        return MascotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MascotaAdapter.MascotaViewHolder, position: Int) {
        val mascota = mascota[position]
        holder.bind(mascota)
    }

    override fun getItemCount(): Int = mascota.size

    fun actualizarDatos(nuevasMascota: List<Mascota>){
        this.mascota= nuevasMascota
        notifyDataSetChanged()
    }

    class MascotaViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        private val tvDescripcion: TextView = itemView.findViewById(R.id.tv_detalles)
        private val tvNombre: TextView = itemView.findViewById(R.id.tv_nombre)
        private val ivFoto: ImageView = itemView.findViewById(R.id.imageView)
        fun bind(mascota:Mascota){
            tvNombre.text = mascota.nombre
            tvDescripcion.text = "Raza: ${mascota.raza}, Tamaño: ${mascota.tamano}, Edad: ${mascota.edad}"

            Glide.with(itemView.context)
                .load(mascota.foto)
                .centerCrop()
                .into(ivFoto)


        }
    }


}
