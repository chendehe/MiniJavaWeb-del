package com.chendehe.vo;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserVo {

  private String id;
  private String name;
  private Integer gender;
  private Date birthday;
  private String address;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
