package com.chendehe.common.enums;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

/**
 * 性别 1-男，2-女.
 */
public enum GenderEnum {

  MALE(1),
  FEMALE(2);

  private int value;

  GenderEnum(int value) {
    this.value = value;
  }

  private static Map<Integer, GenderEnum> map;

  static {
    final ImmutableMap.Builder<Integer, GenderEnum> builder = ImmutableMap.builder();
    for (GenderEnum en : GenderEnum.values()) {
      builder.put(en.value, en);
    }
    map = builder.build();
  }

  public int getValue() {
    return value;
  }

  public static Enum forValue(Integer value) {
    return map.get(value);
  }

}