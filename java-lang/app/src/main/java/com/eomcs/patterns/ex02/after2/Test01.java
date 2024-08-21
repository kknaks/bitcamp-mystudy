package study.patterns.ex02.after2;

public class Test01 {
  public static void main(String[] args) {
    ContentPrinter printer = new ContentPrinter();
    printer.print("안녕하세요");
    System.out.println("---------printer 종료");

    ContentPrinter printer2 = new ContentPrinter();
    HeaderPrinter printer2H = new HeaderPrinter(printer2, "머릿말");
    printer2H.print("안녕하세요");
    System.out.println("---------printer2 종료");

    ContentPrinter printer3 = new ContentPrinter();
    HeaderPrinter printer3H = new HeaderPrinter(printer3, "머릿말");
    FooterPrinter printer3F = new FooterPrinter(printer3H, "꼬릿말");
    printer3F.print("안녕하세요");
    System.out.println("---------printer3 종료");

    ContentPrinter printer4 = new ContentPrinter();
    FooterPrinter printer4F = new FooterPrinter(printer4, "꼬릿말");
    printer4F.print("안녕하세요");
    System.out.println("---------printer4 종료");

    ContentPrinter printer5 = new ContentPrinter();
    SignPrinter printer5S = new SignPrinter(printer5, "naknak");
    printer5S.print("안녕하세요");
    System.out.println("---------printer5 종료");

    ContentPrinter printer6 = new ContentPrinter();
    SignPrinter printer6S = new SignPrinter(printer5, "naknak");
    HeaderPrinter printer6H = new HeaderPrinter(printer6S, "머릿말");
    printer6H.print("안녕하세요");
    System.out.println("---------printer6 종료");
  }
}
