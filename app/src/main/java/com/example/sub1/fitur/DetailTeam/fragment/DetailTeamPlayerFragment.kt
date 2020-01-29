package com.example.sub1.fitur.DetailTeam.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sub1.Model.Player.PlayerItem
import com.example.sub1.Model.Player.PlayerResponse
import com.example.sub1.Utils.invisible
import com.example.sub1.Utils.visible
import com.example.sub1.adapter.PlayerAdapter
import com.example.sub1.fitur.DetailTeam.DetailTeamActivity
import com.example.sub1.fitur.DetailTeam.PlayersTeam.GetPlayerTeam
import com.example.sub1.fitur.DetailTeam.PlayersTeam.PlayerTeamPresenter
import com.example.sub1.fitur.DetailTeam.PlayersTeam.PlayersTeamContract
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.toast

class DetailTeamPlayerFragment : Fragment(), AnkoComponent<Context>,
PlayersTeamContract.DetailPlayerTeamView {

    private lateinit var presenter: PlayerTeamPresenter
    private lateinit var adapter: PlayerAdapter
    private lateinit var activity: DetailTeamActivity
    private lateinit var progressBar: ProgressBar
    lateinit var rvPlayer : RecyclerView


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as DetailTeamActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = PlayerTeamPresenter(
            this,
            GetPlayerTeam(activity.team.strTeam.toString())
        )
        presenter.requestDataFromServer()

        return createView(AnkoContext.create(requireContext()))
    }


    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        verticalLayout {
            rvPlayer = recyclerView {
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


    private fun PlayerClicked(item : PlayerItem) {
        toast(item.strPlayer.toString())
    }

    override fun showProgress() {
        progressBar.visible()
    }

    override fun hideProgress() {
        progressBar.invisible()
    }


    override fun setDataTeam(playerList: PlayerResponse) {
        Log.e("getPlayer", playerList.toString())
        adapter = PlayerAdapter(playerList.player, {
                item: PlayerItem -> PlayerClicked(item)
        })
        rvPlayer.adapter = adapter
    }



    override fun onResponseFailure(throwable: Throwable) {
        toast("Maaf Ada Kesalahan")
        progressBar.invisible()

    }
}
