package com.example.sub1.fitur.TeamsLiga

import com.example.sub1.Model.TeamList

class TeamsLigaPresenter(
    private var teamsLigaView: TeamsLigaContract.TeamsLigaView?,
    getTeamList: TeamsLigaContract.GetTeamIntractor
) : TeamsLigaContract.Presenter,
    TeamsLigaContract.GetTeamIntractor.OnFinishedListener {

    private var getTeamList: TeamsLigaContract.GetTeamIntractor? = getTeamList

    override fun onDestroy() {
        teamsLigaView = null
    }

    override fun onRefreshButtonClick() {
        teamsLigaView?.showProgress()
        getTeamList?.getTeamList(this)
    }

    override fun requestDataFromServer() {
        getTeamList?.getTeamList(this)
    }

    override fun onFinished(teamList: TeamList) {
        teamsLigaView?.setDataToList(teamList)
        teamsLigaView?.hideProgress()
    }


    override fun onFailure(t: Throwable) {
        teamsLigaView?.onResponseFailure(t)
        teamsLigaView?.hideProgress()
    }


}

