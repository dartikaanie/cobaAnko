package com.example.sub1.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sub1.event.NextMatchFragment
import com.example.sub1.event.PrevMatchFragment

class PageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    // sebuah list yang menampung objek Fragment
    private val pages = listOf(
        NextMatchFragment(),
        PrevMatchFragment()
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
            else -> "Prev Match"
        }
    }
}