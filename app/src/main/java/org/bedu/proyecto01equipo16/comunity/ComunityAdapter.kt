package org.bedu.proyecto01equipo16.comunity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bedu.proyecto01equipo16.R

class ComunityAdapter(private val comunityList:List<Articulos>) : RecyclerView.Adapter<ComunityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComunityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ComunityViewHolder(layoutInflater.inflate(R.layout.card_comunity, parent, false))
    }

    override fun onBindViewHolder(holder: ComunityViewHolder, position: Int) {
        val item = comunityList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = comunityList.size

}