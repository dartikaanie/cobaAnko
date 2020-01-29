package com.example.sub1.fragment

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sub1.Model.TeamList
import com.example.sub1.Model.TeamsItem
import com.example.sub1.R
import com.example.sub1.Utils.invisible
import com.example.sub1.Utils.visible
import com.example.sub1.adapter.TeamAdapter
import com.example.sub1.fitur.DetailTeam.DetailTeamActivity
import com.example.sub1.fitur.TeamsGeneral.GetTeam
import com.example.sub1.fitur.TeamsGeneral.TeamsContract
import com.example.sub1.fitur.TeamsGeneral.TeamsPresenter
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast


class TeamsFragment : Fragment(), TeamsContract.View, AnkoComponent<Context>{

    lateinit var presenter : TeamsPresenter
    lateinit var searchView : SearchView
    lateinit var progressBar : ProgressBar
    lateinit var rvTeam : RecyclerView
    private lateinit var adapter: TeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        progressBar = find<ProgressBar>(R.id.progressBar)
        presenter = TeamsPresenter(
            this,
            GetTeam("Arsenal")
        )
        presenter.requestDataFromServer()

        return createView(AnkoContext.create(requireContext()))
//        return TeamsFragmentUI().createView(AnkoContext.create(requireContext(),this))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        verticalLayout {
            cardView {
                searchView = searchView {
                    id = R.id.searchTeam
                    queryHint = "Cari . . ."
                }
                padding = dip(10)
            }.lparams{
                width = matchParent
                height = wrapContent
            }

            rvTeam = recyclerView {
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        view.let { super.onViewCreated(it, savedInstanceState) }
//        rvTeam = find<RecyclerView>(R.id.recycleTeam)
        val manager = LinearLayoutManager(context)
        rvTeam.layoutManager = manager
        rvTeam.setHasFixedSize(true)
//        searchView = find<SearchView>(R.id.searchMatch)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            TeamsContract.View {
            override fun setDataToList(teamList: TeamList) {
                rvTeam.visible()
                adapter = TeamAdapter(teamList.teams, { team : TeamsItem -> teamClicked(team) })
                rvTeam.adapter = adapter
            }


            override fun showProgress() {
                progressBar.visible()
                rvTeam.invisible()
            }

            override fun hideProgress() {
                progressBar.invisible()
            }

            override fun onResponseFailure(throwable: Throwable) {
                toast("Maaf ada kesalahan data")
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                presenter = TeamsPresenter(
                    this,
                    GetTeam(query)
                )
                presenter.requestDataFromServer()
                return false
            }

        })
    }



    override fun showProgress() {
        progressBar.visible()
    }

    override fun hideProgress() {
        progressBar.invisible()
    }

    override fun setDataToList(teamList: TeamList) {
        rvTeam.visible()
        adapter = TeamAdapter(teamList.teams, { team : TeamsItem -> teamClicked(team) })
        rvTeam.adapter = adapter
    }

    private fun teamClicked(item : TeamsItem) {
        item.strTeam?.let { toast(it) }
        startActivity(intentFor<DetailTeamActivity>("team" to item))
    }

    override fun onResponseFailure(throwable: Throwable) {
        toast("Maaf Ada Kesalahan")
    }

}


class TeamsFragmentUI : AnkoComponent<TeamsFragment> {
    override fun createView(ui: AnkoContext<TeamsFragment>): View = with(ui) {
        verticalLayout {

                cardView {
                    searchView {
                        id = R.id.searchMatch

                        queryHint = "Cari . . ."
                    }
                    padding = dip(10)
                }.lparams{
                    width = matchParent
                    height = wrapContent
                }

                    recyclerView {
                        id = R.id.recycleTeam
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }


//                    progressBar().lparams {
//                        id = R.id.progressBar
//                        gravity = Gravity.CENTER_HORIZONTAL
//                    }
                }
        }

//
    }
