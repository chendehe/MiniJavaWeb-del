package com.chendehe.util.jfreechart.entity;

import java.util.List;

public class PieChartEntity extends ChartBaseEntity {

  private List<Integer> values;

  public List<Integer> getValues() {
    return values;
  }

  public void setValues(List<Integer> values) {
    this.values = values;
  }
}
