package study.patterns.ex02.before;

public class Printer6 extends Printer5 {
  String header;
  String sign;

  public Printer6(String sign, String header) {
    super(sign);
    this.header = header;
  }

  @Override
  void print(String s) {
    System.out.println(header);
    super.print(s);

  }
}
