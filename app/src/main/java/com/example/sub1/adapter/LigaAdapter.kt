package com.example.sub1.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sub1.Model.Liga
import com.example.sub1.R
import com.example.sub1.View.ItemUI
import org.jetbrains.anko.AnkoContext
import com.bumptech.glide.request.RequestOptions

class LigaAdapter (private var ligaList: List<Liga>, private val clickListener: (Liga) -> Unit)
    : RecyclerView.Adapter<LigaAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ligaList[position], clickListener )
    }

    override fun getItemCount(): Int {
        return ligaList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUI().createView(
                AnkoContext.create(
                    parent.context
                )
            )
        )
    }



    fun update(dataList: List<Liga>){
        ligaList = dataList as MutableList<Liga>
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.txtName) as TextView
        val img: ImageView = itemView.findViewById(R.id.gambar) as ImageView

        fun bind(item: Liga, clickListener: (Liga) -> Unit){
            val options = RequestOptions()
                .override(100,100)
                .placeholder(R.drawable.img)
                .error(R.drawable.img)
             Glide.with(itemView.context).load(item.image).apply(options).into(img)
//            Glide.with(itemView.context).load("https://www.thesportsdb.com/images/media/league/logo/r7q96i1557058508.png").thumbnail(0.5f).into(img)
            txtTitle.text = item.name

            itemView.setOnClickListener { clickListener(item)}
        }
    }


}