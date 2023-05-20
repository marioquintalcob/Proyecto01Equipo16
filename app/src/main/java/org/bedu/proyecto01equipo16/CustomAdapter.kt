package org.bedu.proyecto01equipo16


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class CustomAdapter(private val titles:ArrayList<workouts>): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout,viewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val currentItem = titles[i]
        viewHolder.tvHeading.text = details[i]
        viewHolder.titleImage.setImageResource(imagenejercicio[i])
    }
    override fun getItemCount(): Int{
        return titles.size
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var titleImage : ShapeableImageView = itemView.findViewById(R.id.title_image)
        val tvHeading : TextView = itemView.findViewById(R.id.tvHeading)
        }
    }
