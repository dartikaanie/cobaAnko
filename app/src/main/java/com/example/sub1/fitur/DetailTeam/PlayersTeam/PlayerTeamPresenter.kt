package com.example.sub1.fitur.DetailTeam.PlayersTeam

import com.example.sub1.Model.Player.PlayerResponse

class PlayerTeamPresenter(
    private var view: PlayersTeamContract.DetailPlayerTeamView?,
    getData: GetPlayerTeam
) : PlayersTeamContract.Presenter,
    PlayersTeamContract.GetPlayerTeamIntractor.OnFinishedListener {


    private var getData: PlayersTeamContract.GetPlayerTeamIntractor? = getData

    override fun onDestroy() {
        view = null
    }

    override fun onRefreshButtonClick() {
        view?.showProgress()
        getData?.getPlayerTeam(this)
    }

    override fun requestDataFromServer() {
        getData?.getPlayerTeam(this)
    }

    override fun onFinished(playerList: PlayerResponse) {
        view?.setDataTeam(playerList)
    }

    override fun onFailure(t: Throwable) {
        view?.onResponseFailure(t)
        view?.hideProgress()
    }


}

