package study.oop.clazz;

import study.oop.clazz.vo.Score;

public class Test02 {
  public static void main(String[] args) {

    Score s = new Score();

    Score s1 = new Score("홍길동", 100, 90, 85);
    printScore(s1);


    Score s2 = new Score("임꺽정", 90, 80, 75);
    printScore(s2);

    Score s3 = new Score("유관순", 80, 70, 65);
    printScore(s3);
  }

  static void printScore(Score s) {
    System.out.printf("%s: %d, %d, %d, %d, %.1f\n", s.getName(), s.getKor(), s.getEng(),
        s.getMath(), s.getSum(), s.getAver());
  }


}
