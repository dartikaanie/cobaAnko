package com.example.sub1.Detail

import com.example.sub1.Model.EventResponse
import com.example.sub1.Model.Liga
import com.example.sub1.Model.TeamList

interface DetailContract {

    interface Presenter {
        fun onDestroy()
        fun onRefreshButtonClick()
        fun requestDataFromServer()
    }

    interface DetailView {
        fun showProgress()
        fun hideProgress()
        fun setDataToList(teamList: TeamList)
        fun setDetaiLiga ( liga: Liga)
        fun onResponseFailure(throwable: Throwable)
    }

    interface GetTeamIntractor {

        interface OnFinishedListener {
            fun onFinished(teamList: TeamList)
            fun onFailure(t: Throwable)
        }

        fun getTeamList(onFinishedListener: OnFinishedListener)
    }

    interface GetDetailIga{
        interface OnFinishedListener {
            fun onFinished(liga: Liga)
            fun onFailure(t: Throwable)
        }

        fun getDetail(onFinishedListener: OnFinishedListener)
    }


}