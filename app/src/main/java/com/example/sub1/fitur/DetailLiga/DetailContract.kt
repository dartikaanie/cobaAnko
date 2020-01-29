package com.example.sub1.fitur.DetailLiga

import com.example.sub1.Model.Liga

interface DetailContract {

    interface Presenter {
        fun onDestroy()
        fun onRefreshButtonClick()
        fun requestDataFromServer()
    }

    interface DetailView {
        fun showProgress()
        fun hideProgress()
        fun setDetaiLiga ( liga: Liga)
        fun onResponseFailure(throwable: Throwable)
    }



    interface GetDetailIga{
        interface OnFinishedListener {
            fun onFinished(liga: Liga)
            fun onFailure(t: Throwable)
        }

        fun getDetail(onFinishedListener: OnFinishedListener)
    }


}