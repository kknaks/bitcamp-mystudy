package study.patterns.ex02.after1;

public class Printer5 {
  Printer origin = new Printer();
  String sign;

  public Printer5(String sign) {
    this.sign = sign;
  }

  void print(String s) {
    origin.print(s);
    System.out.println("   by   " + sign);
  }
}
