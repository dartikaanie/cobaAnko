package com.example.sub1.listPertandingan

import com.example.sub1.Model.EventsItem
import com.example.sub1.Model.MatchResponse
import com.example.sub1.Model.TeamList
import com.example.sub1.fitur.listPertandingan.PertandinganContract
import com.example.sub1.fitur.listPertandingan.PertandinganPresenter
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

class PertandinganPresenterTest{
    @Mock
    private lateinit var view: PertandinganContract.EventView

    @Mock
    private lateinit var getEventsList: PertandinganContract.GetEvent

    lateinit var presenter: PertandinganContract.presenter


    private val matchResponse: MatchResponse? = null

    @Mock
    var apiServices: LigaDataServices? = null


    @Mock
    var mockCallEventsItem: Call<MatchResponse>? = null



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter =
            PertandinganPresenter(view, getEventsList)
        apiServices = Mockito.mock<LigaDataServices>(LigaDataServices::class.java)
    }

    @Test
    fun GetEvent() {
        val id = "1234"
        val teamList : Call<TeamList>? = null
        `when`(apiServices?.getEvent(id)).thenReturn(mockCallEventsItem)
        val match = Response.success(matchResponse).body()
        val teamsHome : TeamList? = null
        val teamsAway : TeamList? = null
        val eventList: List<EventsItem>? = match?.event?.filter { it.strSport == "Soccer" }
        if (eventList != null) {
            for(item in eventList){
                `when`(apiServices?.getTeams(item.idHomeTeam.toString())).thenReturn(teamList)
                val teamsHome = Response.success(teamsHome).body()
                item.imgHome = teamsHome?.teams?.get(0)?.strTeamLogo.toString()

                `when`(apiServices?.getTeams(item.idAwayTeam.toString())).thenReturn(teamList)
                val teamsAway = Response.success(teamsAway).body()
                item.imgHome = teamsAway?.teams?.get(0)?.strTeamLogo.toString()
            }
        }
        presenter.requestDataFromServer()

        match?.let { verify(view).SetDataMatch(it) }
    }

}