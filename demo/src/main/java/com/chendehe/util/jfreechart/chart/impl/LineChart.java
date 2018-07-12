package com.chendehe.util.jfreechart.chart.impl;

import com.chendehe.util.jfreechart.entity.XYChartEntity;
import java.awt.BasicStroke;
import java.text.NumberFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class LineChart extends XYChartAbstract {

  public JFreeChart createLineChart(XYChartEntity entity) {

    // 2：创建Chart
    String title = entity.getTitle();
    String categoryLabel = entity.getCategoryLabel();
    String valueLabel = entity.getValueLabel();
    DefaultCategoryDataset dataSet = createDataSet(entity);
    JFreeChart chart = ChartFactory.createLineChart(title, categoryLabel, valueLabel, dataSet);

    createChart(chart);

    LegendTitle legend = chart.getLegend();
    createLegend(legend);

    CategoryPlot plot = chart.getCategoryPlot();
    createBasePlot(plot);

    LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
    renderer.setBaseStroke(new BasicStroke(1.5F));

    // 图上显示值
    renderer.setBaseItemLabelsVisible(true);
    renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator(
        StandardCategoryItemLabelGenerator.DEFAULT_LABEL_FORMAT_STRING,
        NumberFormat.getInstance()));
    renderer.setBasePositiveItemLabelPosition(
        new ItemLabelPosition(ItemLabelAnchor.OUTSIDE1, TextAnchor.BOTTOM_CENTER));
    // 数据点绘制形状
    renderer.setBaseShapesVisible(true);

    return chart;
  }

}
