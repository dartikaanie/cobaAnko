package com.example.sub1.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sub1.CekKoneksi
import com.example.sub1.Detail.DetailLigaActivity
import com.example.sub1.HomeActivity
import com.example.sub1.MainActivity
import com.example.sub1.Model.Liga
import com.example.sub1.R
import com.example.sub1.adapter.LigaAdapter
import com.example.sub1.listPertandingan.PertandinganActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast

class LigaFragment : Fragment(),  (Liga) -> Unit{

    private var items: MutableList<Liga> = mutableListOf()
    lateinit var homeActivity : HomeActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeActivity = context as HomeActivity
    }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val ligaAdapter: LigaAdapter =
                LigaAdapter(ArrayList<Liga>(), this)
            if(CekKoneksi().cekInternet(homeActivity)){
                getData(ligaAdapter)
            }else{
                toast("Hidupkan koneksi Internet Anda")
            }
        return LigaFragmentUI(ligaAdapter).createView(AnkoContext.create(requireContext(),this))
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

class LigaFragmentUI (val listAdapter : LigaAdapter): AnkoComponent<LigaFragment> {

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun createView(ui: AnkoContext<LigaFragment>): View = with(ui) {
        verticalLayout {
//            appBarLayout {
//                lparams(width = matchParent, height = wrapContent)
//
//                toolbar() {
//                    id = R.id.toolbar
//                    setTitle("LIGA DUNIA")
//                    setTitleTextColor(Color.WHITE)
//                    lparams(width = matchParent, height = wrapContent)
//
//                    menu.apply {
//                        add("Cari Pertandingan").apply {
//                            setIcon(R.drawable.ic_launcher_background)
//                            setOnMenuItemClickListener {
//                                startActivity<PertandinganActivity>()
//                                true
//                            }
//                        }
//
//                    }
//                }
//            }
//             lparams(width = matchParent, height = wrapContent)

            recyclerView {
                id = R.id.recycle_match
                lparams(width = matchParent, height = wrapContent)
                layoutManager = GridLayoutManager(ctx, 2)
                adapter = listAdapter
            }
        }

//
    }
}
