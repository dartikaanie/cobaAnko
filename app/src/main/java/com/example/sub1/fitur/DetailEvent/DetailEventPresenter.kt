package com.example.sub1.fitur.DetailEvent

import com.example.sub1.Model.DetailEvent

class DetailEventPresenter(
    detailEventView: DetailEventContract.DetailEventView,
    getDetailEvent: DetailEventContract.GetDetailEvent
) : DetailEventContract.Presenter,
    DetailEventContract.GetDetailEvent.OnFinishedListener {

    private var detailEventView : DetailEventContract.DetailEventView? = detailEventView
    private var getDetailEvent: DetailEventContract.GetDetailEvent? = getDetailEvent

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