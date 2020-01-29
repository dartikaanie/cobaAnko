package com.example.sub1.fitur.listPertandingan

import android.util.Log
import com.example.sub1.Model.MatchResponse

class PertandinganPresenter(
    private var EventView: PertandinganContract.EventView?,
    getEventsList: PertandinganContract.GetEvent
) : PertandinganContract.presenter,
    PertandinganContract.GetEvent.OnFinishedListener {

    private var getEventsList: PertandinganContract.GetEvent? = getEventsList

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