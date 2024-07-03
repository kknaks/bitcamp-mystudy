package study.oop.clazz;

import java.util.Calendar;

public class Test {
  public static void main(String[] args) {
    Calendar calendar = Calendar.getInstance();
    java.util.Date currentDate = calendar.getTime();
    System.out.println(currentDate);
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    calendar.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY - dayOfWeek);
    System.out.println(calendar);
    for (int i = 0; i < 7; i++) {
      System.out
          .println(calendar.get(Calendar.MONTH) + 1 + " " + calendar.get(Calendar.DAY_OF_MONTH));
      calendar.add(Calendar.DAY_OF_MONTH, 1);

    }
    // System.out.println(calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.DAY_OF_MONTH));
    // calendar.add(Calendar.DAY_OF_MONTH, 1);
    // System.out.println(calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.DAY_OF_MONTH));
  }
}


// java.util.GregorianCalendar[
// time=1719910166542,
// areFieldsSet=true,
// areAllFieldsSet=true,
// lenient=true,
// zone=sun.util.calendar.ZoneInfo[id="Asia/Seoul",offset=32400000,dstSavings=0,useDaylight=false,transitions=30,lastRule=null],
// firstDayOfWeek=1,
// minimalDaysInFirstWeek=1,
// ERA=1,
// YEAR=2024,
// MONTH=6,
// WEEK_OF_YEAR=27,
// WEEK_OF_MONTH=1,
// DAY_OF_MONTH=2,
// DAY_OF_YEAR=184,
// DAY_OF_WEEK=3,
// DAY_OF_WEEK_IN_MONTH=1,
// AM_PM=1,
// HOUR=5,
// HOUR_OF_DAY=17,
// MINUTE=49,
// SECOND=26,
// MILLISECOND=542,
// ZONE_OFFSET=32400000,
// DST_OFFSET=0]
