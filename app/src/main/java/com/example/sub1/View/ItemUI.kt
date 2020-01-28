package com.example.sub1.View

import android.content.Context
import android.view.Gravity
import android.view.View
import com.example.sub1.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.sdk27.coroutines.onClick

class ItemUI: AnkoComponent<Context> {

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        return cardView {
            lparams{
                height = dip(wrapContent)
                width = dip(180)
                topMargin = dip(6)
                bottomMargin = dip(6)
                rightMargin = dip(3)
                leftMargin = dip(3)
            }

            padding = dip(20)
            radius = 8f
            cardElevation = dip(6).toFloat()

            onClick { ctx.toast("Hello") }


            verticalLayout {
                imageView {
                    id = R.id.gambar

                }.lparams {
                    height = dip(100)
                    width = dip(130)
                    gravity = Gravity.CENTER_HORIZONTAL
                    padding = dip(20)
                }

                textView {
                    id = R.id.txtName

                }.lparams {
                    width = wrapContent
                    gravity = Gravity.CENTER_HORIZONTAL
                    padding = dip(10)
                    topMargin = dip(6)
                }
            }

        }
    }
}