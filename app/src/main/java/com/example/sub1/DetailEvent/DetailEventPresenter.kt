package com.example.sub1.DetailEvent

import com.example.sub1.Detail.DetailContract
import com.example.sub1.Model.DetailEvent
import com.example.sub1.Model.TeamList
import com.example.sub1.event.EventListContract

class DetailEventPresenter : DetailEventContract.Presenter, DetailEventContract.GetDetailEvent.OnFinishedListener  {

    private var detailEventView : DetailEventContract.DetailEventView? = null
    private var getDetailEvent: DetailEventContract.GetDetailEvent? = null

    constructor(detailEventView: DetailEventContract.DetailEventView, getDetailEvent: DetailEventContract.GetDetailEvent) {
        this.detailEventView = detailEventView
        this.getDetailEvent = getDetailEvent
    }

    override fun onDestroy() {

    }

    override fun onRefreshButtonClick() {
        detailEventView?.showProgress()
        getDetailEvent?.getDetailEvent(this)

    }

    override fun requestDataFromServer() {
        getDetailEvent?.getDetailEvent(this)
    }

    override fun onFinished(detailEvent: DetailEvent) {
        detailEventView?.setDataEvent(detailEvent)
        detailEventView?.hideProgress()
    }

    override fun onFailure(t: Throwable) {
        detailEventView?.onResponseFailure(t)
        detailEventView?.hideProgress()
    }


}