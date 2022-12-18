package com.buying.back.util.email.provider;

import com.buying.back.util.email.template.HtmlEmailTemplate;
import com.buying.back.util.html.HtmlBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ConsoleLogEmailProvider implements EmailProvider{

  private final HtmlBuilder htmlBuilder;

  @Override
  public void send(HtmlEmailTemplate template) {
    String content = htmlBuilder.buildHtml(template);
    log.info("Email: {}", template);
    log.info("Content: {}", content);
  }
}
