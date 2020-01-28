package com.example.sub1.DetailEvent

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sub1.DatabaseHelper.database
import com.example.sub1.Detail.GetDetailEvent
import com.example.sub1.Model.DetailEvent
import com.example.sub1.Model.EventsItem
import com.example.sub1.Model.Favorite
import com.example.sub1.R
import com.example.sub1.Utils.invisible
import com.example.sub1.Utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailEventActivity : AppCompatActivity(), DetailEventContract.DetailEventView, View.OnClickListener {

    lateinit var presenter : DetailEventPresenter
    lateinit var detailEventUi : DetailEventActivityUI
    lateinit var dataEvent : EventsItem
    lateinit var imageFavorit : ImageView
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailEventUi = DetailEventActivityUI()
        detailEventUi.setContentView(this)

        dataEvent = intent.getParcelableExtra<EventsItem>("event")
        toast(dataEvent.strEvent.toString())
        dataEvent.idEvent?.let { favoriteState(it) }

        val options = RequestOptions()
            .override(100,100)
            .placeholder(R.drawable.img)
            .error(R.drawable.img)
        Glide.with(this).load(dataEvent.imgAway).apply(options).into(detailEventUi.gambarAway)
        Glide.with(this).load(dataEvent.imgHome).apply(options).into(detailEventUi.gambarHome)

        presenter = DetailEventPresenter(this, GetDetailEvent(dataEvent.idEvent.toString()))
        presenter.requestDataFromServer()

        imageFavorit = find<ImageView>(R.id.imageFavorit)
        imageFavorit.setOnClickListener(this)
    }


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
                if (isFavorite) removeFromFavorite(dataEvent) else addToFavorite(dataEvent)

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

    private fun removeFromFavorite(event : EventsItem){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id_event})",
                    "id_event" to event.idEvent.toString())
            }

        } catch (e: SQLiteConstraintException){
            toast("Maaf, Ada Kesaahan")
        }
    }

    private fun addToFavorite(event : EventsItem){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to event.idEvent,
                    Favorite.STR_SPORT to event.strSport,
                    Favorite.STR_EVENT to event.strEvent,
                    Favorite.DATE_EVENT to event.dateEventLocal,
                    Favorite.ID_HOME_TEAM to event.idHomeTeam,
                    Favorite.INT_HOME_SCORE to event.intHomeScore,
                    Favorite.STR_HOME_TEAM to event.strHomeTeam,
                    Favorite.STR_TIME to event.strTime,
                    Favorite.ID_LEAGUE to event.idLeague,
                    Favorite.STR_LEAGUE to event.strLeague,
                    Favorite.INT_AWAY_SCORE to event.intAwayScore,
                    Favorite.STR_AWAY_TEAM to event.strAwayTeam,
                    Favorite.IMG_HOME to event.imgHome,
                    Favorite.IMG_AWAY to event.imgAway)
            }
            toast("favorit berhasil ditambahkan")
        } catch (e: SQLiteConstraintException){

        }
    }

    private fun favoriteState(idEvent : String){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(EVENT_ID = {idEvent})",
                    "idEvent" to idEvent)

            val favorite = result.parseList(classParser<Favorite>())

            if (!favorite.isEmpty()) {
                isFavorite = true
                setFavorite()
            }

        }
    }


    override fun onClick(p0: View?) {
        toast("favorit")
    }


    override fun showProgress() {
        detailEventUi.progressBar.visible()
    }

    override fun hideProgress() {
        detailEventUi.progressBar.invisible()
    }

    override fun setDataEvent(event: DetailEvent) {

        detailEventUi.namaEvent.text =event.strEvent
        detailEventUi.strSport.text =event.strSport
        detailEventUi.dateEvent.text =event.dateEvent + "  " + event.getStrTime()
        detailEventUi.strHomeTeam.text =event.strHomeTeam
        detailEventUi.strAwayTeam.text =event.strAwayTeam
        detailEventUi.intHomeScore.text =event.getScoreHome()
        detailEventUi.intAwayScore.text =event.getScoreAway()

        detailEventUi.Formation.text =event.strHomeFormation
        detailEventUi.redCard.text =event.strHomeRedCards
        detailEventUi.yellowCard.text =event.strHomeYellowCards?.replace(';','\n')
        detailEventUi.LineupDefense.text =event.strHomeLineupDefense?.replace(';','\n')
        detailEventUi.LineupForward.text =event.strHomeLineupForward?.replace(';','\n')
        detailEventUi.LineupGoalkeeper.text =event.strHomeLineupGoalkeeper?.replace(';','\n')
        detailEventUi.LineupMidfield.text =event.strHomeLineupMidfield?.replace(';','\n')
        detailEventUi.LineupSubstitutes.text =event.strHomeLineupSubstitutes?.replace(';','\n')
        detailEventUi.goalDetail.text =event.strHomeGoalDetails?.replace(';','\n')
//
        detailEventUi.goalDetailAway.text =event.strAwayGoalDetails?.replace(';','\n')
        detailEventUi.FormationAway.text =event.strAwayFormation
        detailEventUi.redCardAway.text =event.strAwayRedCards?.replace(';','\n')
        detailEventUi.yellowCardAway.text =event.strAwayYellowCards?.replace(';','\n')
        detailEventUi.LineupDefenseAway.text =event.strAwayLineupDefense?.replace(';','\n')
        detailEventUi.LineupForwardAway.text =event.strAwayLineupForward?.replace(';','\n')
        detailEventUi.LineupGoalkeeperAway.text =event.strAwayLineupGoalkeeper?.replace(';','\n')
        detailEventUi.LineupMidfieldAway.text =event.strAwayLineupMidfield?.replace(';','\n')
        detailEventUi.LineupSubstitutesAway.text =event.strAwayLineupSubstitutes?.replace(';','\n')
    }

    override fun onResponseFailure(throwable: Throwable) {
       toast("Maaf Ada Keasalahan")
    }
}

