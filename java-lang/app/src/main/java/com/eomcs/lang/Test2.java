package com.eomcs.lang;

import java.util.Calendar;

public class Test2 {
  public static void main(String[] args) {
    int year = 2024;
    int month = 9; // 7월 (Calendar 클래스는 0부터 시작하므로 7은 8월)

    printCalendar(year, month);
  }

  public static void printCalendar(int year, int month) {
    // Calendar 인스턴스를 생성하고 연도와 월을 설정
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month - 1); // Calendar.MONTH는 0부터 시작

    // 월의 첫날로 설정
    calendar.set(Calendar.DAY_OF_MONTH, 1);

    // 해당 월의 첫 번째 요일
    int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

    // 해당 월의 마지막 날
    int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

    // 달력 출력
    System.out.println(" " + year + "년 " + month + "월");
    System.out.println("일 월 화 수 목 금 토");

    // 첫 번째 줄의 빈칸
    for (int i = Calendar.SUNDAY; i < firstDayOfWeek; i++) {
      System.out.print("   ");
    }

    // 날짜 출력
    for (int day = 1; day <= lastDay; day++) {
      System.out.printf("%2d ", day);
      if ((day + firstDayOfWeek - 1) % 7 == 0) { // 토요일이면 줄 바꿈
        System.out.println();
      }
    }
    System.out.println();
  }
}
