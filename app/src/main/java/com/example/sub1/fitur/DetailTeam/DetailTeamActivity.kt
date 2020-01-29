package com.example.sub1.fitur.DetailTeam

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.sub1.CekKoneksi
import com.example.sub1.DatabaseHelper.database
import com.example.sub1.Model.FavoriteTeam
import com.example.sub1.Model.TeamsItem
import com.example.sub1.R
import com.example.sub1.Utils.invisible
import com.example.sub1.Utils.visible
import com.example.sub1.adapter.page.TeamPageAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.viewPager


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailTeamActivity : AppCompatActivity(),
    DetailTeamContract.DetailTeamView, (TeamsItem) -> Unit {


    lateinit var presenter : DetailTeamPresenter
    lateinit var detailUI : DetailTeamActivityUI
    lateinit var team : TeamsItem
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
//    lateinit var dataTeam : TeamsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailUI = DetailTeamActivityUI()

        detailUI.setContentView(this)

        team = intent.getParcelableExtra<TeamsItem>("team")

        toast(team.strTeam.toString())
        Glide.with(this).load(team.strTeamLogo).into(detailUI.gambar)
        detailUI.txtName.text = team.strTeam
        detailUI.txtLiga.text = team.strLeague
        team.idTeam?.let { favoriteState(it) }

        presenter = DetailTeamPresenter(
            this,
            GetDetailTeam(team.idTeam.toString())
        )
        if(CekKoneksi().cekInternet(this)){
            presenter.requestDataFromServer()
        }else{
            toast("Hidupkan koneksi Internet Anda")
        }

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        detailUI.myViewPager.adapter =
            TeamPageAdapter(supportFragmentManager)
        detailUI.mTabLayout.setupWithViewPager(detailUI.myViewPager)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun invoke(teamsItem: TeamsItem) {
        teamsItem.strTeam?.let { toast(it) }

    }

    override fun showProgress() {
        detailUI.progressBar.visible()
    }

    override fun hideProgress() {
        detailUI.progressBar.invisible()
    }


//    override fun setDataToList(teamList: TeamList) {
//       teamAdapter = teamList.teams.let { TeamAdapter(it, this) }
////        detailUI.recyclerTeam.adapter = teamAdapter
//    }

    override fun setDataTeam(teamItem: TeamsItem) {
        detailUI.txtName.text = team.strTeam
        detailUI.isiText .text = team.intFormedYear
        detailUI.txtLiga.text = team.strLeague
        detailUI.progressBar.invisible()
    }




    @SuppressLint("SetTextI18n")
    override fun onResponseFailure(throwable: Throwable) {
        toast("ada Kesalahan. Silakan Coba Lagi")
        Log.e("ada Kesalahan", throwable.toString())
        detailUI.isiText.text = "Tidak bisa memuat data. . ."
    }

    //SET FITUR FAVORITE
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_event_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite(team) else addToFavorite(team)

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.btn_favorite_added)
        }
        else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.btn_favorites)
        }
    }

    private fun removeFromFavorite(dataTeam : TeamsItem){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id_team})",
                    "id_team" to dataTeam.idTeam.toString())
            }

        } catch (e: SQLiteConstraintException){
            toast("Maaf, Ada Kesaahan")
        }
    }

    private fun addToFavorite(dataTeam : TeamsItem){
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                    FavoriteTeam.TEAM_ID to dataTeam.idTeam,
                    FavoriteTeam.STR_TEAM to dataTeam.strTeam,
                    FavoriteTeam.INT_FORMED_YEAR to dataTeam.intFormedYear,
                    FavoriteTeam.STR_TEAM_LOGO to dataTeam.strTeamLogo,
                    FavoriteTeam.STR_LEAGUE to dataTeam.strLeague,
                    FavoriteTeam.STR_DESCRIPTIONEN to dataTeam.strDescriptionEN)
            }
            toast("favorit berhasil ditambahkan")
        } catch (e: SQLiteConstraintException){

        }
    }

    private fun favoriteState(idTeam: String){
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                .whereArgs("(TEAM_ID = {idTeam})",
                    "idTeam" to idTeam)

            val favorite = result.parseList(classParser<FavoriteTeam>())

            if (!favorite.isEmpty()) {
                isFavorite = true
                setFavorite()
            }

        }
    }


}

class DetailTeamActivityUI : AnkoComponent<DetailTeamActivity>{
    lateinit var gambar : ImageView
    lateinit var txtName : TextView
//    lateinit  var recyclerTeam: RecyclerView
    lateinit var isiText : TextView
    lateinit var txtLiga : TextView
    lateinit var progressBar : ProgressBar
    lateinit var mTabLayout : TabLayout
    lateinit var myViewPager : ViewPager

    override fun createView(ui: AnkoContext<DetailTeamActivity>): View = with(ui) {
        return   relativeLayout {
                verticalLayout{
                    verticalLayout {
                        backgroundColor = ContextCompat.getColor(context, R.color.backLogo)
                        padding = dip(20)
                        lparams(matchParent, wrapContent)
                        verticalLayout {
                            lparams {
                                width = matchParent
                                height = wrapContent
                            }
                            gravity = Gravity.CENTER_HORIZONTAL

                            cardView {
                                lparams {
                                    height = dip(100)
                                    width = dip(100)
                                    topMargin = dip(10)
                                    gravity = Gravity.CENTER_VERTICAL
                                }
                                radius = 8f
                                cardElevation = dip(5).toFloat()

                            gambar = imageView().lparams {
                                height = matchParent
                                width = matchParent
                                gravity = Gravity.CENTER_HORIZONTAL
                                padding = dip(5)
                            }

                        }

                            txtName = textView("Nama Club") {
                                textColor = ContextCompat.getColor(context, R.color.colorAccent)
                                textSize = sp(8).toFloat()
                            }.lparams {
                                leftMargin = dip(8)
                                width = wrapContent
                                height = wrapContent
                                bottomMargin = dip(5)
                            }

                            isiText = textView("0000") {
                                textSize = sp(8).toFloat()
                            }.lparams {
                                leftMargin = dip(8)
                                width = wrapContent
                                height = wrapContent
                                bottomMargin = dip(5)
                            }

                            txtLiga = textView("Nama Liga") {
                                textSize = sp(8).toFloat()
                            }.lparams {
                                leftMargin = dip(8)
                                width = wrapContent
                                height = wrapContent
                                bottomMargin = dip(5)
                            }
                        }
                    }

                    coordinatorLayout {
                        lparams(matchParent, matchParent)

                        appBarLayout {
                            lparams(matchParent, wrapContent)

                            mTabLayout = themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                                lparams(matchParent, wrapContent)
                                {
                                    tabMode = TabLayout.MODE_FIXED
                                }
                            }
                        }
                        myViewPager = viewPager {
                            id = R.id.viewpager
                        }.lparams(matchParent, matchParent)
                        (myViewPager.layoutParams as CoordinatorLayout.LayoutParams).behavior = AppBarLayout.ScrollingViewBehavior()
                    }
                }

            progressBar = progressBar().lparams {
                centerHorizontally()
            }
        }
    }
}




