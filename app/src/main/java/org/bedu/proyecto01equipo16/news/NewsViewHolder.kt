package org.bedu.proyecto01equipo16.news

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.bedu.proyecto01equipo16.R
import org.bedu.proyecto01equipo16.news.model.Articulos

class NewsViewHolder(view: View):RecyclerView.ViewHolder(view){

    val titulo = view.findViewById<TextView>(R.id.comunityTitle)
    val texto = view.findViewById<TextView>(R.id.comunityTexto)
    val url = view.findViewById<TextView>(R.id.comunityURL)
    val foto = view.findViewById<ImageView>(R.id.comunityImg)

    fun render(comunityModel: Articulos){
        titulo.text = comunityModel.titulo
        texto.text = comunityModel.descripcion
        url.text = comunityModel.url
        Glide.with(foto.context).load(comunityModel.foto).into(foto)
    }

}