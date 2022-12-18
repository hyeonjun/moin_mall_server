package com.buying.back.util.email;

import com.buying.back.util.html.HtmlType;

public enum HtmlEmailType implements HtmlType {

  SAMPLE("email/sample", "Sample"),
  RESET_PASSWORD("email/ResetPassword", "Reset Password");

  private final String templatePath;
  private final String subject;

  HtmlEmailType(String templatePath, String subject) {
    this.templatePath = templatePath;
    this.subject = subject;
  }

  @Override
  public String getTemplatePath() {
    return templatePath;
  }

  @Override
  public String getSubject() {
    return subject;
  }
}
