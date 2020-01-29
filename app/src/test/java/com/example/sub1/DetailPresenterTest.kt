package com.example.sub1

import com.example.sub1.Model.Liga
import com.example.sub1.Model.LigaResponse
import com.example.sub1.Model.TeamList
import com.example.sub1.fitur.DetailLiga.DetailContract
import com.example.sub1.fitur.DetailLiga.DetailPresenter
import com.example.sub1.fitur.DetailLiga.GetDetailLiga
import com.example.sub1.fitur.TeamsLiga.TeamsLigaContract
import com.example.sub1.fitur.TeamsLiga.TeamsLigaPresenter
import com.example.sub1.rest.LigaDataServices
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response


class DetailPresenterTest {
    @Mock
    private lateinit var viewDetail: DetailContract.DetailView

    @Mock
    private lateinit var viewTeam: TeamsLigaContract.TeamsLigaView

    @Mock
    private lateinit var getDetailLiga: GetDetailLiga

    @Mock
    private lateinit var getTeamList: TeamsLigaContract.GetTeamIntractor

    lateinit var presenter: DetailContract.Presenter
    lateinit var presenterTeamsLiga: TeamsLigaContract.Presenter

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
        presenter =
            DetailPresenter(viewDetail, getDetailLiga)
        presenterTeamsLiga =
            TeamsLigaPresenter(viewTeam, getTeamList)
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


        ligaItem?.let { verify(viewDetail).setDetaiLiga(it) }
        teamList?.let { verify(viewTeam).setDataToList(it) }
    }
}