package study.lang.method;

public class Test07 {
  public static void main(String[] args) {
    System.out.println(sigma(10));

  }

  static int sigma(int number) {
    if (number == 1) {
      return 1;
    }
    return number + sigma(number - 1);
  }
}
