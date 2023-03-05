package com.buying.back.infra.interceptor;

import com.buying.back.util.querycounter.ApiQueryCounter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class QueryCountLoggingInterceptor implements HandlerInterceptor {

  private static final String QUERY_COUNT_LOG_FORMAT = "STATUS_CODE: {}, METHOD: {}, URL: {}, QUERY_COUNT: {}";
  private static final String QUERY_COUNT_WARNING_LOG_FORMAT = "Query Executed more than {} times";
  private static final int QUERY_COUNT_WARNING_THRESHOLD = 10;

  private final ApiQueryCounter apiQueryCounter;

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
    Object handler, Exception ex) throws Exception {
    int queryCount = apiQueryCounter.getCount();
    log.info(QUERY_COUNT_LOG_FORMAT, response.getStatus(), request.getMethod(),
      request.getRequestURI(), queryCount);
    if (queryCount >= QUERY_COUNT_WARNING_THRESHOLD) {
      log.warn(QUERY_COUNT_WARNING_LOG_FORMAT, QUERY_COUNT_WARNING_THRESHOLD);
    }
  }
}
