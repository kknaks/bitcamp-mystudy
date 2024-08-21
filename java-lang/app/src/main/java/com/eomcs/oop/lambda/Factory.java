package study.oop.lambda;

interface InterestCalculator {
  double compute(int money);
}


public class Factory {
  // 1.일반 클래스1
  static InterestCalculator create1(double rate) {
    class GeneralClass implements InterestCalculator {
      private double rate;

      public GeneralClass(double rate) {
        this.rate = rate;
      }

      @Override
      public double compute(int money) {
        return money * (1 + rate);
      }
    }
    return new GeneralClass(rate);
  }

  // 1.일반 클래스2
  static InterestCalculator create2(double rate) {
    class GeneralClass2 implements InterestCalculator {
      @Override
      public double compute(int money) {
        return money * (1 + rate);
      }
    }
    return new GeneralClass2();
  }

  // 2.익명클래스1
  static InterestCalculator create3(double rate) {
    InterestCalculator anonymousClass1 = new InterestCalculator() {
      @Override
      public double compute(int money) {
        return money * (1 + rate);
      }
    };
    return anonymousClass1;
  }

  // 2.익명클래스2
  static InterestCalculator create4(double rate) {
    return new InterestCalculator() {
      @Override
      public double compute(int money) {
        return money * (1 + rate);
      }
    };
  }

  // 3.람다식 1
  static InterestCalculator create5(double rate) {
    return (int money) -> {
      return money * (1 + rate);
    };
  }

  // 4.람다식 2
  static InterestCalculator create6(double rate) {
    return money -> money * (1 + rate);
  }
}
