package study.lang.string;

public class Test03 {
  public static void main(String[] args) {
    String s1 = new String("Hello");
    String s2 = new String("Hello");
    String s3 = "Hello";
    String s4 = "Hello";
    String[] s5 = new String[] {"Hello", "World"};

    // identityHashCode를 사용하여 객체의 해시 코드 출력
    System.out.printf("s1 reference : %x\n", System.identityHashCode(s1));
    System.out.printf("s2 reference : %x\n", System.identityHashCode(s2));
    System.out.printf("s3 reference : %x\n", System.identityHashCode(s3));
    System.out.printf("s4 reference : %x\n", System.identityHashCode(s4));
    System.out.printf("s5 reference : %x\n", System.identityHashCode(s5));
    System.out.printf("s5[0] reference : %x\n", System.identityHashCode(s5[0]));
    System.out.printf("s5[1] reference : %x\n", System.identityHashCode(s5[1]));
  }
}
