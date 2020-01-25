package com.example.sub1.event

import com.example.sub1.Model.EventResponse

interface EventListContract {

    interface presenter {
        fun onDestroy()
        fun onRefreshButtonClick()
        fun requestDataFromServer()
    }

    interface EventView {
        fun showProgress()
        fun hideProgress()
        fun setDataEvents( events : EventResponse?)
        fun onResponseFailure(throwable: Throwable)
    }

    interface GetMatch {

        interface OnFinishedListener {
            fun onFinished(eventList: EventResponse)
            fun onFailure(t: Throwable)
        }

        fun getMatch(onFinishedListener: OnFinishedListener)
    }

}