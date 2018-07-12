package com.chendehe.util.jfreechart.entity;

import java.util.List;

public class ChartBaseEntity {

  // 标注类别
  private List<String> categories;
  // 顶部描述
  private String title;

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
