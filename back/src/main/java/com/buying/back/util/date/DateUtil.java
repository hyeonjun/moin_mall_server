package com.buying.back.util.date;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {

  public static long getDateDiff(LocalDate from, LocalDate to) {
    return ChronoUnit.DAYS.between(from, to);
  }
}
