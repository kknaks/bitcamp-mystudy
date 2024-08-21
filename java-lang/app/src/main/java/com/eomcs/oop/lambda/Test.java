package study.oop.lambda;

public class Test {
  public static void main(String[] args) {
    InterestCalculator c1 = Factory.create1(0.025);
    InterestCalculator c2 = Factory.create2(0.025);
    InterestCalculator c3 = Factory.create3(0.025);
    InterestCalculator c4 = Factory.create4(0.025);
    InterestCalculator c5 = Factory.create5(0.025);
    InterestCalculator c6 = Factory.create6(0.025);

    System.out.println(c1.compute(10000000));
    System.out.println(c2.compute(10000000));
    System.out.println(c3.compute(10000000));
    System.out.println(c4.compute(10000000));
    System.out.println(c5.compute(10000000));
    System.out.println(c6.compute(10000000));
  }
}
