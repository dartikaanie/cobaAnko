package com.example.sub1.adapter
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sub1.Model.klasemen.TableItem
import com.example.sub1.R
import com.example.sub1.View.KlasemenUI
import org.jetbrains.anko.AnkoContext


class KlasemenAdapter (private var klasemenList: List<TableItem>)
    : RecyclerView.Adapter<KlasemenAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: KlasemenAdapter.ViewHolder, position: Int) {
        holder.bind(klasemenList[position])
    }



    override fun getItemCount(): Int {
        return klasemenList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            KlasemenUI().createView(
                AnkoContext.create(
                    parent.context
                )
            )
        )
    }



    fun update(dataList: List<TableItem>){
        klasemenList = dataList as MutableList<TableItem>
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama : TextView = itemView.findViewById(R.id.nama) as TextView
        val played : TextView = itemView.findViewById(R.id.played) as TextView
        val goalsfor : TextView = itemView.findViewById(R.id.goalsfor) as TextView
        val loss : TextView = itemView.findViewById(R.id.loss) as TextView
        val win : TextView = itemView.findViewById(R.id.win) as TextView
        val draw : TextView = itemView.findViewById(R.id.draw) as TextView
        fun bind(item: TableItem){
            nama.text = item.name
            played.text = item.played.toString()
            goalsfor.text = item.goalsfor.toString()
            loss.text = item.loss.toString()
            win.text = item.win.toString()
            draw.text = item.draw.toString()

        }
    }


}