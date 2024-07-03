package study.oop.clazz;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestDate {
  public static void main(String[] args) {
    String input = "2024-07-31";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDate date = LocalDate.parse(input, formatter);

    // 입력한 날짜의 주의 시작 (월요일)와 끝 (일요일) 계산
    LocalDate startOfWeek = date.with(DayOfWeek.MONDAY);
    LocalDate endOfWeek = date.with(DayOfWeek.SUNDAY);

    System.out.println("입력한 날짜: " + date);
    System.out.println("해당 주의 날짜들:");

    // 주의 시작부터 끝까지 출력
    LocalDate current = startOfWeek;

    for()
    while (!current.isAfter(endOfWeek)) {
      System.out.println(current);
      current = current.plusDays(1);
    }
  }
}
