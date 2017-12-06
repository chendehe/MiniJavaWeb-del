package com.chendehe.util;

import com.chendehe.common.MyConstant;
import com.chendehe.config.TemplateConfig;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.springframework.util.ResourceUtils;

/**
 * 读取配置文件，或者使用 TemplateConfig
 *
 * @see TemplateConfig
 */
public final class PropUtil {

  private static final Properties prop = new Properties();

  private PropUtil() {
  }

  static {
    loadProp();
  }

  private static void loadProp() {
    try {
      prop.load(new FileReader(ResourceUtils.getFile(MyConstant.TEST_PROP_URL)));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String getValue(String key) {
    return prop.getProperty(key);
  }

}
