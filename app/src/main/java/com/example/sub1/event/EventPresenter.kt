package com.example.sub1.event

import com.example.sub1.Detail.DetailContract
import com.example.sub1.Detail.GetDetailLiga
import com.example.sub1.Model.EventResponse

class EventPresenter : EventListContract.presenter, EventListContract.GetNextMatch.OnFinishedListener, EventListContract.GetPrevMatch.OnFinishedListener {

    private var EventView: EventListContract.EventView? = null
    private var getEventsPrevList: EventListContract.GetPrevMatch? = null
    private var getEventsNextList: EventListContract.GetNextMatch? = null

    constructor(EventView: EventListContract.EventView?, getEventsNextList : EventListContract.GetNextMatch, getEventsPrevList : EventListContract.GetPrevMatch) {
        this.EventView = EventView
        this.getEventsPrevList = getEventsPrevList
        this.getEventsNextList = getEventsNextList
    }

    override fun onDestroy() {
       EventView = null
    }

    override fun onRefreshButtonClick() {
        if(EventView != null){
            EventView!!.showProgress();
        }
        getEventsNextList?.getNextMatch(this)
        getEventsPrevList?.getPrevMatch(this)
    }

    override fun requestDataFromServer() {
        getEventsNextList?.getNextMatch(this)
        getEventsPrevList?.getPrevMatch(this)
    }

    override fun onFailure(t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFinishedNext(eventList: EventResponse) {
        EventView?.setDataNextEvents(eventList)
    }

    override fun onFinishedPrev(eventList: EventResponse) {
        EventView?.setDataPrevEvents(eventList)
    }
}