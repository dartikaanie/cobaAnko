package com.example.sub1.fitur.klasemen

import com.example.sub1.Model.klasemen.KlasemenResponse
import com.example.sub1.rest.LigaDataServices
import com.example.sub1.rest.RetrofitInstances
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetKlasemenLiga (val idLiga : String) :
    KlasemenLigaContract.GetDataIntractor {

    lateinit var ligaDataService: LigaDataServices

    override fun getData(onFinishedListener: KlasemenLigaContract.GetDataIntractor.OnFinishedListener) {
        ligaDataService = RetrofitInstances.getClient().create(LigaDataServices::class.java)

        val call = ligaDataService.getKlasemen(idLiga)

        call.enqueue(object : Callback<KlasemenResponse> {
            override fun onFailure(call: Call<KlasemenResponse>?, t: Throwable?) {
                t?.let { onFinishedListener.onFailure(it) }
            }

            override fun onResponse(call: Call<KlasemenResponse>?, response: Response<KlasemenResponse>?) {
                var response : KlasemenResponse? = response?.body()
                response?.let { onFinishedListener.onFinished(it) }
            }
        })
    }
}
