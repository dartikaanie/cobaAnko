package com.example.sub1.event

import com.example.sub1.Model.*
import com.example.sub1.rest.LigaDataServices
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call

class EventPresenterTest{
    lateinit var presenterNext: EventListContract.presenter
    lateinit var presenterPrev: EventListContract.presenter

    @Mock
    private lateinit var view: EventListContract.EventView

    @Mock
    private lateinit var getEventsNextList: GetMatch

    @Mock
    private lateinit var getEventsPrevList: GetPrevMatch

    @Mock
    var eventResponse : EventResponse? = null

    @Mock
    var eventPrevResponse : EventResponse? = null

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
        getEventsNextList = GetMatch(id)
        presenterPrev = EventPresenter(view, getEventsPrevList)
        presenterNext = EventPresenter(view, getEventsNextList)
        apiServices = Mockito.mock<LigaDataServices>(LigaDataServices::class.java)
    }

    @Test
    fun GetNextMatch() {

        val id = "4328"
        presenterNext.requestDataFromServer()
        Mockito.`when`(apiServices?.getNextEvents(id)).thenReturn(mockCallNextMatch)
        val response = mockCallNextMatch?.execute()
        eventResponse = response?.body()
        eventResponse?.let { Mockito.verify(view).setDataEvents(it) }
    }

    @Test
    fun GetPrevMatch() {

        val id = "4328"
        presenterPrev.requestDataFromServer()
        Mockito.`when`(apiServices?.getPrevEvents(id)).thenReturn(mockCallPrevMatch)
        val response = mockCallPrevMatch?.execute()
        eventPrevResponse = response?.body()
        eventPrevResponse?.let { Mockito.verify(view).setDataEvents(it) }
    }
}