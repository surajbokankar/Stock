package com.example.stockapp.ui.adapter

import android.content.Context
import com.example.stockapp.R
import com.example.stockapp.databinding.StockItemViewBinding
import com.example.stockapp.ui.stock.listener.StockListener
import com.example.stockapp.ui.stock.model.StockParentItem
import com.suraj.edelivery.base.GenericAdapter
import java.text.DecimalFormat

class StockItemAdapter(context: Context, list: ArrayList<StockParentItem>,callBack:StockListener):GenericAdapter<StockParentItem, StockItemViewBinding>(context, list) {
    val mCallBack=callBack
    override val layoutResId: Int
        get() = R.layout.stock_item_view

    override fun onBindData(
            model: StockParentItem,
            position: Int,
            dataBinding: StockItemViewBinding
    ) {
        dataBinding?.stockIdentifier.text=model.identifier
        if(model.change!=null){
            var color:Int=R.color.color_c6c6c6
            dataBinding?.stockGain.text="${model.open}"
            if (model.change>0.0){
                color=R.color.purple_500
            }
            if(model.change<0.0){
                color=R.color.color_ec4646
            }
            dataBinding?.stockGain.setTextColor(context.resources.getColor(color))
        }
        val df = DecimalFormat("####0.00")
        dataBinding?.stockSymbol.text=model.symbol
        dataBinding.stockPerc.text="${df.format(model.change)}(${model.pChange}%)"
    }

    override fun onItemClick(model: StockParentItem, position: Int) {
           mCallBack.onStockClick(model)
    }
}