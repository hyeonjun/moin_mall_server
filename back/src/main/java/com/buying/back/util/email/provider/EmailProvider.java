package com.buying.back.util.email.provider;

import com.buying.back.util.email.template.HtmlEmailTemplate;

public interface EmailProvider {

  String PROPERTY_KEY = "byebuying.email.provider";
  String CONSOLE_LOG = "console-log";
  String AWS = "aws";

  void send(HtmlEmailTemplate template);
}
