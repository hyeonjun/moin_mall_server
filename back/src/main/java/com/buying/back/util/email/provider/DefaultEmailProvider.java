package com.buying.back.util.email.provider;

import com.buying.back.util.email.template.HtmlEmailTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

@RequiredArgsConstructor
public class DefaultEmailProvider implements EmailProvider {

  private final EmailProvider emailProvider;

  @Async
  @Override
  public void send(HtmlEmailTemplate template) {
    emailProvider.send(template);
  }

}

