package com.example.mpandroidchartdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

public class PieDataActivity extends AppCompatActivity {

    private PieChart mPieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPieChart = (PieChart) findViewById(R.id.spacer_pie_chart);

        PieData mPieData = getPieData(5,100);

        showChart(mPieChart,mPieData);

    }

    /**
     * 显示
     * @param pieChart
     * @param mPieData
     */
    private void showChart(PieChart pieChart, PieData mPieData) {

       // pieChart.setHoleColorTransparent(true);
        pieChart.setHoleRadius(60f);//半径
        pieChart.setTransparentCircleRadius(64f);//半透明圈
        //pieChart.setHoleRadius(0);//实心圆

        //图表的描述
        pieChart.setDescription("测试饼状图");

        pieChart.setDrawCenterText(true);

        pieChart.setDrawHoleEnabled(true);

        pieChart.setRotationAngle(90);  //初始化旋转角度

        //设置可以手动旋转
        pieChart.setRotationEnabled(true);

        pieChart.setUsePercentValues(true);//显示成百分比

        pieChart.setCenterText("Quarterly Revenue");

        //设置数据
        pieChart.setData(mPieData);

       // pieChart.highlightValue(null);
       // pieChart.invalidate();  //重绘

        //设置比例图
        Legend mLegend = pieChart.getLegend();

        mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART); //最右边显示


       // mLegend.setForm(Legend.LegendForm.LINE); //设置比例图的形状，默认是方形

        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(5f);

        pieChart.animateXY(1000,1000); //设置动画

    }


    /**
     * 分成多少部分
     * @param count    分成的份
     * @param range    分得到的百分比
     * @return
     */
    private PieData getPieData(int count, float range) {

        //用来表示每个饼状图的描述
        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            xValues.add("Quarterly" + (i + 1)); //饼块上显示成
            //Quarterly1  Quarterly2  Quarterly3 Quarterly4

        }
        //yValues用来表示封装每个饼块的实际数据
        ArrayList<Entry> yValues = new ArrayList<>();


        /**
         * 饼图数
         * 将一个饼图分成五个部分，五个部分的值为14:12:33:20:12
         * 也就是14%
         */
        float quarterly1 = 14;
        float quarterly2 = 14;
        float quarterly3 = 33;
        float quarterly4 = 30;
        float quarterly5 = 12;

        yValues.add(new Entry(quarterly1, 0));
        yValues.add(new Entry(quarterly2, 1));
        yValues.add(new Entry(quarterly3, 2));
        yValues.add(new Entry(quarterly4, 3));
        yValues.add(new Entry(quarterly5, 4));

        PieDataSet pieDataSet = new PieDataSet(yValues,"Quarterly .....");//显示在比例图上

        pieDataSet.setSliceSpace(0f);//设置饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<>();

        //饼图颜色
        colors.add(Color.rgb(205,32,22));
        colors.add(Color.rgb(114,118,22));
        colors.add(Color.rgb(255,123,111));
        colors.add(Color.rgb(205,32,22));
        colors.add(Color.rgb(25,204,225));

        //关联颜色
        pieDataSet.setColors(colors);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi/160f);
        pieDataSet.setSelectionShift(px); //选中态多出的长度

        PieData pieData = new PieData(xValues,pieDataSet);



        return pieData;
    }
}
