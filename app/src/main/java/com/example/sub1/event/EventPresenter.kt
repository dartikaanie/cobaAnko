package com.example.sub1.event

import android.util.Log
import com.example.sub1.Model.EventResponse

class EventPresenter : EventListContract.presenter,  EventListContract.GetMatch.OnFinishedListener {

    private var EventView: EventListContract.EventView? = null
    private var getEventsList: EventListContract.GetMatch? = null

    constructor(EventView: EventListContract.EventView?, getEventsList : EventListContract.GetMatch) {
        this.EventView = EventView
        this.getEventsList = getEventsList
    }

    override fun onDestroy() {
       EventView = null
    }

    override fun onRefreshButtonClick() {
        EventView?.showProgress()
        getEventsList?.getMatch(this)
    }

    override fun requestDataFromServer() {
        getEventsList?.getMatch(this)
    }

    override fun onFailure(t: Throwable) {
        EventView?.onResponseFailure(t)
    }

    override fun onFinished(eventList: EventResponse) {
        EventView?.setDataEvents(eventList)
    }

}