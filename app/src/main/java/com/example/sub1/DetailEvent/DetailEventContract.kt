package com.example.sub1.DetailEvent

import com.example.sub1.Model.DetailEvent

interface DetailEventContract {

    interface Presenter {
        fun onDestroy()
        fun onRefreshButtonClick()
        fun requestDataFromServer()
    }

    interface DetailEventView {
        fun showProgress()
        fun hideProgress()
        fun setDataEvent ( event : DetailEvent)
        fun onResponseFailure(throwable: Throwable)
    }

    interface GetDetailEvent {
        interface OnFinishedListener {
            fun onFinished(detailEvent: DetailEvent)
            fun onFailure(t: Throwable)
        }
        fun getDetailEvent(onFinishedListener: OnFinishedListener)
    }
}