package study.lang.operator;
// 실습
// - 산술 연산자를 사용

// 학습내용
// - 정수와 부동소수점의 연산결과는 부동소수점이다.
// => 연산은 반드시 같은 타입만 가능
// => 타입이 다르면
public class Test01 {
  public static void main(String[] args) {
    // 정수의 연산
    System.out.println("정수연산");
    System.out.println(5 + 2); // 7
    System.out.println(5 - 2); // 3
    System.out.println(5 * 2); // 10
    System.out.println(5 / 2); // 2
    System.out.println(5 % 2); // 1

    // 부동소수점의 연산(소수점 최소 한자리)
    System.out.println("부동소수점 연산");
    System.out.println(5.0 + 2.0); // 7.0
    System.out.println(5.0 - 2.0); // 3.0
    System.out.println(5.0 * 2.0); // 10.0
    System.out.println(5.0 / 2.0); // 2.5
    System.out.println(5.0 % 2.0); // 1.0

    // 정수와 부동수소점의 연산결과
    System.out.println("정수와 부동소수점 연산");
    System.out.println(5 + 2.0); // 7.0
    System.out.println(5 - 2.0); // 3.0
    System.out.println(5 * 2.0); // 10.0
    System.out.println(5 / 2.0); // 2.5
    System.out.println(5 % 2.0); // 1.0

  }

}
