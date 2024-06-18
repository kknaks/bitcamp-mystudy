package study.oop.clazz;

import study.oop.clazz.util.Calculator;

public class Test01 {
  public static void main(String[] args) {

    Calculator c1 = new Calculator();

    c1.plus(2);
    c1.plus(3);
    c1.minus(1);
    c1.multiple(7);
    c1.divide(3);

    System.out.printf("result = %d\n", c1.getResult());
    c1.clear();
    c1.plus(2);
    c1.plus(3);
    System.out.printf("result = %d\n", c1.getResult());
  }

}

// 연습 : 클래스 문법을 매서드 분류하는 용도로 사용
// 1) 메서드 분류 0
// 2) static 필드사용 0
// 3) non-static 필드사용 0
// 4) 인스턴스 메서드 사용 0
// 5) private, getter 사용
// 6) 인스턴스 메서드 사용 : clear()
