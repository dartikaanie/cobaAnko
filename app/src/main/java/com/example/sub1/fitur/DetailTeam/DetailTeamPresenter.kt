package com.example.sub1.fitur.DetailTeam

import com.example.sub1.Model.TeamsItem

class DetailTeamPresenter : DetailTeamContract.Presenter,
    DetailTeamContract.GetDetailTeamIntractor.OnFinishedListener {



    private var detailView: DetailTeamContract.DetailTeamView? = null
    private var getDetailTeam: DetailTeamContract.GetDetailTeamIntractor? = null

    constructor(detailView: DetailTeamContract.DetailTeamView?, getDetailTeam: GetDetailTeam) {
        this.detailView = detailView
        this.getDetailTeam = getDetailTeam
    }

    override fun onDestroy() {
        detailView = null
    }

    override fun onRefreshButtonClick() {
        detailView?.showProgress()
        getDetailTeam?.getDetailTeam(this)
    }

    override fun requestDataFromServer() {
        getDetailTeam?.getDetailTeam(this)
    }

    override fun onFinished(team: TeamsItem) {
        detailView?.setDataTeam(team)
    }

    override fun onFailure(t: Throwable) {
        detailView?.onResponseFailure(t)
        detailView?.hideProgress()
    }


}

