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
        fun setDataNextEvents( events : EventResponse)
        fun setDataPrevEvents( events : EventResponse)
        fun onResponseFailure(throwable: Throwable)
    }

    interface GetNextMatch {

        interface OnFinishedListener {
            fun onFinishedNext(eventList: EventResponse)
            fun onFailure(t: Throwable)
        }

        fun getNextMatch(onFinishedListener: OnFinishedListener)
    }

    interface GetPrevMatch {

        interface OnFinishedListener {
            fun onFinishedPrev(eventList: EventResponse)
            fun onFailure(t: Throwable)
        }

        fun getPrevMatch(onFinishedListener: OnFinishedListener)
    }
}