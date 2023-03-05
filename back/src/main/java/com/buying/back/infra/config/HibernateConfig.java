package com.buying.back.infra.config;

import com.buying.back.util.querycounter.ApiQueryCountInspector;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HibernateConfig {

  private final ApiQueryCountInspector apiQueryCountInspector;

  // StatementInspector 구현체가 직접 만든 빈을 주입받고 있기 때문에
  // Properties 파일ㅇ르 사용하여 설정할 수 없다. 그래서 설정을 커스텀해주어야 한다.
  @Bean
  public HibernatePropertiesCustomizer hibernatePropertiesCustomizer() {
    return hibernateProperties ->
      hibernateProperties.put(AvailableSettings.STATEMENT_INSPECTOR, apiQueryCountInspector);
  }

}
