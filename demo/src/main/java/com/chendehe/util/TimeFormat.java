package com.chendehe.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class TimeFormat {

  private TimeFormat() {
  }

  public static final String DEFAULT_TYPE = "yyyy-MM-dd";
  public static final String TYPE_01 = "yyyy-MM-dd HH:mm:ss";

  /**
   * 获取当前时间字符串（日期＋时间）.
   * 返回时间格式是：yyyy-MM-dd HH:mm:ss.
   *
   * @return String
   */
  public static String getCurrentDate() {
    return getDate(new Date());
  }

  /**
   * 获取当前时间指定格式字符串（日期＋时间）.
   * 返回时间格式是：yyyy-MM-dd HH:mm:ss.
   *
   * @return String
   */
  public static String getCurrentDate(String format) {
    return getDate(new Date(), format);
  }

  /**
   * 获取指定时间字符串（日期＋时间）.
   * 返回时间格式是：yyyy-MM-dd HH:mm:ss.
   *
   * @return String
   */
  public static String getDate(Date date) {
    return new SimpleDateFormat(DEFAULT_TYPE).format(date);
  }

  /**
   * 获取指定时间和格式字符串.
   *
   * @return String
   */
  public static String getDate(Date date, String format) {
    return new SimpleDateFormat(format).format(date);
  }

  public static void main(String[] args) {
    System.out.println(getCurrentDate());
  }
}
