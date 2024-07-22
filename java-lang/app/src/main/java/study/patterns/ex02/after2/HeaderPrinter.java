package study.patterns.ex02.after2;

public class HeaderPrinter extends PrinterDecorator {
  String header;

  public HeaderPrinter(Printer printer, String header) {
    super(printer);
    this.header = header;
  }

  @Override
  public void print(String s) {
    System.out.println(header);
    origin.print(s);
  }

}