class DetailEventActivityUI : AnkoComponent<DetailEventActivity> {

    lateinit var namaEvent: TextView
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
        return verticalLayout {
            cardView {
                lparams {
                    height = dip(wrapContent)
                    width = dip(400)
                    topMargin = dip(6)
                    bottomMargin = dip(6)
                    rightMargin = dip(3)
                    leftMargin = dip(3)
                }

                radius = 8f
                cardElevation = dip(6).toFloat()


                verticalLayout {
                    namaEvent = textView("namaEvent ") {
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

                    linearLayout {
                        gravity = Gravity.CENTER_HORIZONTAL
                        verticalLayout {
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

                        intHomeScore = textView {
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

                        verticalLayout {
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
                    imageView {
                        id = R.id.imageFavorit
                        imageResource = R.drawable.btn_favorites

                    }.lparams {
                        width = dip(50)
                        height = dip(50)
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
                }
                progressBar = progressBar().lparams {
                    gravity = Gravity.CENTER_HORIZONTAL
                }
            }



                scrollView {
                    verticalLayout {
                        lparams(matchParent, wrapContent)
                        tableLayout {
                            lparams(matchParent, wrapContent)
                            tableRow {
                                textView("Goal Detail") {
                                    typeface = Typeface.DEFAULT_BOLD
                                    width = dip(200)
                                }

                            }
                        }
                        tableLayout {
                            tableRow {
                                goalDetail = textView("-").lparams{
                                    width = dip(200)
                                }
                                goalDetailAway = textView("-").lparams{
                                    width = dip(200)
                                }
                            }

                            tableRow {
                                textView("Red Cards") {
                                    typeface = Typeface.DEFAULT_BOLD

                                }
                            }

                            tableRow {
                                redCard = textView("-")
                                redCardAway = textView("-")
                            }

                            tableRow {
                                textView("Yellow Cards") {
                                    typeface = Typeface.DEFAULT_BOLD

                                }
                            }

                            tableRow {
                                yellowCard = textView("-")
                                yellowCardAway = textView("-")
                            }
//
                            tableRow {
                                textView("Lineup Goal keeper")
                                {
                                    typeface = Typeface.DEFAULT_BOLD

                                }
                            }
//
                            tableRow {
                                LineupGoalkeeper = textView("-")
                                LineupGoalkeeperAway = textView("-")
                            }
//
                            tableRow {
                                textView("Line up Defense")
                                {
                                    typeface = Typeface.DEFAULT_BOLD

                                }
                            }
//
                            tableRow {
                                LineupDefense = textView("-")
                                LineupDefenseAway = textView("-")
                            }
//
                            tableRow {
                                textView("Line up Midfield") {
                                    typeface = Typeface.DEFAULT_BOLD

                                }
                            }
                            tableRow {
                                LineupMidfield = textView("-")
                                LineupMidfieldAway = textView("-")
                            }
//
                            tableRow {
                                textView("Line up Forward") {
                                    typeface = Typeface.DEFAULT_BOLD

                                }
                            }
//
                            tableRow {
                                LineupForward = textView("-")
                                LineupForwardAway = textView("-")
                            }

//
                            tableRow {
                                textView("Line up Substitutes") {
                                    typeface = Typeface.DEFAULT_BOLD

                                }
                            }
//
                            tableRow {
                                LineupSubstitutes = textView("-")
                                LineupSubstitutesAway = textView("-")
                            }
//
                            tableRow {
                                textView("Formation") {
                                    typeface = Typeface.DEFAULT_BOLD

                                }
                            }
                            tableRow {
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







