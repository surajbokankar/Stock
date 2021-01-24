package com.example.stockapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stockapp.R
import com.example.stockapp.ui.detail.model.ChartDataModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_chart_bottom.*
import java.lang.Exception
import java.text.DecimalFormat

class StockBottomSheet(model:ChartDataModel):BottomSheetDialogFragment() {
    val mModel=model
    override fun getTheme(): Int = R.style.BaseBottomSheetDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog = BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(
            R.layout.layout_chart_bottom, container,
            false
        )
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try{
            val df = DecimalFormat("####0.00")
            symobol_txt.text=mModel.symbol
            currency.text=": ${mModel.currency}"
            open_txt.text="Open: ${df.format(mModel.open)}"
            low_txt.text="Low: ${df.format(mModel.low)}"
            high_txt.text="High: ${df.format(mModel.high)}"
            close_txt.text="Close: ${df.format(mModel.close)}"
        }catch (e: Exception){
            e.printStackTrace()
        }

    }
}