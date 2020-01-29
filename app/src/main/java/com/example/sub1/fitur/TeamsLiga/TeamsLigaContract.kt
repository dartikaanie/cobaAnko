package com.example.sub1.fitur.TeamsLiga

import com.example.sub1.Model.TeamList

interface TeamsLigaContract {

    interface Presenter {
        fun onDestroy()
        fun onRefreshButtonClick()
        fun requestDataFromServer()
    }

    interface TeamsLigaView {
        fun showProgress()
        fun hideProgress()
        fun setDataToList(teamList: TeamList)
        fun onResponseFailure(throwable: Throwable)
    }

    interface GetTeamIntractor {

        interface OnFinishedListener {
            fun onFinished(teamList: TeamList)
            fun onFailure(t: Throwable)
        }

        fun getTeamList(onFinishedListener: OnFinishedListener)
    }

}