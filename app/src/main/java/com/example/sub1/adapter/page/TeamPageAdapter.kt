package com.example.sub1.adapter.page

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sub1.fitur.DetailTeam.fragment.DetailTeamInfoFragment
import com.example.sub1.fitur.DetailTeam.fragment.DetailTeamPlayerFragment

class TeamPageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    // sebuah list yang menampung objek Fragment
    private val pages = listOf(
        DetailTeamInfoFragment(),
        DetailTeamPlayerFragment()
    )

    // menentukan fragment yang akan dibuka pada posisi tertentu
    override fun getItem(position: Int): Fragment {
        return pages[position] as Fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    // judul untuk tabs
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Info Team"
            else -> "Player"
        }
    }
}