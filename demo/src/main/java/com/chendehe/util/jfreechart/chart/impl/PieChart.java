package com.chendehe.util.jfreechart.chart.impl;

import com.chendehe.util.jfreechart.entity.PieChartEntity;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;

public class PieChart extends PieChartAbstract {

  public JFreeChart createPieChart(PieChartEntity entity) {

    String title = entity.getTitle();
    DefaultPieDataset dataset = createDataSet(entity);
    JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, false, false);

    createChart(chart);

    LegendTitle legend = chart.getLegend();
    createLegend(legend);
    // 标注位于右侧
    legend.setPosition(RectangleEdge.BOTTOM);

    PiePlot piePlot = (PiePlot) chart.getPlot();
    createPiePlot(piePlot);

    return chart;
  }

}
