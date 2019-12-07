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


class LigaAdapter (private var ligaList: List<Liga>, private val clickListener: (Liga) -> Unit)
    : RecyclerView.Adapter<LigaAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.bind(ligaList[position], clickListener )
    }

    override fun getItemCount(): Int {
        return ligaList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUI().createView(
                AnkoContext.create(
                    parent!!.context
                )
            )
        )
    }



    fun update(dataList: List<Liga>){
        ligaList = dataList as MutableList<Liga>
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val txtTitle: TextView = itemView?.findViewById(R.id.txtName) as TextView
        val img: ImageView = itemView?.findViewById(R.id.gambar) as ImageView

        fun bind(item: Liga, clickListener: (Liga) -> Unit){
            Glide.with(itemView.context).load(item.image).into(img)
            txtTitle.setText(item.name)

            itemView.setOnClickListener { clickListener(item)}
        }
    }


}