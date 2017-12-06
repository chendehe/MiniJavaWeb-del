package com.chendehe.entity;

import com.google.common.base.Objects;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserEntity implements Serializable {

  private static final long serialVersionUID = -2535178899662614143L;
  private String id;
  private String name;
  private int gender;
  private Date birthday;
  private String address;
  private Date createTime;

  public Date getCreateTime() {

    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

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

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
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
    // java-Objects.toString(),guava-MoreObjects.toStringHelper()
    return ToStringBuilder.reflectionToString(this);
  }

  @Override
  public boolean equals(Object o) {
    // Apache Commons Lang3-EqualsBuilder
    return Objects.equal(this, o);
  }

  @Override
  public int hashCode() {
    // Apache Commons Lang3-HashCodeBuilder
    return Objects.hashCode(this);
  }

}
