package com.example.sub1.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import com.bumptech.glide.Glide
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sub1.Utils.invisible
import com.example.sub1.Utils.visible
import org.jetbrains.anko.recyclerview.v7.recyclerView
import com.example.sub1.*
import com.example.sub1.adapter.TeamAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.sub1.Model.*
import com.example.sub1.adapter.EventAdapter
import com.example.sub1.adapter.PageAdapter
import com.example.sub1.event.GetMatch
import com.example.sub1.event.NextMatchFragment
import com.example.sub1.event.PrevMatchFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.viewPager


class DetailLigaActivity : AppCompatActivity(), DetailContract.DetailView, (TeamsItem) -> Unit {


    private lateinit var progressBar: ProgressBar
    lateinit var teamAdapter : TeamAdapter
    lateinit var presenter : DetailPresenter
    lateinit var detailUI : DetalctivityUI
    lateinit var liga : Liga


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailUI = DetalctivityUI()

        detailUI.setContentView(this)

        liga = intent.getParcelableExtra<Liga>("liga")
        toast(liga.name.toString())
        Glide.with(this).load(liga.image).into(detailUI.gambar)
        detailUI.txtName.text = liga.name

        presenter = DetailPresenter(this, GetTeam(liga.id.toString()), GetDetailLiga(liga.id.toString()))
        if(CekKoneksi().cekInternet(this)){
            presenter.requestDataFromServer()
        }else{
            toast("Hidupkan koneksi Internet Anda")
        }

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        detailUI.myViewPager.adapter = PageAdapter(supportFragmentManager)
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


    override fun setDataToList(teamList: TeamList) {
       teamAdapter = teamList.teams.let { TeamAdapter(it, this) }
        detailUI.recyclerTeam.adapter = teamAdapter
    }

    override fun setDetaiLiga(liga: Liga) {
        detailUI.isiText.text = liga.deskripsi?.substring(0,200)
    }




    override fun onResponseFailure(throwable: Throwable) {
        toast("ada Kesalahan. Silakan Coba Lagi")
        Log.e("ada Kesalahan", throwable.toString())
        detailUI.isiText.text = "Tidak bisa memuat data. . ."
    }

}

class DetalctivityUI : AnkoComponent<DetailLigaActivity>{
    lateinit var gambar : ImageView
    lateinit var txtName : TextView
    lateinit  var recyclerTeam: RecyclerView
    lateinit var isiText : TextView
    lateinit var progressBar : ProgressBar
    lateinit var mTabLayout : TabLayout
    lateinit var myViewPager : ViewPager

    override fun createView(ui: AnkoContext<DetailLigaActivity>): View = with(ui) {
        return   relativeLayout {
            scrollView {
                verticalLayout{
                    verticalLayout {
                        backgroundColor = ContextCompat.getColor(context, R.color.backLogo)
                        padding = dip(20)
                        lparams(matchParent, wrapContent)
                        linearLayout {
                            lparams {
                                width = matchParent
                                height = wrapContent
                            }
                            gravity = Gravity.CENTER_HORIZONTAL

                            cardView {
                                lparams {
                                    height = dip(150)
                                    width = dip(150)
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
                            verticalLayout {
                                txtName = textView("Nama Club") {
                                    textColor = ContextCompat.getColor(context, R.color.colorAccent)
                                    textSize = sp(8).toFloat()
                                }.lparams {
                                    leftMargin = dip(8)
                                    width = wrapContent
                                    height = wrapContent
                                    bottomMargin = dip(5)
                                }

                                scrollView {
                                    verticalLayout {
                                        lparams(matchParent, wrapContent)
                                        isiText = textView {
                                            textColor = ContextCompat.getColor(
                                                context,
                                                R.color.colorPrimary
                                            )
                                            textSize = sp(5).toFloat()
                                        }.lparams {
                                            leftMargin = dip(15)
                                            width = matchParent
                                            height = dip(150)
                                            rightMargin = dip(8)
                                        }
                                    }
                                }
                            }
                        }

                        recyclerTeam = recyclerView {
                            lparams {
                                width = matchParent
                                height = wrapContent
                                rightMargin = dip(8)
                                leftMargin = dip(8)
                            }
                            layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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
            }
            progressBar = progressBar().lparams {
                centerHorizontally()
            }
        }
    }
}




