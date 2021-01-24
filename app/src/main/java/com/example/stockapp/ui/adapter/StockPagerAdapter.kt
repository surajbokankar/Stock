package com.example.stockapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class StockPagerAdapter(fm: FragmentManager, val list:ArrayList<Fragment>):FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
       return list[position]
    }
}