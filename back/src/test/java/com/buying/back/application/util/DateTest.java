package com.buying.back.application.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;

public class DateTest {

  @Test
  void dateDiffDay() {
    LocalDate a = LocalDate.now();
    System.out.println(a);
    LocalDate b = a.plusDays(720);
    System.out.println(b);
    long day = ChronoUnit.DAYS.between(a, b);
    System.out.println(day);
  }

}
