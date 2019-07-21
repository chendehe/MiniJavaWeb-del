package com.chendehe.util.jfreechart;

import com.chendehe.util.jfreechart.entity.PieChartEntity;
import com.chendehe.util.jfreechart.entity.XYChartEntity;
import com.chendehe.util.jfreechart.utils.ChartUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jfree.chart.JFreeChart;

public class Test {

  public static void main(String[] args) {
    XYChartEntity entity = new XYChartEntity();
    entity.setCategoryLabel("fsafas");
    entity.setValueLabel("Rainfall (mm)");
    entity.setTitle("Monthly Average Rainfall");
    entity.setCategories(Arrays
        .asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
            "Dec"));
    Map<String, List<Double>> map = new HashMap<>();
    map.put("Tokyo", Arrays
        .asList(49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4));
    map.put("New York",
        Arrays.asList(83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3));
    map.put("London",
        Arrays.asList(48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2));
    map.put("Berlin",
        Arrays.asList(42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1));
    entity.setMap(map);

    //柱状图
    JFreeChart barChart = ChartFactory.createBarChart(entity);
    //折线图
    JFreeChart lineChart = ChartFactory.createLineChart(entity);

    /////////////////////
    PieChartEntity pieChartEntity = new PieChartEntity();
    pieChartEntity.setTitle("Contents of Highsoft's weekly fruit delivery");
    pieChartEntity.setValues(Arrays.asList(8, 3, 1, 6, 8, 4, 4, 1, 1));
    pieChartEntity.setCategories(
        Arrays.asList("Bananas", "Kiwi", "Mixed nuts", "Oranges", "Apples", "Pears", "Clementines",
            "Reddish (bag)", "Grapes (bunch)"));
    //饼图
    JFreeChart pieChart = ChartFactory.createPieChart(pieChartEntity);
    //环形图
    JFreeChart ringChart = ChartFactory.createRingChart(pieChartEntity);

    ChartUtils.saveAsFile(barChart, "D:\\BarChart.jpg");
    ChartUtils.saveAsFile(lineChart, "D:\\LineChart.jpg");
    ChartUtils.saveAsFile(pieChart, "D:\\PieChart.jpg");
    ChartUtils.saveAsFile(ringChart, "D:\\RingChart.jpg");
//    byte[] bytes = ChartUtils.convertToByte(barChart);
//    byte[] bytes1 = ChartUtils.convertToByte(lineChart);
//    byte[] bytes2 = ChartUtils.convertToByte(pieChart);
//    byte[] bytes3 = ChartUtils.convertToByte(ringChart);
  }
}
