package com.example.sub1.fitur.TeamsLiga

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sub1.Model.TeamList
import com.example.sub1.Model.TeamsItem
import com.example.sub1.Utils.invisible
import com.example.sub1.Utils.visible
import com.example.sub1.adapter.TeamAdapter
import com.example.sub1.fitur.DetailLiga.DetailLigaActivity
import com.example.sub1.fitur.DetailTeam.DetailTeamActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast

class TeamsFragment : Fragment(), AnkoComponent<Context>,
    TeamsLigaContract.TeamsLigaView, (TeamsItem) -> Unit {

    private lateinit var presenter: TeamsLigaPresenter
        private lateinit var adapter: TeamAdapter
        private lateinit var detailActivity: DetailLigaActivity
        private lateinit var progressBar: ProgressBar
        lateinit var rvTeams : RecyclerView

        override fun onAttach(context: Context) {
            super.onAttach(context)
            detailActivity = context as DetailLigaActivity
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            presenter = TeamsLigaPresenter(
                this,
                GetTeam(detailActivity.liga.id.toString())
            )
            presenter.requestDataFromServer()

            return createView(AnkoContext.create(requireContext()))
        }

        override fun createView(ui: AnkoContext<Context>): View = with(ui){
            verticalLayout {
                rvTeams = recyclerView {
                    lparams {
                        width = matchParent
                        height = wrapContent
                        rightMargin = dip(8)
                        leftMargin = dip(8)
                    }
//                    adapter = eventNextAdapter
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
                progressBar = progressBar().lparams {
                    gravity = Gravity.CENTER_HORIZONTAL
                }
            }
        }



        override fun showProgress() {
            progressBar.visible()
        }

        override fun hideProgress() {
            progressBar.invisible()
        }


    override fun setDataToList(teamList: TeamList) {
        adapter = teamList.teams.let { TeamAdapter(it, this) }
        rvTeams.adapter = adapter
    }

    override fun invoke(p1: TeamsItem) {
        p1.strTeam?.let { toast(it) }
        startActivity(intentFor<DetailTeamActivity>("team" to p1))
    }



    override fun onResponseFailure(throwable: Throwable) {
            toast("Maaf Ada Kesalahan")
            progressBar.invisible()

        }
    }