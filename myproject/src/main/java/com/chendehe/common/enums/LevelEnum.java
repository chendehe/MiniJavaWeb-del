package com.chendehe.common.enums;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;

public enum LevelEnum {

  HIGH(1),
  MIDDLE(2),
  LOW(3);

  private int value;

  LevelEnum(int value) {
    this.value = value;
  }

  private static Map<Integer, LevelEnum> map = Maps.newHashMap();

  static {
    final ImmutableMap.Builder<Integer, LevelEnum> builder = ImmutableMap.builder();
    for (LevelEnum en : LevelEnum.values()) {
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