package com.example.sub1.View

import android.content.Context
import android.view.Gravity
import android.view.View
import androidx.core.content.ContextCompat
import com.example.sub1.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class TeamUI: AnkoComponent<Context> {

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        return cardView {
            lparams{
                height = dip(120)
                width = matchParent
                margin = dip(5)
            }

            radius = 5f
            cardElevation = dip(6).toFloat()
            padding = dip (5)

            linearLayout {
                imageView {
                    id = R.id.gambar
                }.lparams{
                    width = dip(150)
                    height = matchParent
                    padding = dip (10)
                    rightMargin = dip(5)
                    gravity = Gravity.CENTER_VERTICAL
                }

                verticalLayout {
                    textView{
                        id = R.id.nama_tim
                        textColor = ContextCompat.getColor(context, R.color.colorAccent)
                        textSize = sp(8).toFloat()
                    }
                    textView{
                        id = R.id.deskripsi_team
                        textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                    }
                }.lparams{
                    padding = dip (10)
                }

            }

        }
    }
}