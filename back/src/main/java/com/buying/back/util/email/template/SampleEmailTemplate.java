package com.buying.back.util.email.template;

import com.buying.back.util.email.HtmlEmailType;

public class SampleEmailTemplate extends HtmlEmailTemplate{

  public SampleEmailTemplate(String toAddress) {
    super(HtmlEmailType.SAMPLE);
    this.addTo(toAddress);
    this.addParam("sample", "sample message");
  }
}
