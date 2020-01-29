package com.example.sub1.fitur.klasemen

import com.example.sub1.Model.klasemen.KlasemenResponse

class KlasemenLigaPresenter(
    private var view: KlasemenLigaContract.View?,
    getDataList: KlasemenLigaContract.GetDataIntractor
) : KlasemenLigaContract.Presenter,
    KlasemenLigaContract.GetDataIntractor.OnFinishedListener {

    private var getDataList: KlasemenLigaContract.GetDataIntractor? = getDataList

    override fun onDestroy() {
        view = null
    }

    override fun onRefreshButtonClick() {
        view?.showProgress()
        getDataList?.getData(this)
    }

    override fun requestDataFromServer() {
        getDataList?.getData(this)
    }

    override fun onFinished(response: KlasemenResponse) {
        view?.setDataToList(response)
        view?.hideProgress()
    }


    override fun onFailure(t: Throwable) {
        view?.onResponseFailure(t)
        view?.hideProgress()
    }


}

