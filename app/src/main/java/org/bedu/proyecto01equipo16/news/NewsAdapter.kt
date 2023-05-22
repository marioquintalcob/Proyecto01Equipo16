package org.bedu.proyecto01equipo16.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bedu.proyecto01equipo16.R
import org.bedu.proyecto01equipo16.news.model.Articulos

class NewsAdapter(private val comunityList:List<Articulos>) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(layoutInflater.inflate(R.layout.card_news, parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = comunityList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = comunityList.size

}