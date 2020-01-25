package com.example.sub1

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sub1.Detail.DetailLigaActivity
import com.example.sub1.Model.Liga
import com.example.sub1.adapter.LigaAdapter
import com.example.sub1.listPertandingan.PertandinganActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView


class MainActivity : AppCompatActivity(), (Liga) -> Unit {


    private var items: MutableList<Liga> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // generateLayout()

        val ligaAdapter: LigaAdapter =
            LigaAdapter(ArrayList<Liga>(), this)
        MainActivityUI(ligaAdapter).setContentView(this)
        if(CekKoneksi().cekInternet(this)){
            getData(ligaAdapter)
        }else{
            toast("Hidupkan koneksi Internet Anda")
        }
//        val toolbar: Toolbar = find(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)

    }

    fun getData(adapter: LigaAdapter) {
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.getStringArray(R.array.club_image)
        val id = resources.getStringArray(R.array.club_id)
        items.clear()
        for (i in name.indices) {
            items.add(
                Liga(id[i],name[i], image[i])
            )
        }

        adapter.update(items)

    }



    override fun invoke(liga: Liga) {
        toast(liga.name.toString())
        startActivity(intentFor<DetailLigaActivity>("liga" to liga))
    }
}

    class MainActivityUI (val listAdapter : LigaAdapter): AnkoComponent<MainActivity> {

        @SuppressLint("NewApi")
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
            verticalLayout {
                appBarLayout {
                    lparams(width = matchParent, height = wrapContent)

                    toolbar {
                        id = R.id.toolbar
                        title = "LIGA DUNIA"
                        setTitleTextColor(Color.WHITE)
                        lparams(width = matchParent, height = wrapContent)

                        menu.apply {
                            add("Cari Pertandingan").apply {
                                setIcon(R.drawable.ic_launcher_background)
                                setOnMenuItemClickListener {
                                    startActivity<PertandinganActivity>()
                                    true
                                }
                            }

                        }
                    }
                }
//             lparams(width = matchParent, height = wrapContent)

                recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = GridLayoutManager(ctx, 2)
                    adapter = listAdapter
                }
            }

//
        }
    }



