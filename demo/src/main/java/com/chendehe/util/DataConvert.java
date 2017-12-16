package com.chendehe.util;

import java.io.UnsupportedEncodingException;

public final class DataConvert {

  private DataConvert() {
  }

  /**
   * 字节数组转16进制字符串.
   *
   * @param bytes byte[]
   * @return 16进制字符串
   */
  public static String bytes2HexString(byte[] bytes) {
    StringBuilder builder = new StringBuilder();

    for (byte b : bytes) {
      String hex = Integer.toHexString(b & 0xFF);
      if (hex.length() == 1) {
        hex = '0' + hex;
      }
      builder.append(hex.toUpperCase());
    }

    return builder.toString();
  }

  /**
   * 十六进制转字节.
   *
   * @param hex 十六进制字符串
   * @return 字节数组
   */
  public static byte[] hexString2Bytes(String hex) {

    if ((null == hex) || ("".equals(hex))) {
      return null;
    } else if (hex.length() % 2 != 0) {
      return null;
    } else {
      hex = hex.toUpperCase();
      int len = hex.length() / 2;
      byte[] b = new byte[len];
      char[] hc = hex.toCharArray();
      for (int i = 0; i < len; i++) {
        int p = 2 * i;
        b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p + 1]));
      }
      return b;
    }
  }

  /*
   * 字符转换为字节
   */
  private static byte charToByte(char c) {
    return (byte) "0123456789ABCDEF".indexOf(c);
  }

  /**
   * 字符串转字节数组.
   *
   * @param s 字符串
   * @return 字节
   */
  public static byte[] string2Bytes(String s) {
    return s.getBytes();
  }

  /**
   * 16进制字符串转字符串.
   *
   * @param hex 16进制字符串
   * @return 字符串
   * @throws Exception 异常
   */
  public static String hex2String(String hex) throws Exception {
    return bytes2String(hexString2Bytes(hex));
  }

  /**
   * 字符串转16进制字符串.
   *
   * @param s 字符串
   * @return 16进制字符串
   * @throws Exception 异常
   */
  public static String string2HexString(String s) throws Exception {
    return bytes2HexString(string2Bytes(s));
  }

  /**
   * 字节数组转字符串.
   *
   * @param b 字节
   * @return 字节字符串
   * @throws UnsupportedEncodingException 异常
   */
  public static String bytes2String(byte[] b) throws UnsupportedEncodingException {
    return new String(b, "UTF-8");
  }

}
