package com.buying.back.util.querycounter;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope // 스프링에서 제공하는 어노테이션으로, HTTP Request 생명주기를 가지는 빈을 만들어서 사용
@Getter
public class ApiQueryCounter {

  private int count;

  public void queryCounting() {
    count++;
  }

}
