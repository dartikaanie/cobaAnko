package com.example.sub1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Item
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity(), (Item) -> Unit {


    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // generateLayout()

        val ligaAdapter: LigaAdapter = LigaAdapter(ArrayList<Item>(), this)
        MainActivityUI(ligaAdapter).setContentView(this)
        getData(ligaAdapter)
    }

    fun getData(adapter: LigaAdapter) {
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        val isi = resources.getStringArray(R.array.club_isi)
        items.clear()
        for (i in name.indices) {
            items.add(
                Item(name[i],
                    image.getResourceId(i, 0), isi[i])
            )
        }

        //Recycle the typed array
        image.recycle()

        adapter.update(items)

    }

    override fun invoke(liga: Item) {
        toast(liga.name.toString())
        val intent = Intent(this, DetailLigaActivity::class.java)
        intent.putExtra("liga", liga) // compilation error
        startActivity(intent)
    }
}

    class MainActivityUI (val listAdapter : LigaAdapter): AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) : View = with(ui) {
            return relativeLayout{
                padding = dip(16)
                lparams (width = matchParent, height = wrapContent)

                recyclerView{
                    lparams (width = matchParent, height = wrapContent)
                    layoutManager = GridLayoutManager(ctx, 2)
                    adapter = listAdapter
                }

            }
        }
    }

