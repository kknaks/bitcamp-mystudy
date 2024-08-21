package study.patterns.ex02.after1;

public class Printer2 {
  Printer origin = new Printer();
  String header;

  public Printer2(String header) {
    this.header = header;
  }

  void print(String s) {
    System.out.println(header);
    origin.print(s);
  }

}
