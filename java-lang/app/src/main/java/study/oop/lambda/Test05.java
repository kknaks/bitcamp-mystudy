package study.oop.lambda;

public class Test05 {
  class MyCalculator {
    public static int plus(int a, int b) {
      return a + b;
    }

    public static int minus(int a, int b) {
      return a - b;
    }

    public static int multiple(int a, int b) {
      return a * b;
    }

    public static int divide(int a, int b) {
      return a / b;
    }
  }

  interface Calculator {
    int compute(int x, int y);
  }

  public static void main(String[] args) {
    class CalPlus implements Calculator {
      @Override
      public int compute(int x, int y) {
        return MyCalculator.plus(x, y);
      }
    }
    Calculator obj = new CalPlus();
    System.out.println(obj.compute(100, 200));
    // //2
    // Calculator obj1 = new Calculator() {
    // @Override
    // public int compute(int x, int y) {
    // return MyCalculator.plus(x, y);
    // }
    // };
    // int result1 = obj1.compute(100, 200);
    // System.out.println(result1);
    //
    // //3
    // Calculator obj2 = (int x, int y) -> {
    // return MyCalculator.plus(x, y);
    // };
    //
    // //4
    // int result2 = obj2.compute(100, 200);
    // System.out.println(result2);
    //
    // //5
    // Calculator obj3 = (x, y) -> MyCalculator.plus(x, y);
    // int result3 = obj3.compute(100, 200);
    // System.out.println(result3);

    // 6
    Calculator obj4 = MyCalculator::plus;
    System.out.println(obj4.compute(100, 200));
  }
}

