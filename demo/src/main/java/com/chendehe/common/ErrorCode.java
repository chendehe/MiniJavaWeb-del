package com.chendehe.common;

public final class ErrorCode {

  private ErrorCode() {
  }

  public static final String SYSTEM_ERROR = "system.error";
  public static final String PARAM_EMPTY = "param.empty";
  public static final String PARAM_TYPE_ERROR = "param.type.empty";

  public static final String EXCEL_PARSE_ERROR = "excel.parse.error";
  public static final String EXCEL_DOWNLOAD_FAILED = "excel.download.failed";
  public static final String BARRIER_ERROR = "barrier.error";
  public static final String BARRIER_TIMEOUT = "barrier.timeout";
}
