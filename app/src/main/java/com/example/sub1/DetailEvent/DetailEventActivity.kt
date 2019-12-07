package com.example.sub1.DetailEvent

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.sub1.Detail.DetailLigaActivity
import com.example.sub1.Detail.DetailPresenter
import com.example.sub1.Detail.GetDetailEvent
import com.example.sub1.Detail.GetTeam
import com.example.sub1.Model.DetailEvent
import com.example.sub1.Model.EventsItem
import com.example.sub1.Model.Liga
import com.example.sub1.R
import com.example.sub1.Utils.invisible
import com.example.sub1.Utils.visible
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.viewPager
import org.w3c.dom.Text

class DetailEventActivity : AppCompatActivity(), DetailEventContract.DetailEventView {
    lateinit var presenter : DetailEventPresenter
    lateinit var detailEventUi : DetailEventActivityUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailEventUi = DetailEventActivityUI()
        detailEventUi.setContentView(this)

        val dataEvent: EventsItem? = intent.getParcelableExtra<EventsItem>("event")
        toast(dataEvent?.strEvent.toString())

        Glide.with(this).load(dataEvent?.imgAway).into(detailEventUi.gambarAway)
        Glide.with(this).load(dataEvent?.imgHome).into(detailEventUi.gambarHome)

        Log.e("parse Data", dataEvent.toString() )

        presenter = DetailEventPresenter(this, GetDetailEvent(dataEvent?.idEvent.toString()))
        presenter.requestDataFromServer()
    }

    override fun showProgress() {
        detailEventUi.progressBar.visible()
    }

    override fun hideProgress() {
        detailEventUi.progressBar.invisible()
    }

    override fun setDataEvent(event: DetailEvent) {
        detailEventUi.nama_event.setText(event.strEvent)
        detailEventUi.strSport.setText(event.strSport)
        detailEventUi.dateEvent.setText(event.dateEvent + "  " + event.getStrTime())
        detailEventUi.strHomeTeam.setText(event.strHomeTeam)
        detailEventUi.strAwayTeam.setText(event.strAwayTeam)
        detailEventUi.intHomeScore.setText(event.intHomeScore)
        detailEventUi.intAwayScore.setText(event.intAwayScore)

        detailEventUi.Formation.setText(event.strHomeFormation)
        detailEventUi.redCard.setText(event.strHomeRedCards)
        detailEventUi.yellowCard.setText(event.strHomeYellowCards?.replace(';','\n'))
        detailEventUi.LineupDefense.setText(event.strHomeLineupDefense?.replace(';','\n'))
        detailEventUi.LineupForward.setText(event.strHomeLineupForward?.replace(';','\n'))
        detailEventUi.LineupGoalkeeper.setText(event.strHomeLineupGoalkeeper?.replace(';','\n'))
        detailEventUi.LineupMidfield.setText(event.strHomeLineupMidfield?.replace(';','\n'))
        detailEventUi.LineupSubstitutes.setText(event.strHomeLineupSubstitutes?.replace(';','\n'))
        detailEventUi.goalDetail.setText(event.strHomeGoalDetails?.replace(';','\n'))
//
        detailEventUi.goalDetailAway.setText(event.strAwayGoalDetails?.replace(';','\n'))
        detailEventUi.FormationAway.setText(event.strAwayFormation)
        detailEventUi.redCardAway.setText(event.strAwayRedCards?.replace(';','\n'))
        detailEventUi.yellowCardAway.setText(event.strAwayYellowCards?.replace(';','\n'))
        detailEventUi.LineupDefenseAway.setText(event.strAwayLineupDefense?.replace(';','\n'))
        detailEventUi.LineupForwardAway.setText(event.strAwayLineupForward?.replace(';','\n'))
        detailEventUi.LineupGoalkeeperAway.setText(event.strAwayLineupGoalkeeper?.replace(';','\n'))
        detailEventUi.LineupMidfieldAway.setText(event.strAwayLineupMidfield?.replace(';','\n'))
        detailEventUi.LineupSubstitutesAway.setText(event.strAwayLineupSubstitutes?.replace(';','\n'))
    }

    override fun onResponseFailure(throwable: Throwable) {
       toast("Maaf Ada Keasalahan")
    }
}

class DetailEventActivityUI : AnkoComponent<DetailEventActivity> {

    lateinit var nama_event: TextView
    lateinit var strSport: TextView
    lateinit var dateEvent: TextView
    lateinit var strHomeTeam: TextView
    lateinit var gambarHome: ImageView
    lateinit var intHomeScore: TextView
    lateinit var intAwayScore: TextView
    lateinit var gambarAway: ImageView
    lateinit var strAwayTeam: TextView
    lateinit var progressBar: ProgressBar
    lateinit var goalDetail: TextView
    lateinit var Formation: TextView
    lateinit var redCard: TextView
    lateinit var yellowCard: TextView
    lateinit var LineupGoalkeeper: TextView
    lateinit var LineupDefense: TextView
    lateinit var LineupForward: TextView
    lateinit var LineupMidfield: TextView
    lateinit var LineupSubstitutes: TextView
    lateinit var goalDetailAway: TextView
    lateinit var FormationAway: TextView
    lateinit var redCardAway: TextView
    lateinit var yellowCardAway: TextView
    lateinit var LineupGoalkeeperAway: TextView
    lateinit var LineupDefenseAway: TextView
    lateinit var LineupForwardAway: TextView
    lateinit var LineupMidfieldAway: TextView
    lateinit var LineupSubstitutesAway: TextView

