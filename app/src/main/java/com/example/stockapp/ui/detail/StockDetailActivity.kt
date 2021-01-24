package com.example.stockapp.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.stockapp.R
import com.example.stockapp.common.Constant
import com.example.stockapp.common.Constant.RANGE
import com.example.stockapp.ui.detail.model.ChartDataModel
import com.example.stockapp.ui.detail.model.Quote
import com.example.stockapp.ui.detail.model.StockDetailParent
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_stock_detail.*
import kotlinx.android.synthetic.main.stock_item_view.stock_gain
import kotlinx.android.synthetic.main.stock_item_view.stock_identifier
import kotlinx.android.synthetic.main.stock_item_view.stock_perc
import kotlinx.android.synthetic.main.stock_item_view.stock_symbol

class StockDetailActivity:AppCompatActivity() {

    lateinit var mViewModel:StockDetailViewModel
    lateinit var mQuotes:Quote
    var symbol:String=""
    var currency:String=""
    var mBottomSheet:StockBottomSheet?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_detail)
        initView()
    }

    private fun initView() {
        if (intent.hasExtra(Constant.SYMBOL)){
            symbol=intent.getStringExtra(Constant.SYMBOL)!!
            val identifier=intent.getStringExtra(Constant.IDENTIFIER)
            val gain=intent.getStringExtra(Constant.GAIN)
            val change=intent.getStringExtra(Constant.CHANGE)
            val status=intent.getIntExtra(Constant.STATUS,0)
            stock_symbol.text=symbol
            stock_identifier.text=identifier
            stock_gain.text=gain
            stock_perc.text=change
            stock_gain.setTextColor(resources.getColor(status))
            loadLineChart(symbol,RANGE)
        }
    }

    private fun loadLineChart(symbol:String?,range:String) {
        mViewModel=ViewModelProviders.of(this).get(StockDetailViewModel::class.java)
        mViewModel?.loadStocks(symbol!!, range)
        mViewModel.stocksData.observe(this, Observer {
           loadLineChartView(it)
        })

    }

    private fun loadLineChartView(it: StockDetailParent?) {
        ChartData.renderLineChart(line_chart,it!!.chart)
        mQuotes=it?.chart.result[0].indicators.quote[0]
        currency=it?.chart.result[0].meta.currency

        line_chart.setOnChartValueSelectedListener(object :OnChartValueSelectedListener{
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                val entry = e!!
                val value = entry.x
                val position = value.toInt()
                if(mQuotes?.open.size!! >position){
                    if(mQuotes.open[position]>0){
                        callBottomSheet(position)
                    }else{
                        Toast.makeText(this@StockDetailActivity,"Stock haven't opened today", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onNothingSelected() {

            }

        })
    }

    private fun callBottomSheet(position: Int) {
        val model=ChartDataModel()
        model.symbol=symbol
        model.currency=currency
        model.close=mQuotes.close[position]
        model.open=mQuotes.open[position]
        model.high=mQuotes.high[position]
        model.low=mQuotes.low[position]
        if(mBottomSheet!=null)mBottomSheet?.dismiss()
        mBottomSheet=StockBottomSheet(model)
        mBottomSheet?.show(supportFragmentManager,"StockView")
    }
}