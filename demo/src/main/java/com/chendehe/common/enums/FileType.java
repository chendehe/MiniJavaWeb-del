package com.chendehe.common.enums;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;

/**
 * 性别 1-男，2-女.
 */
public enum FileType {

  JPG("FFD8FF", "jpg"),
  PNG("89504E47", "png"),
  GIF("47494638", "gif"),
  TIF("49492A00", "tif"),
  BMP("424D", "bmp"),
  DWG("41433130", "dwg"), // CAD
  PSD("38425053", "psd"),
  RTF("7B5C727466", "rtf"), // 日记本
  XML("3C3F786D6C", "xml"),
  HTML("68746D6C3E", "html"),
  EML("44656C69766572792D646174653A", "eml"), // 邮件
  DOC("D0CF11E0", "doc"),
  XLS("D0CF11E0", "xls"),//excel2003版本文件
  MDB("5374616E64617264204A", "mdb"),
  PS("252150532D41646F6265", "ps"),
  PDF("255044462D312E", "pdf"),
  DOCX("504B0304", "docx"),
  XLSX("504B0304", "xlsx"),//excel2007以上版本文件
  RAR("52617221", "rar"),
  WAV("57415645", "wav"),
  AVI("41564920", "avi"),
  RM("2E524D46", "rm"),
  MPG("000001BA", "mpg"),
  MOV("6D6F6F76", "mov"),
  ASF("3026B2758E66CF11", "asf"),
  MID("4D546864", "mid"),
  GZ("1F8B08", "gz");

  private String code;
  private String type;

  FileType(String code, String type) {
    this.code = code;
    this.type = type;
  }

  private static Map<String, String> map;

  static {
    final ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
    for (FileType en : FileType.values()) {
      builder.put(en.type, en.code);
    }
    map = builder.build();
  }

  public static String forCode(String type) {
    return map.get(type);
  }

}