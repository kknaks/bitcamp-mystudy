package study.lang.string;

public class Test01 {
  public static void main(String[] args) {
    String s1;
    s1 = null;
    byte[] v = null;
    // byte b = null;
    // 레퍼런스에만 null를 대입할수 있다.

    s1 = new String("aaa");
    String s2 = new String("aaa");
    String s3 = "aaa";
    String s4 = "aaa";

    System.out.println(s1 == s2);
    System.out.println(s1 == s3);
    System.out.println(s4 == s3);
    System.out.println(s3 == "aaa");
  }
}
