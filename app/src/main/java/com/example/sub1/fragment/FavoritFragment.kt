package com.example.sub1.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.sub1.Model.EventsItem
import com.example.sub1.Model.Favorite
import com.example.sub1.R
import com.example.sub1.adapter.page.FavoritePageAdapter
import com.example.sub1.fitur.DetailEvent.DetailEventActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.viewPager

class FavoritFragment : Fragment(), AnkoComponent<Context>, (Favorite) -> Unit {

    lateinit var mTabLayout : TabLayout
    lateinit var myViewPager : ViewPager

    override fun invoke(favorite: Favorite){
        val eventsItem = EventsItem()
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
            coordinatorLayout {
                lparams(matchParent, matchParent)

                appBarLayout {
                    lparams(matchParent, wrapContent)

                    mTabLayout = themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                        lparams(matchParent, wrapContent)
                        {
                            tabMode = TabLayout.MODE_FIXED
                        }
                    }
                }
                myViewPager = viewPager {
                    id = R.id.viewpager
                }.lparams(matchParent, matchParent)
                (myViewPager.layoutParams as CoordinatorLayout.LayoutParams).behavior = AppBarLayout.ScrollingViewBehavior()
                myViewPager.adapter =
                    FavoritePageAdapter(childFragmentManager)
                mTabLayout.setupWithViewPager(myViewPager)
            }

        }

    }



}

