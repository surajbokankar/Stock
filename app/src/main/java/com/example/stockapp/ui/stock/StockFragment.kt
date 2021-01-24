package com.example.stockapp.ui.stock

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stockapp.R
import com.example.stockapp.base.BaseFragment
import com.example.stockapp.databinding.FragmentHomeBinding
import com.example.stockapp.ui.adapter.StockPagerAdapter
import com.example.stockapp.ui.stock.child.BankStockFragment
import com.example.stockapp.ui.stock.child.ITStockFragment
import com.example.stockapp.ui.stock.child.NiftyFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*

class StockFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var homeViewModel: StockViewModel
    private lateinit var pagerAdapter: StockPagerAdapter
    override fun getLayoutResId(): Int {
        return R.layout.fragment_home
    }

    override fun init(dataBinding: FragmentHomeBinding?) {
        setTabs()
    }

    private fun setTabs() {
        val list = getFragmentList()
        tabLayout.addTab(tabLayout!!.newTab().setText(resources.getString(R.string.tab_one)))
        tabLayout.addTab(tabLayout!!.newTab().setText(resources.getString(R.string.tab_two)))
        tabLayout.addTab(tabLayout!!.newTab().setText(resources.getString(R.string.tab_three)))
        setTabListener()
        viewPager.offscreenPageLimit=list.size
        pagerAdapter = StockPagerAdapter(childFragmentManager, list)
        viewPager.adapter = pagerAdapter
    }

    private fun setTabListener() {
        viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager?.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private fun getFragmentList(): ArrayList<Fragment> {
        val list = ArrayList<Fragment>()
        list.add(NiftyFragment())
        list.add(ITStockFragment())
        list.add(BankStockFragment())
        return list
    }

    override fun observeChanges() {
        homeViewModel =
            ViewModelProvider(this).get(StockViewModel::class.java)
    }

}