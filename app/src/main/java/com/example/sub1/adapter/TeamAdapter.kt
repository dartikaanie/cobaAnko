package com.example.sub1.adapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
        val namaTeam : TextView = itemView.findViewById(R.id.nama_tim) as TextView
        val deskripsi : TextView = itemView.findViewById(R.id.deskripsi_team) as TextView

        fun bind(item: TeamsItem, clickListener: (TeamsItem) -> Unit){
            if(item.strTeamLogo != null){
                val options = RequestOptions()
                    .override(200,200)
                    .placeholder(R.drawable.img)
                    .error(R.drawable.img)
                Glide.with(itemView.context).load(item.strTeamLogo).apply(options).into(img)
                itemView.setOnClickListener { clickListener(item)}
            }else{
                img.setImageResource(R.drawable.img)
            }
            namaTeam.text = item.strTeam
            deskripsi.text = item.strDescriptionEN?.substring(0,100) +" ..."
        }
    }

    interface RecyclerItemClickListener {
        fun onItemClick(teamsItem: TeamsItem)
    }

}