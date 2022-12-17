package com.buying.back;

import java.util.Optional;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<String> profile() {
    return ResponseEntity.ok(profile);
  }
}
