package com.chendehe.util.jfreechart.entity;

import java.util.List;
import java.util.Map;

public class XYChartEntity extends ChartBaseEntity {

  // 柱子名称：柱子所有的值集合
  private Map<String, List<Double>> map;
  // 底部描述
  private String categoryLabel;
  // 侧边描述
  private String valueLabel;

  public Map<String, List<Double>> getMap() {
    return map;
  }

  public void setMap(Map<String, List<Double>> map) {
    this.map = map;
  }

  public String getCategoryLabel() {
    return categoryLabel;
  }

  public void setCategoryLabel(String categoryLabel) {
    this.categoryLabel = categoryLabel;
  }

  public String getValueLabel() {
    return valueLabel;
  }

  public void setValueLabel(String valueLabel) {
    this.valueLabel = valueLabel;
  }
}
