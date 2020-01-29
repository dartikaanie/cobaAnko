package com.example.sub1.fitur.TeamsGeneral

import com.example.sub1.Model.TeamList

class TeamsPresenter(
    private var view: TeamsContract.View?,
    getTeamList: TeamsContract.GetTeamIntractor
) : TeamsContract.Presenter,
    TeamsContract.GetTeamIntractor.OnFinishedListener {

    private var getTeamList: TeamsContract.GetTeamIntractor? = getTeamList

    override fun onDestroy() {
        view = null
    }

    override fun onRefreshButtonClick() {
        view?.showProgress()
        getTeamList?.getTeamList(this)
    }

    override fun requestDataFromServer() {
        getTeamList?.getTeamList(this)
    }

    override fun onFinished(teamList: TeamList) {
        view?.setDataToList(teamList)
        view?.hideProgress()
    }


    override fun onFailure(t: Throwable) {
        view?.onResponseFailure(t)
        view?.hideProgress()
    }


}

