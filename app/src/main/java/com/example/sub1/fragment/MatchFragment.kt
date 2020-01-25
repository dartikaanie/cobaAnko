package com.example.sub1.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sub1.DetailEvent.DetailEventActivity
import com.example.sub1.MainActivity
import com.example.sub1.Model.EventsItem
import com.example.sub1.Model.MatchResponse
import com.example.sub1.R
import com.example.sub1.Utils.invisible
import com.example.sub1.Utils.visible
import com.example.sub1.adapter.EventAdapter
import com.example.sub1.listPertandingan.*
import kotlinx.android.synthetic.*
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast



class MatchFragment : Fragment(), PertandinganContract.EventView{

    lateinit var matchFragmentUI: MatchFragmentUI
    lateinit var presenter : PertandinganPresenter
    lateinit var searchView : SearchView
    lateinit var progressBar : ProgressBar
    lateinit var rvMatch : RecyclerView
    private lateinit var adapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        progressBar = find<ProgressBar>(R.id.progressBar)
        presenter = PertandinganPresenter(this, GetEvent("Arsenal_vs_Chelsea"))
        presenter.requestDataFromServer()


        return MatchFragmentUI().createView(AnkoContext.create(requireContext(),this))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        view.let { super.onViewCreated(it, savedInstanceState) }

        rvMatch = find<RecyclerView>(R.id.recycleMatch)
        val manager = LinearLayoutManager(context)
        rvMatch.setLayoutManager(manager)
        rvMatch.setHasFixedSize(true)
        searchView = find<SearchView>(R.id.searchMatch)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            PertandinganContract.EventView {
            override fun noDataResponse() {
                rvMatch.invisible()
                toast("Data yang anda cari tidak ada")
            }

            override fun showProgress() {
                progressBar.visible()
                rvMatch.invisible()
            }

            override fun hideProgress() {
                progressBar.invisible()

            }

            override fun SetDataMatch(events: MatchResponse) {
                rvMatch.visible()
                adapter = EventAdapter(events.event, { eventItem : EventsItem -> EventClicked(eventItem) })
                rvMatch.adapter = adapter

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
                presenter = PertandinganPresenter(this, GetEvent(query))
                presenter.requestDataFromServer()
                return false
            }

        })
    }



    override fun showProgress() {
        progressBar.visible()
    }

    override fun hideProgress() {
        progressBar.invisible()
    }

    override fun SetDataMatch(events: MatchResponse) {
        adapter = EventAdapter(events.event, { eventItem : EventsItem -> EventClicked(eventItem) })

        rvMatch.adapter = adapter
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


class MatchFragmentUI : AnkoComponent<MatchFragment> {
    override fun createView(ui: AnkoContext<MatchFragment>): View = with(ui) {
        verticalLayout {

                cardView {
                    searchView {
                        id = R.id.searchMatch

                        queryHint = "Cari . . ."
                    }
                    padding = dip(10)
                }.lparams{
                    width = matchParent
                    height = wrapContent
                }

                    recyclerView {
                        id = R.id.recycleMatch
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }


                    progressBar().lparams {
                        id = R.id.progressBar
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
                }
        }

//
    }
