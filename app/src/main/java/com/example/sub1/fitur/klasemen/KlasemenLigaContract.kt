package com.example.sub1.fitur.klasemen

import com.example.sub1.Model.klasemen.KlasemenResponse

interface KlasemenLigaContract {

    interface Presenter {
        fun onDestroy()
        fun onRefreshButtonClick()
        fun requestDataFromServer()
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun setDataToList(response: KlasemenResponse)
        fun onResponseFailure(throwable: Throwable)
    }

    interface GetDataIntractor {

        interface OnFinishedListener {
            fun onFinished(response: KlasemenResponse)
            fun onFailure(t: Throwable)
        }

        fun getData(onFinishedListener: OnFinishedListener)
    }

}