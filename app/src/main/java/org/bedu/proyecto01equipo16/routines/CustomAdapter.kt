package org.bedu.proyecto01equipo16.routines


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.imageview.ShapeableImageView
import org.bedu.proyecto01equipo16.R
import org.bedu.proyecto01equipo16.routines.model.workouts

class CustomAdapter(private val titles: ArrayList<workouts>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    var like = false
    likeImageView.setOnClickListener{
        like = likeAnimation(likeImageView, R.raw.bandai_dokkan, like)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)

    }
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val currentItem = titles[i]
        viewHolder.tvHeading.text = details[i]
        viewHolder.title.text = nombreejercicio[i]
        viewHolder.titleImage.setImageResource(imagenejercicio[i])
    }

    override fun getItemCount(): Int {
        return titles.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleImage: ShapeableImageView = itemView.findViewById(R.id.title_image)
        val tvHeading: TextView = itemView.findViewById(R.id.tvHeading)
        val title: TextView = itemView.findViewById(R.id.title)
    }

    private fun likeAnimation(imageView: LottieAnimationView,
                              animation: Int,
                              like: Boolean) : Boolean {

        if (!like) {
            imageView.setAnimation(animation)
            imageView.playAnimation()
        } else {
            imageView.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animator: Animator) {

                        imageView.setImageResource(R.drawable.twitter_like)
                        imageView.alpha = 1f
                    }

                })

        }

        return !like
    }
}
