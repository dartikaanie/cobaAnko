package com.example.sub1.listPertandingan

import com.example.sub1.Model.EventResponse
import com.example.sub1.Model.MatchResponse

interface PertandinganContract {
    interface presenter {
        fun onDestroy()
        fun onRefreshButtonClick()
        fun requestDataFromServer()
    }

    interface EventView {
        fun showProgress()
        fun hideProgress()
        fun noDataResponse()
        fun SetDataMatch( events : MatchResponse)
        fun onResponseFailure(throwable: Throwable)
    }

    interface GetEvent {

        interface OnFinishedListener {
            fun onFinished(eventList: MatchResponse)
            fun onFailure(t: Throwable)
            fun noData()
        }

        fun getEvent(onFinishedListener: OnFinishedListener)
    }
}