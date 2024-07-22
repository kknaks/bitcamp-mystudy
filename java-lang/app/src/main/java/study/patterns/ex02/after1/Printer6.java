package study.patterns.ex02.after1;

public class Printer6 {
  Printer2 origin;
  String sign;
  String footer;

  public Printer6(String header, String sign) {
    origin = new Printer2(header);
    this.sign = sign;
  }

  void print(String s) {
    origin.print(s);
    System.out.println("   by   " + sign);
  }
}
