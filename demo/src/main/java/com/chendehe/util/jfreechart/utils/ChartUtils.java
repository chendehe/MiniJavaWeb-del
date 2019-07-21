package com.chendehe.util.jfreechart.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

public final class ChartUtils {

  private ChartUtils() {
  }

  // 保存为文件
  public static void saveAsFile(JFreeChart chart, String outputPath) {
    File outFile = new File(outputPath);
    if (!outFile.getParentFile().exists() && !outFile.getParentFile().mkdirs()) {
      throw new RuntimeException("创建目录失败！");
    }
    try (FileOutputStream out = new FileOutputStream(outputPath)) {
      // 保存为JPEG
      ChartUtilities.writeChartAsJPEG(out, chart, 600, 400);
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // 返回字节流
  public static byte[] convertToByte(JFreeChart chart) {
    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      // 保存为JPEG
      ChartUtilities.writeChartAsJPEG(out, chart, 600, 400);
      out.flush();
      return out.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
