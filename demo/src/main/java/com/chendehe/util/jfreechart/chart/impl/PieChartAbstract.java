package com.chendehe.util.jfreechart.chart.impl;

import com.chendehe.util.jfreechart.chart.Chart;
import com.chendehe.util.jfreechart.entity.PieChartEntity;
import java.awt.Color;
import java.awt.Rectangle;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

abstract class PieChartAbstract implements Chart {

  DefaultPieDataset createDataSet(PieChartEntity entity) {
    DefaultPieDataset dataset = new DefaultPieDataset();
    for (int i = 0; i < entity.getValues().size(); i++) {
      dataset.setValue(entity.getCategories().get(i), entity.getValues().get(i));
    }
    return dataset;
  }

  void createChart(JFreeChart chart) {
    // 必须设置文本抗锯齿
    chart.setTextAntiAlias(false);
  }

  void createLegend(LegendTitle legend) {
    // 设置标注无边框
    legend.setFrame(new BlockBorder(Color.WHITE));
    // 标注位于右侧
    legend.setPosition(RectangleEdge.BOTTOM);
  }

  void createPiePlot(PiePlot piePlot) {
    piePlot.setNoDataMessage(NO_DATA_MSG);
    piePlot.setInsets(new RectangleInsets(0, 0, 0, 0));
    piePlot.setCircular(true);// 圆形
    piePlot.setBaseSectionOutlinePaint(Color.WHITE);

    piePlot.setSimpleLabels(false);// 简单标签
    piePlot.setLabelGap(0.01);
    piePlot.setInteriorGap(0.05D);
    piePlot.setLegendItemShape(new Rectangle(10, 10));// 图例形状
    piePlot.setIgnoreNullValues(true);
    piePlot.setLabelBackgroundPaint(null);// 去掉背景色
    piePlot.setLabelShadowPaint(null);// 去掉阴影
    piePlot.setLabelOutlinePaint(null);// 去掉边框
    piePlot.setShadowPaint(null);
    // 0:category 1:value:2 :percentage
    piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{2}"));// 显示标签数据
  }
}
