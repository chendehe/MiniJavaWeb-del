package com.chendehe.util.jfreechart.chart.impl;

import com.chendehe.util.jfreechart.entity.XYChartEntity;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart extends XYChartAbstract {

  public JFreeChart createBarChart(XYChartEntity entity) {

    // 2：创建Chart
    String title = entity.getTitle();
    String categoryLabel = entity.getCategoryLabel();
    String valueLabel = entity.getValueLabel();
    DefaultCategoryDataset dataSet = createDataSet(entity);
    JFreeChart chart = ChartFactory.createBarChart(title, categoryLabel, valueLabel, dataSet);

    createChart(chart);

    LegendTitle legend = chart.getLegend();
    createLegend(legend);

    CategoryPlot plot = chart.getCategoryPlot();
    createBasePlot(plot);

    BarRenderer renderer = (BarRenderer) plot.getRenderer();
    renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
    // 设置柱子最大宽度
    renderer.setMaximumBarWidth(0.075);
    // 显示柱状图上的值
    renderer.setBaseItemLabelsVisible(false);

    return chart;
  }

}
