package com.example.sub1.fitur.DetailTeam.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.sub1.R
import com.example.sub1.fitur.DetailTeam.DetailTeamActivity
import org.jetbrains.anko.*

class DetailTeamInfoFragment : Fragment(), AnkoComponent<Context>{


        private lateinit var activity: DetailTeamActivity
        private lateinit var progressBar: ProgressBar
        private lateinit var deskripsi : TextView

        override fun onAttach(context: Context) {
            super.onAttach(context)
            activity = context as DetailTeamActivity
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            return createView(AnkoContext.create(requireContext()))
        }

        override fun createView(ui: AnkoContext<Context>): View = with(ui){
            verticalLayout {
                scrollView {
                    verticalLayout {
                        lparams(matchParent, wrapContent)
                        textView("DESCIPTION") {
                            textColor = ContextCompat.getColor(
                                context,
                                R.color.colorAccent
                            )
                        }.lparams {
                            margin = dip(10)
                        }
                        deskripsi = textView {
                            textColor = ContextCompat.getColor(
                                context,
                                R.color.colorPrimary
                            )
                            text = activity.team.strDescriptionEN
                            textSize = sp(5).toFloat()
                        }.lparams {
                            leftMargin = dip(15)
                            width = matchParent
                            height = matchParent
                            rightMargin = dip(8)
                        }
                    }
                }
            }
        }
    }