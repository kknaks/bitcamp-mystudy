package study.lang.literal;

// 실습
// - 다음 파일을 컴파일하고 컴파일된 클래스 파일을 16진수 코드로 읽어라.
// - 문자, 정수, 부동소수점, 논리 값이 값이 인코딩된 결과를 확인하라.
//

public class Test06 {
  public static void main(String[] args) {
    System.out.println("ABC\nabc\n012\n가각간\n똘똠똥\n"); // 41 42 43 0A 61 62 63 0A 30 31 32 0A EA
                                                     // B0 80 EA B0 81 ....
    System.out.println('Y'); // 59
    System.out.println('간'); // AC 04
    System.out.println('8'); // 38
    System.out.println(1234); // 2의 보수로 인코딩된 것을 확인한다. => 04 D2
    System.out.println(-1234); // 2의 보수로 인코딩된 것을 확인한다. => FB 2E
    System.out.println(12.375f); // 부동소수점이 메모리에 저장될 때 2진수로 바뀌는 것을 확인한다. => 41 46 00 00
    System.out.println(true);
    System.out.println(false);
  }
}
