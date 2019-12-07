package com.example.sub1.View

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginTop
import com.example.sub1.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.sdk27.coroutines.onClick

class EventUI: AnkoComponent<Context> {

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        return cardView {
            lparams{
                height = dip(wrapContent);
                width = dip(400);
                topMargin = dip(6);
                bottomMargin = dip(6);
                rightMargin = dip(3);
                leftMargin = dip(3)
            }
            padding = dip(10)
            radius = 8f
            cardElevation = dip(6).toFloat()


            verticalLayout {
                textView("nama_event ") {
                    id = R.id.nama_event
                }.lparams {
                    gravity = Gravity.CENTER_HORIZONTAL
                }
                textView("TES strSport") {
                    id = R.id.strSport
                }.lparams {
                    gravity = Gravity.CENTER_HORIZONTAL
                }
                textView("TES dateEvent") {
                    id = R.id.dateEvent
                }.lparams {
                    gravity = Gravity.CENTER_HORIZONTAL
                }

                linearLayout() {
                    gravity = Gravity.CENTER_HORIZONTAL
                    verticalLayout() {
                        imageView {
                            id = R.id.gambarHome
                            imageResource = R.drawable.img

                        }.lparams {
                            height = dip(100)
                            width = dip(100)
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                        textView("TES strHomeTeam") {
                            id = R.id.strHomeTeam
                        }
                    }.lparams{
                        width = dip(wrapContent)
                        gravity = Gravity.CENTER_HORIZONTAL
                        gravity = Gravity. CENTER_VERTICAL
                        margin = dip(10)
                    }

                    textView() {
                        id = R.id.intHomeScore
                        textSize = dip(12).toFloat()
                    }.lparams{
                        width = dip(wrapContent)
                        gravity = Gravity.CENTER_HORIZONTAL
                        gravity = Gravity. CENTER_VERTICAL
                        margin = dip(3)
                    }
                    textView(":"){
                        textSize = dip(12).toFloat()
                    }.lparams{
                        width = dip(10)
                        gravity = Gravity.CENTER_HORIZONTAL
                        gravity = Gravity. CENTER_VERTICAL
                        margin = dip(3)
                    }

                    textView("TES intAwayScore") {
                        id = R.id.intAwayScore
                        textSize = dip(12).toFloat()
                    }.lparams{
                        width = dip(wrapContent)
                        gravity = Gravity.CENTER_HORIZONTAL
                        gravity = Gravity. CENTER_VERTICAL
                        margin = dip(3)
                    }

                    verticalLayout() {
                        imageView {
                            id = R.id.gambarAway
                            imageResource = R.drawable.img

                        }.lparams {
                            height = dip(100)
                            width = dip(100)
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                        textView("TES strAwayTeam") {
                            id = R.id.strAwayTeam
                        }.lparams(wrapContent, wrapContent)
                    }.lparams{
                        width = dip(wrapContent)
                        gravity = Gravity.CENTER_HORIZONTAL
                        gravity = Gravity. CENTER_VERTICAL
                        margin = dip(10)
                    }
                }
            }

        }
    }
}