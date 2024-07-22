package study.patterns.ex02.before;

public class Test01 {
  public static void main(String[] args) {
    Printer printer = new Printer();
    printer.print("안녕하세요");
    System.out.println("---------printer 종료");

    Printer printer2 = new Printer2("머릿말");
    printer2.print("안녕하세요");
    System.out.println("---------printer2 종료");

    Printer printer3 = new Printer3("머릿말", "꼬릿말");
    printer3.print("안녕하세요");
    System.out.println("---------printer3 종료");

    Printer printer4 = new Printer4("꼬릿말");
    printer4.print("안녕하세요");
    System.out.println("---------printer4 종료");

    Printer printer5 = new Printer5("naknak");
    printer5.print("안녕하세요");
    System.out.println("---------printer5 종료");

    Printer printer6 = new Printer6("naknak", "머릿말");
    printer6.print("안녕하세요");
    System.out.println("---------printer6 종료");
  }
}
