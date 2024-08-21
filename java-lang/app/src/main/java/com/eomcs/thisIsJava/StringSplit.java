package study.thisIsJava;

public class StringSplit {
  public static void main(String[] args) {
    String s = "번호,제목,내용,성명";
    String s2 = new String("번호,제목,내용,성명");
    String[] arr = s.split(",");

    System.out.println(s == s2);
    System.out.println(s.equals(s2));
    System.out.println(s.hashCode());
    System.out.println(arr.hashCode());
    System.out.println(s.contains(arr[0]));
  }
}
