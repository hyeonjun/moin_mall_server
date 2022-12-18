package com.buying.back.util.html.template;

import com.buying.back.util.html.HtmlType;
import java.util.Map;

public interface HtmlTemplate {

  Map<String, Object> getParams();

  HtmlType getHtmlType();
}