    override fun createView(ui: AnkoContext<DetailEventActivity>): View = with(ui) {
        return verticalLayout() {
            cardView {
                lparams {
                    height = dip(wrapContent);
                    width = dip(400);
                    topMargin = dip(6);
                    bottomMargin = dip(6);
                    rightMargin = dip(3);
                    leftMargin = dip(3)
                }

                radius = 8f
                cardElevation = dip(6).toFloat()


                verticalLayout {
                    nama_event = textView("nama_event ") {
                    }.lparams {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
                    strSport = textView("strSport") {
                    }.lparams {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
                    dateEvent = textView("dateEvent") {
                    }.lparams {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }

                    linearLayout() {
                        gravity = Gravity.CENTER_HORIZONTAL
                        verticalLayout() {
                            gambarHome = imageView {
                            }.lparams {
                                height = dip(100)
                                width = dip(100)
                                gravity = Gravity.CENTER_HORIZONTAL
                            }
                            strHomeTeam = textView("-")

                        }.lparams {
                            width = dip(wrapContent)
                            gravity = Gravity.CENTER_HORIZONTAL
                            gravity = Gravity.CENTER_VERTICAL
                            margin = dip(5)
                        }

                        intHomeScore = textView() {
                            textSize = dip(12).toFloat()
                        }.lparams {
                            gravity = Gravity.CENTER_HORIZONTAL
                            gravity = Gravity.CENTER_VERTICAL
                            margin = dip(3)
                        }
                        textView(":") {
                            textSize = dip(12).toFloat()
                        }.lparams {
                            gravity = Gravity.CENTER_HORIZONTAL
                            gravity = Gravity.CENTER_VERTICAL
                            margin = dip(3)
                        }

                        intAwayScore = textView("-") {
                            textSize = dip(12).toFloat()
                        }.lparams {
                            gravity = Gravity.CENTER_HORIZONTAL
                            gravity = Gravity.CENTER_VERTICAL
                            margin = dip(3)
                        }

                        verticalLayout() {
                            gambarAway = imageView {}.lparams {
                                height = dip(100)
                                width = dip(100)
                                gravity = Gravity.CENTER_HORIZONTAL
                            }
                            strAwayTeam =
                                textView("-") {}.lparams(wrapContent, wrapContent)
                        }.lparams {
                            width = dip(wrapContent)
                            gravity = Gravity.CENTER_HORIZONTAL
                            gravity = Gravity.CENTER_VERTICAL
                            margin = dip(3)
                        }
                    }
                }
                progressBar = progressBar().lparams {
                    gravity = Gravity.CENTER_HORIZONTAL
                }
            }
            scrollView {
                verticalLayout(){
                    lparams(matchParent, wrapContent)
                    tableLayout() {
                        lparams(matchParent, wrapContent)
                        tableRow() {
                            textView("Goal Detail") {
                                typeface = Typeface.DEFAULT_BOLD
                                width = dip(200)
                            }

                        }
                    }
                    tableLayout() {
                        tableRow() {
                            goalDetail = textView("-").lparams{
                                width = dip(200)
                            }
                            goalDetailAway = textView("-").lparams{
                                width = dip(200)
                            }
                        }

                        tableRow() {
                            textView("Red Cards") {
                                typeface = Typeface.DEFAULT_BOLD

                            }
                        }

                        tableRow() {
                            redCard = textView("-")
                            redCardAway = textView("-")
                        }

                        tableRow() {
                            textView("Yellow Cards") {
                                typeface = Typeface.DEFAULT_BOLD

                            }
                        }

                        tableRow() {
                            yellowCard = textView("-")
                            yellowCardAway = textView("-")
                        }
//
                        tableRow() {
                            textView("Lineup Goal keeper")
                            {
                                typeface = Typeface.DEFAULT_BOLD

                            }
                        }
//
                        tableRow() {
                            LineupGoalkeeper = textView("-")
                            LineupGoalkeeperAway = textView("-")
                        }
//
                        tableRow() {
                            textView("Line up Defense")
                            {
                                typeface = Typeface.DEFAULT_BOLD

                            }
                        }
//
                        tableRow() {
                            LineupDefense = textView("-")
                            LineupDefenseAway = textView("-")
                        }
//
                        tableRow() {
                            textView("Line up Midfield") {
                                typeface = Typeface.DEFAULT_BOLD

                            }
                        }
                        tableRow(){
                            LineupMidfield = textView("-")
                            LineupMidfieldAway = textView("-")
                        }
//
                        tableRow() {
                            textView("Line up Forward") {
                                typeface = Typeface.DEFAULT_BOLD

                            }
                        }
//
                        tableRow(){
                            LineupForward = textView("-")
                            LineupForwardAway = textView("-")
                        }

//
                        tableRow() {
                            textView("Line up Substitutes") {
                                typeface = Typeface.DEFAULT_BOLD

                            }
                        }
//
                        tableRow(){
                            LineupSubstitutes = textView("-")
                            LineupSubstitutesAway = textView("-")
                        }
//
                        tableRow() {
                            textView("Formation") {
                                typeface = Typeface.DEFAULT_BOLD

                            }
                        }
                        tableRow() {
                            Formation = textView("-")
                            FormationAway = textView("-")
                        }
                    }.lparams {
                        gravity = Gravity.CENTER
                    }
                }
            }
        }
    }
}






