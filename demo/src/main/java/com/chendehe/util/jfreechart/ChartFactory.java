package com.chendehe.util.jfreechart;

import com.chendehe.util.jfreechart.chart.impl.BarChart;
import com.chendehe.util.jfreechart.chart.impl.LineChart;
import com.chendehe.util.jfreechart.chart.impl.PieChart;
import com.chendehe.util.jfreechart.chart.impl.RingChart;
import com.chendehe.util.jfreechart.entity.PieChartEntity;
import com.chendehe.util.jfreechart.entity.XYChartEntity;
import com.chendehe.util.jfreechart.theme.DefaultTheme;
import org.jfree.chart.JFreeChart;

public class ChartFactory {

  private ChartFactory() {
  }

  static {
    DefaultTheme.initTheme();
  }

  public static JFreeChart createBarChart(XYChartEntity entity) {
    return new BarChart().createBarChart(entity);
  }

  public static JFreeChart createLineChart(XYChartEntity entity) {
    return new LineChart().createLineChart(entity);
  }

  public static JFreeChart createPieChart(PieChartEntity entity) {
    return new PieChart().createPieChart(entity);
  }

  public static JFreeChart createRingChart(PieChartEntity entity) {
    return new RingChart().createRingChart(entity);
  }

}
