package com.buying.back.infra.config;

import com.buying.back.util.email.provider.ConsoleLogEmailProvider;
import com.buying.back.util.email.provider.DefaultEmailProvider;
import com.buying.back.util.email.provider.EmailProvider;
import com.buying.back.util.html.HtmlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EmailConfig {

  @Primary
  @Bean
  public EmailProvider emailProvider(@Autowired EmailProvider emailProvider) {
    return new DefaultEmailProvider(emailProvider);
  }

  @Bean
  @ConditionalOnProperty(name = EmailProvider.PROPERTY_KEY, havingValue = EmailProvider.CONSOLE_LOG, matchIfMissing = true)
  public EmailProvider consoleLogEmailProvider(@Autowired HtmlBuilder htmlBuilder) {
    return new ConsoleLogEmailProvider(htmlBuilder);
  }

}
