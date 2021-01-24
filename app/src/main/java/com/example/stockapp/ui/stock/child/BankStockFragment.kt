package com.example.stockapp.ui.stock.child


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.stockapp.R
import com.example.stockapp.base.BaseFragment
import com.example.stockapp.databinding.FragmentStockBankBinding
import com.example.stockapp.ui.adapter.StockItemAdapter
import com.example.stockapp.ui.detail.ChartData
import com.example.stockapp.ui.stock.BottomSheet
import com.example.stockapp.ui.stock.StockViewModel
import com.example.stockapp.ui.stock.listener.StockListener
import com.example.stockapp.ui.stock.model.StockParentItem
import kotlinx.android.synthetic.main.fragment_stock_nifty.*

class BankStockFragment:BaseFragment<FragmentStockBankBinding>(), StockListener {
    lateinit var mViewModel: StockViewModel
    lateinit var adapter: StockItemAdapter
    lateinit var mList:ArrayList<StockParentItem>
    private var mBottomSheet: BottomSheet? = null
    private var mNormal=ArrayList<StockParentItem>()
    override fun getLayoutResId(): Int {
        return R.layout.fragment_stock_bank
    }

    override fun init(dataBinding: FragmentStockBankBinding?) {
        mViewModel= ViewModelProviders.of(this).get(StockViewModel::class.java)
        mViewModel?.loadStocks("NIFTY BANK")
        mViewModel?.stocksData.observe(this, Observer {
            setAdapter(it)
        })
        sort_view.setOnClickListener {
            mBottomSheet = BottomSheet(this)
            mBottomSheet?.show(childFragmentManager, "Bottom")
        }
    }

    private fun setAdapter(it: ArrayList<StockParentItem>?) {
        mNormal.addAll(it!!)
        mList=it!!
        adapter=StockItemAdapter(requireActivity(),mList,this)
        stock_view.adapter=adapter
    }
    override fun observeChanges() {

    }

    override fun onStockClick(item: StockParentItem) {
        val intent= ChartData.loadIntent(item)
        startActivity(intent)
    }

    override fun onSortClick(type: Int) {
          filterData(type)
    }

    private fun filterData(sort:Int) {
        if(sort==0){
            mList.sortByDescending { it.pChange }
            mList.reverse()
        }else{
            mList.sortByDescending { it.pChange }

        }
        adapter.notifyDataSetChanged()
    }
}