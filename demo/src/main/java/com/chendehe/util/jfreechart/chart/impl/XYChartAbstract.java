package com.chendehe.util.jfreechart.chart.impl;

import com.chendehe.util.jfreechart.chart.Chart;
import com.chendehe.util.jfreechart.entity.XYChartEntity;
import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

abstract class XYChartAbstract implements Chart {

  DefaultCategoryDataset createDataSet(XYChartEntity entity) {
    // 创建类别数据集合
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    entity.getMap().forEach((series, values) -> {
      for (int i = 0; i < values.size(); i++) {
        dataset.setValue(values.get(i), series, entity.getCategories().get(i));
      }
    });
    return dataset;
  }

  void createChart(JFreeChart chart) {
    // 必须设置文本抗锯齿
    chart.setTextAntiAlias(false);
  }

  void createLegend(LegendTitle legend) {
    // 设置标注无边框
    legend.setFrame(new BlockBorder(Color.WHITE));
  }

  void createBasePlot(CategoryPlot plot) {
    plot.setNoDataMessage(NO_DATA_MSG);
    plot.setInsets(new RectangleInsets(10, 10, 5, 10));

    setXAixs(plot);
    setYAixs(plot);
  }

  /**
   * 设置类别图表(CategoryPlot) X坐标轴线条颜色和样式
   */
  private void setXAixs(CategoryPlot plot) {
    Color lineColor = new Color(31, 121, 170);
    plot.getDomainAxis().setAxisLinePaint(lineColor);// X坐标轴颜色
    plot.getDomainAxis().setTickMarkPaint(lineColor);// X坐标轴标记|竖线颜色

  }

  /**
   * 设置类别图表(CategoryPlot) Y坐标轴线条颜色和样式 同时防止数据无法显示
   */
  private void setYAixs(CategoryPlot plot) {
    Color lineColor = new Color(192, 208, 224);
    ValueAxis axis = plot.getRangeAxis();
    axis.setAxisLinePaint(lineColor);// Y坐标轴颜色
    axis.setTickMarkPaint(lineColor);// Y坐标轴标记|竖线颜色
    // 隐藏Y刻度
    axis.setAxisLineVisible(false);
    axis.setTickMarksVisible(false);
    // Y轴网格线条
    plot.setRangeGridlinePaint(new Color(192, 192, 192));
    plot.setRangeGridlineStroke(new BasicStroke(1));

    plot.getRangeAxis().setUpperMargin(0.1);// 设置顶部Y坐标轴间距,防止数据无法显示
    plot.getRangeAxis().setLowerMargin(0.1);// 设置底部Y坐标轴间距

  }
}
