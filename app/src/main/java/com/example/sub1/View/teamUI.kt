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

class teamUI: AnkoComponent<Context> {

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        return cardView {
            lparams{
                height = dip(50)
                width = dip(80)
                topMargin = dip(6)
                bottomMargin = dip(6)
                rightMargin = dip(3)
                leftMargin = dip(3)
            }

            radius = 5f
            cardElevation = dip(6).toFloat()
            padding = dip (5)

            verticalLayout {
                imageView {
                    id = R.id.gambar
                }.lparams{
                    padding = dip (10)
                }
            }

        }
    }
}