package com.example.sub1.event

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sub1.Detail.DetailLigaActivity
import com.example.sub1.Detail.DetailPresenter
import com.example.sub1.Detail.GetDetailLiga
import com.example.sub1.Detail.GetTeam
import com.example.sub1.DetailEvent.DetailEventActivity
import com.example.sub1.Model.EventResponse
import com.example.sub1.Model.EventsItem
import com.example.sub1.R
import com.example.sub1.adapter.EventAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

class PrevMatchFragment : Fragment(), EventListContract.EventView {

    private lateinit var presenter: EventPresenter
    private lateinit var adapter: EventAdapter
    lateinit var detailActivity: DetailLigaActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        detailActivity = context as DetailLigaActivity
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = EventPresenter(this, GetMatch(detailActivity.liga.id.toString()), GetMatch(detailActivity.liga.id.toString()))
        presenter.requestDataFromServer()
        return PrevMatchFragmentUI().createView(AnkoContext.create(requireContext(),this))
    }

    private fun EventClicked(eventItem : EventsItem) {
        eventItem.strAwayTeam?.let { toast(it) }
        startActivity(intentFor<DetailEventActivity>("event" to eventItem))
    }

    override fun showProgress() {
        Log.e("rshowProgress", "showProgress")
    }

    override fun hideProgress() {
        Log.e("hideProgress", "hideProgress")
    }

    override fun setDataNextEvents(events: EventResponse) {
    }

    override fun setDataPrevEvents(events: EventResponse) {
        adapter = events.events?.let { EventAdapter(it, { eventItem : EventsItem -> EventClicked(eventItem) }) }!!
        val rvMatch = find<RecyclerView>(R.id.prevRecycle)
        rvMatch.adapter = adapter
    }

    override fun onResponseFailure(throwable: Throwable) {
        toast("Maaf Ada Kesalahan")
    }
}

class PrevMatchFragmentUI : AnkoComponent<PrevMatchFragment> {

    private lateinit var progressBar: ProgressBar
    override fun createView(ui: AnkoContext<PrevMatchFragment>): View = with(ui) {
        verticalLayout() {
            recyclerView {
                id = R.id.prevRecycle

                lparams {
                    width = matchParent
                    height = wrapContent
                    rightMargin = dip(8)
                    leftMargin = dip(8)
                }
//                    adapter = eventNextAdapter
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
            progressBar = progressBar().lparams {
                gravity = Gravity.CENTER_HORIZONTAL
            }
        }

    }
}

