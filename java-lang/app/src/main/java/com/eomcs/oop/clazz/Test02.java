package study.oop.clazz;

import study.oop.clazz.domain.Score3;

public class Test02 {
  static Score3[] scores = new Score3[] {new Score3("홍길동", 100, 90, 85),
      new Score3("임꺽정", 90, 80, 75), new Score3("유관순", 80, 70, 65)};

  public static void main(String[] args) {
    printScore();
  }

  static void printScore() {
    for (Score3 score : scores) {
      System.out.printf("%s: %d, %d, %d, %d, %.1f\n", score.getName(), score.getKor(),
          score.getEng(), score.getMath(), score.getSum(), score.getAver());
    }
  }
}

