package study.patterns.ex02.before;

public class Printer5 extends Printer {
  String sign;

  public Printer5(String sign) {
    this.sign = sign;
  }

  @Override
  void print(String s) {
    super.print(s);
    System.out.println("   by   " + sign);
  }
}
