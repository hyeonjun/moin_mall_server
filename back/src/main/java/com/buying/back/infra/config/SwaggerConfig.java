package com.buying.back.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() { // swagger Failed to fetch 오류
    return new OpenAPI()     // 서버는 https 인데, swagger 에서는 http 로 요청을 보내어 발생
      .addServersItem(new Server().url("/")); // servers url 설정으로 "/"를 주면 현재 url 로 요청됨
  }

  @Bean
  OpenApiCustomiser springSecurityLoginEndPointCustomiser() {
    return openApi -> {
      Operation operation = new Operation();

      Schema<?> schema = new ObjectSchema();
      schema.addProperty("email", new StringSchema());
      schema.addProperty("password", new StringSchema());
      RequestBody requestBody = new RequestBody()
        .content(new Content()
          .addMediaType("application/x-www-form-urlencoded", new MediaType().schema(schema)));
      operation.requestBody(requestBody);

      ApiResponses apiResponses = new ApiResponses();
      apiResponses.addApiResponse(String.valueOf(HttpStatus.OK.value()),
        new ApiResponse().description(HttpStatus.OK.getReasonPhrase()));
      apiResponses.addApiResponse(String.valueOf(HttpStatus.FORBIDDEN.value()),
        new ApiResponse().description(HttpStatus.FORBIDDEN.getReasonPhrase()));
      operation.responses(apiResponses);
      operation.addTagsItem("security login");
      PathItem pathItem = new PathItem().post(operation);
      openApi.getPaths().addPathItem("/api/login", pathItem);
    };
  }
}
