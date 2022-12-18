package com.buying.back.util.html;

import com.buying.back.util.html.template.HtmlTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class HtmlBuilder {

  private final TemplateEngine templateEngine;

  public String buildHtml(HtmlTemplate template) {
    Context context = new Context(null);
    template.getParams().forEach(context::setVariable);
    return templateEngine.process(template.getHtmlType().getTemplatePath(), context);
  }
}
