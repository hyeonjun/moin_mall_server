package com.buying.back.util.string;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@NoArgsConstructor
public class RandomString {

  public static String generateRandomPassword() {
    return RandomStringUtils.randomAlphanumeric(8);
  }

}
