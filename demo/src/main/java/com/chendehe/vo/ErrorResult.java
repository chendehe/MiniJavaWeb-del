package com.chendehe.vo;

public class ErrorResult {

  private String errorMessage;
  private String errorDetail;

  public ErrorResult(String errorMessage, String errorDetail) {
    this.errorMessage = errorMessage;
    this.errorDetail = errorDetail;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorDetail() {
    return errorDetail;
  }

  public void setErrorDetail(String errorDetail) {
    this.errorDetail = errorDetail;
  }
}
