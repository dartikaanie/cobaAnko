package com.example.sub1.fitur.listPertandingan

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sub1.MainActivity
import com.example.sub1.Model.EventsItem
import com.example.sub1.Model.MatchResponse
import com.example.sub1.R
import com.example.sub1.Utils.invisible
import com.example.sub1.Utils.visible
import com.example.sub1.adapter.EventAdapter
import com.example.sub1.fitur.DetailEvent.DetailEventActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class PertandinganActivity : AppCompatActivity(),
    PertandinganContract.EventView {


    lateinit var pertandinganActivityUI : PertandinganActivityUI
    lateinit var presenter : PertandinganPresenter
    private lateinit var adapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pertandinganActivityUI = PertandinganActivityUI()
        pertandinganActivityUI.setContentView(this)
        presenter = PertandinganPresenter(
            this,
            GetEvent("Arsenal_vs_Chelsea")
        )
        presenter.requestDataFromServer()

        pertandinganActivityUI.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            PertandinganContract.EventView {
            override fun noDataResponse() {
                pertandinganActivityUI.recycleMatch.invisible()
                toast("Data yang anda cari tidak ada")
            }

            override fun showProgress() {
                pertandinganActivityUI.progressBar.visible()
                pertandinganActivityUI.recycleMatch.invisible()
            }

            override fun hideProgress() {
                pertandinganActivityUI.progressBar.invisible()

            }

            override fun SetDataMatch(events: MatchResponse) {
                pertandinganActivityUI.recycleMatch.visible()
                    adapter = EventAdapter(events.event, { eventItem : EventsItem -> EventClicked(eventItem) })
                    pertandinganActivityUI.recycleMatch.adapter = adapter

            }

            override fun onResponseFailure(throwable: Throwable) {
                toast("Maaf ada kesalahan data")
            }

            override fun onQueryTextChange(newText: String): Boolean {

//                presenter = PertandinganPresenter(this, GetEvent(newText))
//                presenter.requestDataFromServer()

                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                presenter = PertandinganPresenter(
                    this,
                    GetEvent(query)
                )
                presenter.requestDataFromServer()
                return false
            }

        })

    }

    override fun showProgress() {
       pertandinganActivityUI.progressBar.visible()
    }

    override fun hideProgress() {
        pertandinganActivityUI.progressBar.invisible()
    }

    override fun SetDataMatch(events: MatchResponse) {
        adapter = EventAdapter(events.event, { eventItem : EventsItem -> EventClicked(eventItem) })
        pertandinganActivityUI.recycleMatch.adapter = adapter
        pertandinganActivityUI.progressBar.invisible()
    }

    private fun EventClicked(eventItem: EventsItem) {
        eventItem.strAwayTeam?.let { toast(it) }
        startActivity(intentFor<DetailEventActivity>("event" to eventItem))
    }

    override fun onResponseFailure(throwable: Throwable) {
        toast("Maaf Ada Kesalahan")
    }

    override fun noDataResponse() {
        toast("Data yang anda cari tidak ada")
    }
}

class PertandinganActivityUI : AnkoComponent<PertandinganActivity> {
    lateinit var recycleMatch : RecyclerView
    lateinit var searchView : SearchView
    lateinit var progressBar : ProgressBar
    lateinit var noData : TextView

    override fun createView(ui: AnkoContext<PertandinganActivity>): View = with(ui) {
        verticalLayout {
            appBarLayout {
                lparams(width = matchParent, height = wrapContent)

                toolbar {
                    id = R.id.toolbar
                    title = "Pertandingan"
                    setTitleTextColor(Color.WHITE)
                    lparams(width = matchParent, height = wrapContent)

                    menu.apply {
                        add("Liga").apply {
                            setIcon(R.drawable.ic_launcher_background)
                            setOnMenuItemClickListener {
                                startActivity<MainActivity>()
                                true
                            }
                        }

                    }
                }
            }
            cardView {
                searchView = searchView {
                    id = R.id.cariMatch
                    queryHint = "Cari . . ."
                }
            }.lparams(matchParent, wrapContent)

            recycleMatch = recyclerView {
                id = R.id.recycle_match
                lparams(width = matchParent, height = wrapContent)
                layoutManager = LinearLayoutManager(ctx)
            }
            progressBar = progressBar().lparams {
                id = R.id.progressBar_match
                gravity = Gravity.CENTER_HORIZONTAL
            }
        }

//
    }
}

