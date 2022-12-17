package com.buying.back.application.common.vo;

public class InvalidInputVO {

  private String field;
  private Object value;
  private String details;
  private String code;
  private String[] arguments;

  public static InvalidInputVO valueOf(String field, Object value, String details) {
    return new InvalidInputVO(field, value, details, "default", null);
  }

  public static InvalidInputVO valueOf(String field, Object value, String details, String code,
    String[] arguments) {
    return new InvalidInputVO(field, value, details, code, arguments);
  }

  public InvalidInputVO(String field, Object value, String details, String code,
    String[] arguments) {
    this.field = field;
    this.value = value;
    this.details = details;
    this.code = code;
    this.arguments = arguments;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String[] getArguments() {
    return arguments;
  }

  public void setArguments(String[] arguments) {
    this.arguments = arguments;
  }
}
