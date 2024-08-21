package study.lang.method;

public class Test06 {
  public static void main(String[] args) {
    System.out.println(sigma(100000));
  }

  static int sigma(int number) {
    System.out.println(number);
    if (number == 1) {
      return 1;
    }
    return number + sigma(number - 1);
  }
}
