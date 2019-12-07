package com.example.sub1.Detail

import android.util.Log
import com.example.sub1.DetailEvent.DetailEventContract
import com.example.sub1.Model.*
import com.example.sub1.rest.LigaDataServices
import com.example.sub1.rest.RetrofitInstances
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetDetailEvent (val idEvent: String):  DetailEventContract.GetDetailEvent {

    lateinit var ligaDataService: LigaDataServices;
    override fun getDetailEvent(onFinishedListener: DetailEventContract.GetDetailEvent.OnFinishedListener) {
        ligaDataService = RetrofitInstances.getClient()?.create(LigaDataServices::class.java)!!
        val call = ligaDataService.getDetailEvent(idEvent)

        Log.e("URL", call.request().url().toString())

        call.enqueue(object : Callback<DetailEventResponse> {
            override fun onFailure(call: Call<DetailEventResponse>?, t: Throwable?) {
                t?.let { onFinishedListener.onFailure(it) };
                Log.e("URL", t.toString())
            }

            override fun onResponse(call: Call<DetailEventResponse>?, response: Response<DetailEventResponse>?) {
                var event : DetailEventResponse? = response?.body()
                var eventItem : DetailEvent? = event?.events?.get(0);
                eventItem?.let { onFinishedListener.onFinished(it) };
            }
        });
    }
}
