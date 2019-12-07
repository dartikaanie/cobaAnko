package com.example.sub1.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sub1.Detail.DetailLigaActivity
import com.example.sub1.Detail.GetTeam
import com.example.sub1.Model.EventsItem
import com.example.sub1.Model.TeamsItem
import com.example.sub1.R
import com.example.sub1.View.EventUI
import org.jetbrains.anko.AnkoContext
import java.text.SimpleDateFormat
import java.util.*


class EventAdapter(private var eventList: List<EventsItem>, private val  clickListener: (EventsItem) -> Unit)
    : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: EventAdapter.ViewHolder, position: Int) {
        holder?.bind(eventList[position], clickListener )
    }



    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EventUI().createView(
                AnkoContext.create(
                    parent!!.context
                )
            )
        )
    }



    fun update(dataList: List<EventsItem>){
        eventList = dataList as MutableList<EventsItem>
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val txtTitle: TextView = itemView?.findViewById(R.id.nama_event) as TextView
        val strSport: TextView = itemView?.findViewById(R.id.strSport) as TextView
        val dateEvent: TextView = itemView?.findViewById(R.id.dateEvent) as TextView
        val strHomeTeam: TextView = itemView?.findViewById(R.id.strHomeTeam) as TextView
        val gambarHome : ImageView = itemView?.findViewById(R.id.gambarHome) as ImageView

        val intHomeScore: TextView = itemView?.findViewById(R.id.intHomeScore) as TextView
        val intAwayScore: TextView = itemView?.findViewById(R.id.intAwayScore) as TextView
        val strAwayTeam: TextView = itemView?.findViewById(R.id.strAwayTeam) as TextView
        val gambarAway : ImageView = itemView?.findViewById(R.id.gambarAway) as ImageView

        fun bind(item: EventsItem, clickListener: (EventsItem) -> Unit){
         txtTitle.setText(item.strEvent)
         strSport.setText(item.strSport)

//        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
//        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
//        val time : String = dateFormat.parse(item.strTime).toString()

        dateEvent.setText(item.dateEventLocal + "  " + item.getStrTime())
        strHomeTeam.setText(item.strHomeTeam)

        if(item.intHomeScore != null){
          intHomeScore.setText(item.intHomeScore)
        }else{
           intHomeScore.setText(" - ")
        }
        if(item.imgHome != null){
            Glide.with(itemView.context).load(item?.imgHome).into(gambarHome)
        }else{
            gambarHome.setImageResource(R.drawable.img)
        }

        if(item.intAwayScore != null){
            intAwayScore.setText(item.intAwayScore)
        }else{
            intAwayScore.setText(" - ")
        }
        strAwayTeam.setText(item.strAwayTeam)

        if(item.imgAway != null){
            Glide.with(itemView.context).load(item?.imgAway).into(gambarAway)
        }else{
            gambarAway.setImageResource(R.drawable.img)
        }
            itemView.setOnClickListener { clickListener(item)}
        }
    }



/*





 */
}