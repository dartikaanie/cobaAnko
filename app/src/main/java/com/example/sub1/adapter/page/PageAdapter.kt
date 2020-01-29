package com.example.sub1.adapter.page

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sub1.fitur.EventLiga.NextMatchFragment
import com.example.sub1.fitur.EventLiga.PrevMatchFragment
import com.example.sub1.fitur.TeamsLiga.TeamsFragment
import com.example.sub1.fitur.klasemen.KlasemenFragment

class PageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    // sebuah list yang menampung objek Fragment
    private val pages = listOf(
        NextMatchFragment(),
        PrevMatchFragment(),
        TeamsFragment(),
        KlasemenFragment()
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
            0 -> "Next Match"
            1 -> "Prev Match"
            2 -> "Teams"
            else -> "Klasemen"
        }
    }
}