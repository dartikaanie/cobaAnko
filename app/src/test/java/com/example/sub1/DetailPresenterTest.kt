package com.example.sub1

import com.example.sub1.Detail.DetailContract
import com.example.sub1.Detail.DetailPresenter
import com.example.sub1.Detail.GetDetailLiga
import com.example.sub1.Model.Liga
import com.example.sub1.Model.LigaResponse
import com.example.sub1.Model.TeamList
import com.example.sub1.Model.TeamsItem
import com.example.sub1.rest.LigaDataServices
import com.example.sub1.rest.RetrofitInstances
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import okhttp3.ResponseBody
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import retrofit2.Response




class DetailPresenterTest {
    @Mock
    private lateinit var view: DetailContract.DetailView

    @Mock
    private lateinit var getDetailLiga: GetDetailLiga

    @Mock
    private lateinit var getTeamList: DetailContract.GetTeamIntractor

    lateinit var presenter: DetailContract.presenter


    private val teams: TeamList? = null
    private val liga : LigaResponse? = null

    @Mock
    var apiServices: LigaDataServices? = null

    @Mock
    var mockCallLigaDetail: Call<LigaResponse>? = null

    @Mock
    var mockCallLigaTeams: Call<TeamList>? = null



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view, getTeamList, getDetailLiga)
        apiServices = Mockito.mock<LigaDataServices>(LigaDataServices::class.java)
    }

    @Test
    fun GetDetailLiga() {
        val id = "1234"

        `when`(apiServices?.getDetailLiga(id)).thenReturn(mockCallLigaDetail)
        val liga = Response.success(liga).body()

        `when`(apiServices?.getLigaTeams(id)).thenReturn(mockCallLigaTeams)
        val teamList = Response.success(teams).body()

        presenter.requestDataFromServer()

        var ligaItem : Liga? = liga?.ligaList?.get(0)


        ligaItem?.let { verify(view).setDetaiLiga(it) }
        teamList?.let { verify(view).setDataToList(it) }
    }
}