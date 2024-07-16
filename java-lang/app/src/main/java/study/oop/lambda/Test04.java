package study.oop.lambda;

public class Test04 {
  interface InterestCalculator {
    double compute(int money);
  }

  // 1)
  static InterestCalculator create1(double rate) {
    class C implements InterestCalculator {
      private double rate;

      public C(double rate) {
        this.rate = rate;
      }

      @Override
      public double compute(int money) {
        return money * (1 + rate);
      }
    }
    return new C(rate);
  }

  // 2)
  static InterestCalculator create2(double rate) {
    class C2 implements InterestCalculator {
      @Override
      public double compute(int money) {
        return money * (1 + rate);
      }
    }
    return new C2();
  }

  // 3)
  static InterestCalculator create3(double rate) {
    InterestCalculator c3 = new InterestCalculator() {
      @Override
      public double compute(int money) {
        return money * (1 + rate);
      }
    };
    return c3;
  }

  // 4)
  static InterestCalculator create4(double rate) {
    return new InterestCalculator() {
      @Override
      public double compute(int money) {
        return money * (1 + rate);
      }
    };
  }

  // 5)
  static InterestCalculator create5(double rate) {
    return (int money) -> {
      return money * (1 + rate);
    };
  }

  // 6)
  static InterestCalculator create6(double rate) {
    return money -> money * (1 + rate);
  }

  public static void main(String[] args) {
    InterestCalculator c1 = create1(0.035);
    System.out.println(c1.compute(10000000));
    System.out.println(create2(0.035).compute(10000000));
    System.out.println(create3(0.035).compute(10000000));
    System.out.println(create4(0.035).compute(10000000));
    System.out.println(create5(0.035).compute(10000000));
    System.out.println(create6(0.035).compute(10000000));

  }
}
