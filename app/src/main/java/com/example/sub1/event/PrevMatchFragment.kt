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
import com.example.sub1.DetailEvent.DetailEventActivity
import com.example.sub1.Model.EventResponse
import com.example.sub1.Model.EventsItem
import com.example.sub1.R
import com.example.sub1.adapter.EventAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

class PrevMatchFragment : Fragment(),  AnkoComponent<Context>,  EventListContract.EventView {

    private lateinit var presenter: EventPresenter
    private lateinit var adapter: EventAdapter
    private lateinit var detailActivity: DetailLigaActivity
    private lateinit var progressBar: ProgressBar
    lateinit var rvPrev : RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        detailActivity = context as DetailLigaActivity
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = EventPresenter(this, GetPrevMatch(detailActivity.liga.id.toString()))
        presenter.requestDataFromServer()
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        verticalLayout {
            rvPrev = recyclerView {
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

    override fun setDataEvents(events: EventResponse?) {
        val eventsList: MutableList<EventsItem> = mutableListOf()
        events?.events?.let { eventsList.addAll(it) }
        adapter = EventAdapter(eventsList, {
            eventsItem: EventsItem -> EventClicked(eventsItem)
        })
        rvPrev.adapter = adapter
    }


    override fun onResponseFailure(throwable: Throwable) {
        toast("Maaf Ada Kesalahan")
    }
}

//class PrevMatchFragmentUI : AnkoComponent<PrevMatchFragment> {
//
//    private lateinit var progressBar: ProgressBar
//    override fun createView(ui: AnkoContext<PrevMatchFragment>): View = with(ui) {
//        verticalLayout {
//            recyclerView {
//                id = R.id.prevRecycle
//
//                lparams {
//                    width = matchParent
//                    height = wrapContent
//                    rightMargin = dip(8)
//                    leftMargin = dip(8)
//                }
////                    adapter = eventNextAdapter
//                layoutManager =
//                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            }
//            progressBar = progressBar().lparams {
//                gravity = Gravity.CENTER_HORIZONTAL
//            }
//        }
//
//    }
//}

