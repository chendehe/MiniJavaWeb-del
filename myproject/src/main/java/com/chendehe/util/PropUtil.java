package com.chendehe.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

public final class PropUtil {

  @Autowired
  private static Environment env;

  private PropUtil() {
  }

  public static String test(String key) {
    return env.getProperty(key);
  }

}
