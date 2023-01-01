package com.buying.back.util.email.template;

import com.buying.back.util.email.HtmlEmailType;

public class AccountEmailTemplate extends HtmlEmailTemplate {

  public AccountEmailTemplate(HtmlEmailType htmlEmailType,
    String addSubject,
    String email, String password) {
    super(htmlEmailType);
    this.addTo(email);
    this.setSubject(addSubject);
    this.addParam("email", email);
    this.addParam("password", password);
  }
}
