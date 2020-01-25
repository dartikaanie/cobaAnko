package com.example.sub1.listPertandingan

import android.util.Log
import com.example.sub1.Model.EventResponse
import com.example.sub1.Model.MatchResponse

class PertandinganPresenter : PertandinganContract.presenter, PertandinganContract.GetEvent.OnFinishedListener{

    private var EventView: PertandinganContract.EventView? = null
    private var getEventsList: PertandinganContract.GetEvent? = null

    constructor(EventView: PertandinganContract.EventView?, getEventsList : PertandinganContract.GetEvent) {
        this.EventView = EventView
        this.getEventsList = getEventsList
    }

    override fun onDestroy() {
        EventView = null
    }

    override fun onRefreshButtonClick() {
        EventView?.showProgress()
        getEventsList?.getEvent(this)
    }

    override fun requestDataFromServer() {
        getEventsList?.getEvent(this)
    }

    override fun noData() {
        EventView?.noDataResponse()

    }

    override fun onFailure(t: Throwable) {
       Log.e("Maaf Ada Kesalahan", t.toString())
    }

    override fun onFinished(eventList: MatchResponse) {
        EventView?.SetDataMatch(eventList)
    }



}