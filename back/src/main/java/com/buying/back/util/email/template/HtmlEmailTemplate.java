package com.buying.back.util.email.template;

import com.buying.back.util.email.HtmlEmailType;
import com.buying.back.util.html.HtmlType;
import com.buying.back.util.html.template.HtmlTemplate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public abstract class HtmlEmailTemplate implements HtmlTemplate {

  protected static final String DEFAULT_EMAIL_SENDER = "manage@byebuying.com";

  private final String fromAddress;
  private final List<String> toAddressList = new ArrayList<>();

  private final HtmlEmailType htmlEmailType;
  private final Map<String, Object> params = new HashMap<>();

  protected HtmlEmailTemplate(HtmlEmailType htmlEmailType) {
    this.fromAddress = DEFAULT_EMAIL_SENDER;
    this.htmlEmailType = htmlEmailType;
  }

  protected HtmlEmailTemplate(HtmlEmailType htmlEmailType, String fromAddress) {
    this.fromAddress = fromAddress;
    this.htmlEmailType = htmlEmailType;
  }

  @Override
  public Map<String, Object> getParams() {
    return this.params;
  }

  @Override
  public HtmlType getHtmlType() {
    return this.htmlEmailType;
  }

  public void addParam(String key, Object value) {
    this.params.put(key, value);
  }

  public void addTo(String to) {
    toAddressList.add(to);
  }

}
