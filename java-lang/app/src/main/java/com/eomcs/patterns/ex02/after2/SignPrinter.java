package study.patterns.ex02.after2;

public class SignPrinter extends PrinterDecorator {
  String sign;

  public SignPrinter(Printer printer, String sign) {
    super(printer);
    this.sign = sign;
  }

  @Override
  public void print(String s) {
    origin.print(s);
    System.out.println("   by   " + sign);
  }
}
