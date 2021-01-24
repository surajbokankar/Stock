package com.example.stockapp.ui.detail

import android.content.Intent
import android.graphics.Color
import androidx.core.graphics.toColorInt
import com.example.stockapp.App
import com.example.stockapp.R
import com.example.stockapp.common.Constant
import com.example.stockapp.common.CustomMarkerView
import com.example.stockapp.ui.detail.model.Chart
import com.example.stockapp.ui.stock.model.StockParentItem
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList


object ChartData {
    fun renderLineChart(linechart: LineChart, model: Chart) {
        val entries = model.result[0].indicators.quote[0].open.mapIndexedTo(ArrayList<Entry>()) { i, model -> Entry(
            model.toFloat(),
            i.toFloat()
        ) }
        val dataset = LineDataSet(entries, "")
        dataset.isDrawCubicEnabled
        dataset.setDrawFilled(true)
        dataset.setDrawHighlightIndicators(true)
        //
        dataset.lineWidth = 1.95f
        dataset.circleRadius = 5f
        dataset.color = Color.parseColor("#EFEFFF")
        dataset.setDrawCircleHole(false)
        dataset.setDrawCircles(false)
        dataset.highLightColor = Color.WHITE
        dataset.setDrawValues(false)

        val mv = CustomMarkerView(App.instance, R.layout.layout_view)
      //  linechart.setMarkerView(mv)

        val lineDataModel=java.util.ArrayList<ILineDataSet>()
        model.result[0].timestamp.forEach {
            val cal1 = Calendar.getInstance()
            cal1.time= Date(it.toLong())
            val d = LineDataSet(entries, cal1[Calendar.DAY_OF_MONTH].toString())
            d.lineWidth = 2.0f
            d.circleRadius = 4f
            d.setDrawCircleHole(false)
            val color = R.color.purple_500
            d.color = color
            d.setCircleColor(color)
            d.isDrawCircleHoleEnabled
            lineDataModel.add(d)
        }
        val labels = model.result[0].timestamp.map { it }
        val data = LineData(lineDataModel)
      //  linechart.setDescription(Description().text="Stock")
        linechart.data = data
        linechart.xAxis.setDrawGridLines(false)
        linechart.axisLeft.setDrawGridLines(false)
        linechart.xAxis.axisLineColor = R.color.purple_500//top line
        linechart.xAxis.textColor = R.color.purple_500
        linechart.axisLeft.axisLineColor = R.color.purple_500//left line
        linechart.axisLeft.textColor = R.color.purple_500
        linechart.axisRight.axisLineColor = R.color.purple_500//right line
        linechart.axisRight.textColor = R.color.purple_500
        linechart.setDrawBorders(false)
        linechart.setDrawGridBackground(false)
        linechart.isAutoScaleMinMaxEnabled = false
        linechart.description.text="Stock Journey"


        //Custom marker
        linechart.setDrawMarkerViews(true)
      //  val markerView = CustomMarkerView(applicationContext, R.layout.marker_view, csvDataset.models)
        //linechart.markerView = markerView
        linechart.setTouchEnabled(true)

        // remove axis
        //val leftAxis = linechart.axisLeft
        //leftAxis.isEnabled = false
        //val rightAxis = linechart.axisRight
        //rightAxis.isEnabled = false

        //val xAxis = linechart.xAxis
        //xAxis.isEnabled = false

        // hide legend
        val legend = linechart.legend
        legend.isEnabled = false
        //linechart.setViewPortOffsets(0f, 0f, 0f, 0f) //remove padding
        linechart.invalidate()
        linechart.animateXY(300, 300)
    }

    fun loadIntent(item: StockParentItem):Intent{
        val intent= Intent(App.instance, StockDetailActivity::class.java)
        intent.putExtra(Constant.SYMBOL, item.symbol)
        intent.putExtra(Constant.IDENTIFIER, item.identifier)
        val df = DecimalFormat("####0.00")
        val change="${df.format(item.change)}(${item.pChange}%)"
        intent.putExtra(Constant.CHANGE, change)
        intent.putExtra(Constant.GAIN, item.open.toString())
        if(item.change!=null){
            var color:Int=R.color.color_c6c6c6
            if (item.change>0.0){
                color=R.color.purple_500
            }
            if(item.change<0.0){
                color=R.color.color_ec4646
            }
            intent.putExtra(Constant.STATUS, color)
        }
        return intent
    }
}