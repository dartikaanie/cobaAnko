package com.example.sub1.fitur.favorite

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.sub1.DatabaseHelper.database
import com.example.sub1.Model.FavoriteTeam
import com.example.sub1.Model.TeamsItem
import com.example.sub1.R
import com.example.sub1.Utils.invisible
import com.example.sub1.Utils.visible
import com.example.sub1.adapter.FavoriteTeamAdapter
import com.example.sub1.fitur.DetailTeam.DetailTeamActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoritTeamFragment : Fragment(), AnkoComponent<Context>, (FavoriteTeam) -> Unit {


    private var favorites: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: FavoriteTeamAdapter
    lateinit var recycle : RecyclerView
    lateinit var noData : TextView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        favoriteFramentUI = FavoriteFragmentUI()
        adapter = FavoriteTeamAdapter(favorites, this)
        recycle.adapter = adapter
        swipeRefresh.onRefresh {
            showFavorite()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    override fun invoke(favorite: FavoriteTeam){
        val item  = TeamsItem()
        item.idTeam = favorite.idTeam
        item.strTeam = favorite.strTeam
        item.strTeamLogo = favorite.strTeamLogo
        item.intFormedYear = favorite.intFormedYear
        item.strLeague = favorite.strLeague
        item.strDescriptionEN = favorite.strDescriptionEN
        startActivity(intentFor<DetailTeamActivity>("team" to item))
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
            noData = textView("Tidak Ada Team Favorite"){
                textColor = R.color.colorPrimary
                gravity = Gravity.CENTER_HORIZONTAL
            }

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.background_dark,
                    android.R.color.darker_gray,
                    android.R.color.holo_red_light
                )

                recycle = recyclerView {
                    id = R.id.recycle_fav
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }


            }
        }
    }

    private fun showFavorite(){
        favorites.clear()
        noData.invisible()
        swipeRefresh.isRefreshing = false
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
            if(favorites.size < 1)
                noData.visible()
        }
    }

}

