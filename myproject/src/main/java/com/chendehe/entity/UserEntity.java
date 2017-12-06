package com.chendehe.entity;

import java.io.Serializable;
import java.util.Date;

public class UserEntity implements Serializable {

  private static final long serialVersionUID = -2535178899662614143L;
  private String id;
  private String name;
  private int sex;
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

  public int getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = sex;
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
    return "UserEntity{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", sex=" + sex +
        ", birthday=" + birthday +
        ", address='" + address + '\'' +
        ", createTime=" + createTime +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserEntity that = (UserEntity) o;

    if (sex != that.sex) {
      return false;
    }
    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (name != null ? !name.equals(that.name) : that.name != null) {
      return false;
    }
    if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) {
      return false;
    }
    if (address != null ? !address.equals(that.address) : that.address != null) {
      return false;
    }
    return createTime != null ? createTime.equals(that.createTime) : that.createTime == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + sex;
    result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
    return result;
  }

}
