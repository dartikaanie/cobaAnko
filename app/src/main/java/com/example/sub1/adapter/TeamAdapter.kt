package com.example.sub1.adapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sub1.Model.TeamsItem
import com.example.sub1.R
import com.example.sub1.View.TeamUI
import org.jetbrains.anko.AnkoContext




class TeamAdapter (private var teamList: List<TeamsItem>, private val clickListener: (TeamsItem) -> Unit)
    : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: TeamAdapter.ViewHolder, position: Int) {
        holder.bind(teamList[position], clickListener )
    }



    override fun getItemCount(): Int {
        return teamList.size
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



    fun update(dataList: List<TeamsItem>){
        teamList = dataList as MutableList<TeamsItem>
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.gambar) as ImageView

        fun bind(item: TeamsItem, clickListener: (TeamsItem) -> Unit){
            if(item.strTeamLogo != null){
                val options = RequestOptions()
                    .override(200,200)
                    .placeholder(R.drawable.img)
                    .error(R.drawable.img)
                Glide.with(itemView.context).load(item.strTeamLogo).apply(options).into(img)
                itemView.setOnClickListener { clickListener(item)}
            }
        }
    }

    interface RecyclerItemClickListener {
        fun onItemClick(teamsItem: TeamsItem)
    }

}