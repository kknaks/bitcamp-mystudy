package study.oop.lambda;

public class Test03 {

  interface Calculator {
    int plus(int a, int b);
  }

  static void test(Calculator c) {
    System.out.println(c.plus(100, 200));
  }

  public static void main(String[] args) {
    class Calc implements Calculator {
      @Override
      public int plus(int a, int b) {
        return a + b;
      }
    }
    Calculator c1 = new Calc();
    test(c1);

    Calculator c2 = new Calculator() {
      @Override
      public int plus(int a, int b) {
        return a + b;
      }
    };

    test(c2);

    test(new Calculator() {
      @Override
      public int plus(int a, int b) {
        return a + b;
      }
    });

    Calculator c4 = (int a, int b) -> {
      return a + b;
    };
    test(c4);

    Calculator c5 = (a, b) -> a + b;
    test(c5);

    test((a, b) -> {
      return a + b;
    });


  }
}
