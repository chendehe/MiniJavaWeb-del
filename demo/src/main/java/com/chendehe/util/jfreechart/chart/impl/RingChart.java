package com.chendehe.util.jfreechart.chart.impl;

import com.chendehe.util.jfreechart.entity.PieChartEntity;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;

public class RingChart extends PieChartAbstract {

  public JFreeChart createRingChart(PieChartEntity entity) {

    String title = entity.getTitle();
    DefaultPieDataset dataset = createDataSet(entity);
    JFreeChart chart = ChartFactory.createRingChart(title, dataset, true, false, false);

    createChart(chart);

    LegendTitle legend = chart.getLegend();
    createLegend(legend);

    RingPlot ringPlot = (RingPlot) chart.getPlot();
    createPiePlot(ringPlot);
    ringPlot.setSectionDepth(0.58);
    ringPlot.setSeparatorPaint(Color.WHITE);

    return chart;
  }

}
