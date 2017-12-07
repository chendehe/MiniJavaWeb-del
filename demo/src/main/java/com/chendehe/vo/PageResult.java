package com.chendehe.vo;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PageResult<T> extends Page {

  // 总数
  private Integer totalNum;

  private List<T> list;

  public Integer getTotalNum() {
    return totalNum;
  }

  public void setTotalNum(Integer totalNum) {
    this.totalNum = totalNum;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
