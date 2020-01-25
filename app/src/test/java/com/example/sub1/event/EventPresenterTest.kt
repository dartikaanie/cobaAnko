package com.example.sub1.event

import android.util.Log
import com.example.sub1.Detail.DetailContract
import com.example.sub1.Detail.DetailPresenter
import com.example.sub1.Detail.GetDetailLiga
import com.example.sub1.Model.*
import com.example.sub1.rest.LigaDataServices
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import retrofit2.Callback


class EventPresenterTest{
    lateinit var presenterNext: EventListContract.presenter
    lateinit var presenterPrev: EventListContract.presenter

    @Mock
    private lateinit var view: EventListContract.EventView

    @Mock
    private lateinit var getEventsList: EventListContract.GetMatch

    @Mock
    var eventNext : EventResponse? = null;

    @Mock
    var eventPrev : EventResponse? = null;


    @Mock
    var apiServices: LigaDataServices? = null

    @Mock
    var mockCallNextMatch: Call<EventResponse>? = null

    @Mock
    var mockCallPrevMatch: Call<EventResponse>? = null


    @Before
    fun setUp() {
        var id = "4328"
        MockitoAnnotations.initMocks(this)
        presenterNext = EventPresenter(view, GetMatch(id))
        presenterPrev = EventPresenter(view, GetPrevMatch(id))
        apiServices = Mockito.mock<LigaDataServices>(LigaDataServices::class.java)
    }

    @Test
    fun GetNextMatch() {
        val id = "4328"
        Mockito.`when`(apiServices?.getNextEvents(id)).thenReturn(mockCallNextMatch)
        val event: EventResponse? = Response.success(eventNext).body()
        presenterNext.requestDataFromServer()
        verify(view).setDataEvents(event)
    }

    @Test
    fun GetPrevMatch(){
        val id = "4328"
        Mockito.`when`(apiServices?.getPrevEvents(id)).thenReturn(mockCallPrevMatch)
        val event : EventResponse? = Response.success(eventPrev).body()
        presenterPrev.requestDataFromServer()
        verify(view).setDataEvents(event)
    }


}