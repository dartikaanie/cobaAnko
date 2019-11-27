package com.example.sub1

import android.graphics.Color
import android.graphics.fonts.Font
import android.graphics.fonts.FontFamily
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.print.PrintAttributes
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.marginTop
import com.bumptech.glide.Glide
import com.example.myapplication.Item
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class DetailLigaActivity : AppCompatActivity() {

    lateinit var gambarDetail : ImageView
    lateinit var nameTextView: TextView
    lateinit var isiTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout(){
            relativeLayout(){
                backgroundColor = Color.LTGRAY
                lparams(matchParent, 450)
            }

            verticalLayout {
                lparams(matchParent, matchParent)
                verticalLayout {
                    lparams {
                        width = matchParent
                        height = wrapContent
                        topMargin = dip(80)
                        bottomMargin = dip (20)
                    }
                    gravity = Gravity.CENTER_HORIZONTAL

                    cardView(){
                        lparams{
                            height = dip(180);
                            width = dip(180)
                            bottomMargin = dip (20)
                        }
                        radius = 8f
                        cardElevation = dip(10).toFloat()

                        gambarDetail =  imageView().lparams {
                            height = matchParent
                            width = matchParent
                            gravity = Gravity.CENTER_HORIZONTAL
                            padding = dip(20)
                            margin = dip(10)
                        }

                    }

                    nameTextView = textView("Nama Club"){
                        textColor = Color.BLACK
                        textSize = sp(12).toFloat()
                    }.lparams {
                        width = wrapContent
                        height= wrapContent

                    }
                }
                scrollView {
                    verticalLayout(){
                        lparams(matchParent, wrapContent)
                        isiTextView = textView("KONTEN"){
                            textSize = sp(6).toFloat()
                        }.lparams {
                            leftMargin = dip(20)
                            width = matchParent
                            height = wrapContent
                            rightMargin = dip(20)
                        }
                    }
                }


            }
      }


        val dataLiga: Item? = intent.getParcelableExtra<Item>("liga")
        toast(dataLiga?.name.toString())
        Glide.with(this).load(dataLiga?.image).into(gambarDetail)
        nameTextView.text = dataLiga?.name
        isiTextView.text = dataLiga?.isi


        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
