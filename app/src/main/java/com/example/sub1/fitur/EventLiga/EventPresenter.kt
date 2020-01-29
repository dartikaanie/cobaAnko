package com.example.sub1.fitur.EventLiga

import com.example.sub1.Model.EventResponse

class EventPresenter(
    private var EventView: EventListContract.EventView?,
    getEventsList: EventListContract.GetMatch
) : EventListContract.Presenter,
    EventListContract.GetMatch.OnFinishedListener {

    private var getEventsList: EventListContract.GetMatch? = getEventsList

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