package com.example.sub1.Detail

import com.example.sub1.Model.Liga
import com.example.sub1.Model.LigaResponse
import com.example.sub1.rest.LigaDataServices
import com.example.sub1.rest.RetrofitInstances
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetDetailLiga (val idLiga : String)  :  DetailContract.GetDetailIga {
    lateinit var ligaDataService: LigaDataServices
    override fun getDetail(onFinishedListener: DetailContract.GetDetailIga.OnFinishedListener) {
        ligaDataService = RetrofitInstances.getClient().create(LigaDataServices::class.java)

        val call = ligaDataService.getDetailLiga(idLiga)

        /**Log the URL called*/


        call.enqueue(object : Callback<LigaResponse> {
            override fun onFailure(call: Call<LigaResponse>?, t: Throwable?) {
                t?.let { onFinishedListener.onFailure(it) }
            }

            override fun onResponse(call: Call<LigaResponse>?, response: Response<LigaResponse>?) {
                var liga : LigaResponse? = response?.body()
                var ligaItem : Liga? = liga?.ligaList?.get(0)
                ligaItem?.let { onFinishedListener.onFinished(it) }
            }
        })
    }
}
