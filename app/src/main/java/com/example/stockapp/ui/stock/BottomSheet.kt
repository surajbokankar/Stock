package com.example.stockapp.ui.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stockapp.R
import com.example.stockapp.common.SharedPrefData
import com.example.stockapp.ui.stock.listener.StockListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_bottpm_sheet.*

class BottomSheet(listener:StockListener):BottomSheetDialogFragment(),View.OnClickListener {
    val mCallBack=listener
    override fun getTheme(): Int = R.style.BaseBottomSheetDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog = BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view= inflater.inflate(
                R.layout.layout_bottpm_sheet, container,
                false
        )
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        low.setOnClickListener(this)
        high.setOnClickListener(this)
        val currentSort=SharedPrefData.getInt(SharedPrefData.SORT_VALUE)
        if(currentSort!=null){
           when(currentSort){
               0->{
                 low.setTextColor(requireActivity()?.resources.getColor(R.color.purple_500))
               }
               1->{
                   high.setTextColor(requireActivity()?.resources.getColor(R.color.purple_500))
               }
           }
        }


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.low->{
                changeView(0)
            }
            R.id.high->{
                changeView(1)
            }
        }
    }

    private fun changeView(i: Int) {
        SharedPrefData.setInt(SharedPrefData.SORT_VALUE,i)
        mCallBack.onSortClick(i)
        dismiss()
    }
}