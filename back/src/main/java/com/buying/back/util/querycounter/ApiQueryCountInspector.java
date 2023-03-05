package com.buying.back.util.querycounter;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

// Hibernate 에서 쿼리문을 가로챌 수 있는 StatementInspector 라는 인터페이스를 제공함
// 메소드로 inspect 라는 것이 존재하는데, HibernateProperty로 StatementInspector 구현체를 등록하면
// 쿼리 실행 시점에 실행된 쿼리문을 인자로 받은 inspect 메소드가 실행된다.
// 이 구현체를 사용하여 쿼리 개수를 세는 데에 활용
// 쿼리 개수는 Http Request 별로 다르게 저장해야 하는데, 각 요청별로 실행되는 쿼리 개수를 셀 것이디 때문에.
// 하지만 StatementInspector는 HibernateProperty로 하나만 등록 가능하다.
// 그래서 StatementInspector 자체의 생성 주기를 request로 유지할 수 없다.
// 대신 쿼리 개수를 저장하기 위한 request 스코프의 빈을 만들주어야 하기 때문에 ApiQueryCounter 를 만드는 것
@Component
@RequiredArgsConstructor
public class ApiQueryCountInspector implements StatementInspector {

  private final ApiQueryCounter apiQueryCounter;

  @Override
  public String inspect(String sql) {
    if (isHttpRequestScope()) {
      apiQueryCounter.queryCounting();
    }
    return sql;
  }

  // ApiQueryCounter의 빈 생명주기는 Http Request 스코프 안이지만,
  // ApiQueryCountInspector는 아니기 때문에 그 밖에서도 작동될 수 있다.
  // 그래서 현재 시점이 Http Request Scope 인지 체크해주어야 한다.
  private boolean isHttpRequestScope() {
    //RequestContextHolder.getRequestAttributes()는 현재 스레드의 RequestAttributes를 반환한다.
    // HttpRequest 스코프 안이라면 null이 아닐 것이기 때문에 이를 확인해준다.
    return Objects.nonNull(RequestContextHolder.getRequestAttributes());
  }
}
