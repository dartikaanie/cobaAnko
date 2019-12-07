package com.example.sub1.Detail

import com.example.sub1.Model.EventResponse
import com.example.sub1.Model.Liga
import com.example.sub1.Model.TeamList

class DetailPresenter : DetailContract.presenter, DetailContract.GetTeamIntractor.OnFinishedListener, DetailContract.GetDetailIga.OnFinishedListener {



    private var detailView: DetailContract.DetailView? = null
    private var getTeamList: DetailContract.GetTeamIntractor? = null
    private var getDetailLiga: DetailContract.GetDetailIga? = null

    constructor(detailView: DetailContract.DetailView?, getTeamList: DetailContract.GetTeamIntractor, getDetailLiga: GetDetailLiga) {
        this.detailView = detailView
        this.getTeamList = getTeamList
        this.getDetailLiga = getDetailLiga
    }

    override fun onDestroy() {
        detailView = null;
    }

    override fun onRefreshButtonClick() {
        if(detailView != null){
            detailView!!.showProgress();
        }
        getTeamList?.getTeamList(this)
        getTeamList?.getTeamList(this)
        getDetailLiga?.getDetail(this)
    }

    override fun requestDataFromServer() {
        getTeamList?.getTeamList(this)
        getDetailLiga?.getDetail(this)
    }

    override fun onFinished(liga: Liga) {
        detailView?.setDetaiLiga(liga)
    }

    override fun onFinished(teamList: TeamList) {
        detailView?.setDataToList(teamList);
        detailView?.hideProgress();
    }


    override fun onFailure(t: Throwable) {
        detailView?.onResponseFailure(t);
        detailView?.hideProgress();
    }


}

