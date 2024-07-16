package study.oop.lambda;

import study.oop.lambda.Test04.InterestCalculator;

public class My implements InterestCalculator {
  double rate;

  public My(double rate) {
    this.rate = rate;
  }

  public double comput(int money) {
    return money * (1 + rate);
  }
}
