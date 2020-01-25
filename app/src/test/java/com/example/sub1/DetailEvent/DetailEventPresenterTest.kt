package com.example.sub1.DetailEvent

import com.example.sub1.Detail.DetailContract
import com.example.sub1.Detail.DetailPresenter
import com.example.sub1.Detail.GetDetailLiga
import com.example.sub1.Model.*
import com.example.sub1.rest.LigaDataServices
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response

class DetailEventPresenterTest{
    @Mock
    private lateinit var view: DetailEventContract.DetailEventView
    @Mock
    private lateinit var getDetailEvent: DetailEventContract.GetDetailEvent

    lateinit var presenter: DetailEventContract.presenter


    private val detailEvent: DetailEvent? = null

    @Mock
    var apiServices: LigaDataServices? = null

    @Mock
    var mockCallEventDetail: Call<DetailEventResponse>? = null



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailEventPresenter(view, getDetailEvent)
        apiServices = Mockito.mock<LigaDataServices>(LigaDataServices::class.java)
    }

    @Test
    fun GetDetailEvent() {
        val id = "1234"
        Mockito.`when`(apiServices?.getDetailEvent(id)).thenReturn(mockCallEventDetail)
        presenter.requestDataFromServer()
        Response.success(detailEvent).body()?.let { Mockito.verify(view).setDataEvent(it) }
    }


}