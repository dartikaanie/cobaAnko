package com.example.sub1.adapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sub1.Model.Player.PlayerItem
import com.example.sub1.Model.TeamsItem
import com.example.sub1.R
import com.example.sub1.View.TeamUI
import org.jetbrains.anko.AnkoContext


class PlayerAdapter (private var playerList: List<PlayerItem>, private val clickListener: (PlayerItem) -> Unit)
    : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: PlayerAdapter.ViewHolder, position: Int) {
        holder.bind(playerList[position], clickListener )
    }



    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TeamUI().createView(
                AnkoContext.create(
                    parent.context
                )
            )
        )
    }



    fun update(dataList: List<PlayerItem>){
        playerList = dataList as MutableList<PlayerItem>
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.gambar) as ImageView
        val nama : TextView = itemView.findViewById(R.id.nama_tim) as TextView
        val dateBorn : TextView = itemView.findViewById(R.id.deskripsi_team) as TextView

        fun bind(item: PlayerItem, clickListener: (PlayerItem) -> Unit){
            if(item.strThumb != null){
                val options = RequestOptions()
                    .override(200,200)
                    .placeholder(R.drawable.img)
                    .error(R.drawable.img)
                Glide.with(itemView.context).load(item.strThumb).apply(options).into(img)
                itemView.setOnClickListener { clickListener(item)}
            }else{
              img.setImageResource(R.drawable.img)
            }
            nama.text = item.strPlayer
            dateBorn.text = item.dateBorn
        }
    }

    interface RecyclerItemClickListener {
        fun onItemClick(teamsItem: TeamsItem)
    }

}