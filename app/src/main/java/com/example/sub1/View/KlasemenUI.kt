package com.example.sub1.View

import android.content.Context
import android.view.Gravity
import android.view.View
import androidx.core.content.ContextCompat
import com.example.sub1.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class KlasemenUI: AnkoComponent<Context> {

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        return cardView {
            lparams{
                height = wrapContent
                width = matchParent
                topMargin = dip(6)
                bottomMargin = dip(6)
                rightMargin = dip(3)
                leftMargin = dip(3)
            }

            radius = 5f
            cardElevation = dip(6).toFloat()
            padding = dip (5)

            verticalLayout {
                textView{
                    id = R.id.nama
                    textColor = ContextCompat.getColor(context, R.color.colorAccent)
                    textSize = sp(8).toFloat()

//                    gravity = Gravity.CENTER_HORIZONTAL
                }.lparams {
                    margin = dip(6)
                }
                linearLayout {
                    verticalLayout {
                        textView("Played")
                        textView {
                            id = R.id.played
                            textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        }
                    }.lparams{
                        margin = dip(8)
                    }

                    verticalLayout {
                        textView("Goalsfor")
                        textView{
                            id = R.id.goalsfor
                            textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        }
                    }.lparams{
                        margin = dip(8)
                    }
                    verticalLayout {
                        textView("Loss ")
                        textView{
                            id = R.id.loss
                            textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        }
                  }.lparams{
                        margin = dip(8)
                    }
                    verticalLayout {
                        textView("Win")
                        textView{
                            id = R.id.win
                            textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        }
                    }.lparams{
                        margin = dip(8)
                    }

                    verticalLayout {
                        textView("Draw")
                        textView{
                            id = R.id.draw
                            textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        }
                    }.lparams{
                        margin = dip(8)
                    }
                }.lparams(matchParent, wrapContent){
                    gravity = Gravity.CENTER_HORIZONTAL
                    width = matchParent
                    height = wrapContent
                }
            }.lparams{
                padding = dip (5)
                width = matchParent
                height = wrapContent
            }


        }
    }
}