package com.example.sub1.listPertandingan

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
        if(EventView != null){
            EventView!!.showProgress();
        }
        getEventsList?.getEvent(this)
    }

    override fun requestDataFromServer() {
        getEventsList?.getEvent(this)
    }

    override fun noData() {
        if(EventView != null){
            EventView!!.noDataResponse()
        }

    }

    override fun onFailure(t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFinished(eventList: MatchResponse) {
        EventView?.SetDataMatch(eventList)
    }



}