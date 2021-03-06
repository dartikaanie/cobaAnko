package com.example.sub1.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sub1.Model.Favorite
import com.example.sub1.R
import com.example.sub1.View.EventUI
import org.jetbrains.anko.AnkoContext

class FavoriteAdapter(private var favoriteList: List<Favorite>, private val  clickListener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        holder.bindItem(favoriteList[position], clickListener)
    }


    override fun getItemCount(): Int {
        return favoriteList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EventUI().createView(
                AnkoContext.create(
                    parent.context
                )
            )
        )
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.nama_event) as TextView
        val strSport: TextView = itemView.findViewById(R.id.strSport) as TextView
        val dateEvent: TextView = itemView.findViewById(R.id.dateEvent) as TextView
        val strHomeTeam: TextView = itemView.findViewById(R.id.strHomeTeam) as TextView
        val gambarHome : ImageView = itemView.findViewById(R.id.gambarHome) as ImageView

        val intHomeScore: TextView = itemView.findViewById(R.id.intHomeScore) as TextView
        val intAwayScore: TextView = itemView.findViewById(R.id.intAwayScore) as TextView
        val strAwayTeam: TextView = itemView.findViewById(R.id.strAwayTeam) as TextView
        val gambarAway : ImageView = itemView.findViewById(R.id.gambarAway) as ImageView

        fun bindItem(item: Favorite, clickListener: (Favorite) -> Unit){
            txtTitle.text = item.strEvent
            strSport.text = item.strSport

            dateEvent.text = item.dateEventLocal + "  " + item.getStrTime()
            strHomeTeam.text = item.strHomeTeam

            val options = RequestOptions()
                .override(100,100)
                .placeholder(R.drawable.img)
                .error(R.drawable.img)

            intHomeScore.text = item.getScoreHome()
            Glide.with(itemView.context).load(item.imgHome).apply(options).into(gambarHome)
            intAwayScore.text = item.getScoreAway()
            strAwayTeam.text = item.strAwayTeam
            Glide.with(itemView.context).load(item.imgAway).apply(options).into(gambarAway)
            itemView.setOnClickListener { clickListener(item)}
        }
    }
}