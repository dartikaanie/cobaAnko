package com.example.sub1.event

import com.example.sub1.Model.EventResponse
import com.example.sub1.fitur.EventLiga.EventListContract
import com.example.sub1.fitur.EventLiga.EventPresenter
import com.example.sub1.fitur.EventLiga.GetMatch
import com.example.sub1.fitur.EventLiga.GetPrevMatch
import com.example.sub1.rest.LigaDataServices
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call

class EventPresenterTest{
    lateinit var presenterNext: EventListContract.Presenter
    lateinit var presenterPrev: EventListContract.Presenter

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
        val id = "4328"
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