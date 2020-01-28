package com.example.sub1.fragment

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sub1.DatabaseHelper.database
import com.example.sub1.DetailEvent.DetailEventActivity
import com.example.sub1.Model.EventsItem
import com.example.sub1.Model.Favorite
import com.example.sub1.R
import com.example.sub1.Utils.invisible
import com.example.sub1.Utils.visible
import com.example.sub1.adapter.FavoriteAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.intentFor

class FavoritFragment : Fragment(), AnkoComponent<Context>, (Favorite) -> Unit {


    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteAdapter
    lateinit var recycleMatch : RecyclerView
    lateinit var noData : TextView
//    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        favoriteFramentUI = FavoriteFragmentUI()
        adapter = FavoriteAdapter(favorites, this)
        recycleMatch.adapter = adapter
//        swipeRefresh.onRefresh {
//            showFavorite()
//        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    override fun invoke(favorite: Favorite){
        val eventsItem : EventsItem = EventsItem()
        eventsItem.idEvent = favorite.idEvent
        eventsItem.strEvent =  favorite.strEvent
        eventsItem.imgHome = favorite.imgHome
        eventsItem.imgAway = favorite.imgAway
        startActivity(intentFor<DetailEventActivity>("event" to eventsItem))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        verticalLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
            noData = textView("Tidak Ada Pertandingan Favorite"){
                textColor = R.color.colorPrimary
                gravity = Gravity.CENTER_HORIZONTAL
            }

//            swipeRefresh = swipeRefreshLayout {
//                setColorSchemeResources(
//                    R.color.colorAccent,
//                    android.R.color.background_dark,
//                    android.R.color.darker_gray,
//                    android.R.color.holo_red_light
//                )

                recycleMatch = recyclerView {
                    id = R.id.recycle_fav
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }


//            }
        }
    }

    private fun showFavorite(){
        favorites.clear()
        noData.invisible()
//        swipeRefresh.isRefreshing = false
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
            if(favorites.size < 1)
                noData.visible()
        }
    }

}

