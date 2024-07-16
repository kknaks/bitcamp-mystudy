package study.oop.lambda;

import study.oop.lambda.Test04.InterestCalculator;

public class Test {
  public static void main(String[] args) {
    InterestCalculator c1 = Factory.create(0.035);
    InterestCalculator c2 = Factory.create(0.015);

    System.out.println(c1.compute(10000000));
    System.out.println(c2.compute(10000000));
  }
}
