package study.lang.method;

public class Test09 {
  public static void main(String[] args) {
    int[] values = new int[] {100, 110, 120};
    m1(values);
    System.out.println(values[0]);
  }

  static void m1(int[] value) {
    value[0] = 200;
  }
}
