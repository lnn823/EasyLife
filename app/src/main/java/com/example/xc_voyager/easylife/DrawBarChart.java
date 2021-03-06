package com.example.xc_voyager.easylife;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class DrawBarChart extends AppCompatActivity {

    ArrayList<String> chart_data = new ArrayList<String>();
    int data_num;
    Chart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_bar_chart);
        setTitle("Bar Chart");
        initData();
        initBarChart();
    }

    private void initData(){
        Intent intent = getIntent();
        chart_data = intent.getStringArrayListExtra("chart_data");
        data_num = chart_data.size()/2;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.chart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_save){
            ChartToImage.chartToImage(chart, "Bar Chart");
            Toast.makeText(getApplicationContext(), "Your Bar Chart has been saved!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void initBarChart(){
        BarChart barChart = (BarChart)findViewById(R.id.barchart);
        chart = barChart;
        List<BarEntry> entries = new ArrayList<>();
        Float x, y;
        String str;
        for(int i = 0; i < data_num; i++){
            str = chart_data.get(2*i);
            x = Float.parseFloat(str);
            str = chart_data.get(2*i+1);
            y = Float.parseFloat(str);
            entries.add(new BarEntry(x, y));
        }

        BarDataSet barDataSet = new BarDataSet(entries, "Bar Data");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setDrawValues(true);
        barDataSet.setValueTextSize(12);

        Description description = new Description();
        description.setText("Bar Chart");
        description.setTextSize(12);
        description.setTextColor(Color.DKGRAY);
        barChart.setDescription(description);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f);
        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.invalidate();
    }
}
