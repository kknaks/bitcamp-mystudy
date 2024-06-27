package study.lang;

public class Test {
  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5};
    Integer a = 5;
    System.out.println(arr[arr.length - 1] == a);

    String[] str = {"b", new String("b")};
    String s = "b";
    System.out.println(str[1] == s);

    String[] str2;
    str2 = str;
    System.out.println(str == str2);
  }
}
