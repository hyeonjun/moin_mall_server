package com.buying.back;

import com.buying.back.util.response.CommonResponse;
import com.buying.back.util.response.CommonResponseCode;
import java.util.Optional;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

  private final String profile;

  public DefaultController(Environment environment) {
    this.profile = Optional
      .ofNullable(environment.getActiveProfiles()[0])
      .orElse("default");
  }

  @GetMapping("/profile")
  public CommonResponse<String> profile() {
    return new CommonResponse<>(profile, CommonResponseCode.SUCCESS);
  }
}
