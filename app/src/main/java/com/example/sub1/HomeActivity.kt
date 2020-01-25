package com.example.sub1

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sub1.fragment.FavoritFragment
import com.example.sub1.fragment.LigaFragment
import com.example.sub1.fragment.MatchFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView

class HomeActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadFragment(LigaFragment(), savedInstanceState)
        relativeLayout {
            lparams {
                width = matchParent
                height = matchParent
            }
            frameLayout {
                id = R.id.content
            }.lparams {
                above(R.id.linearNav)
                width = matchParent
                height = matchParent
            }
            linearLayout {
                id = R.id.linearNav

                bottomNavigationView {
                    id = R.id.btmNav
                    inflateMenu(R.menu.bottom_nav_menu)
                    itemBackgroundResource = R.drawable.drawable_selector
                    itemIconTintList = resources.getColorStateList(R.drawable.color_selector, theme)
                    itemTextColor = resources.getColorStateList(R.drawable.color_selector, theme)

                }.lparams {
                    gravity = Gravity.BOTTOM
                    height = wrapContent
                    width = matchParent

                }.setOnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.liga_navigation -> {
                            loadFragment(LigaFragment(), savedInstanceState)
                        }
                        R.id.match_navigation -> {
                            loadFragment(MatchFragment(), savedInstanceState)
                        }
                        R.id.favorit_navigation -> {
                            loadFragment(FavoritFragment(), savedInstanceState)
                        }
                    }

                    false
                }
            }.lparams {
                alignParentBottom()
                height = wrapContent
                width = matchParent
            }

        }
    }


    private fun loadFragment(fragment : Fragment, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit()
        }
    }



}
