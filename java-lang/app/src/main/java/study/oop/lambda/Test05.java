package study.oop.lambda;

public class Test05 {
  interface Intro {
    void introduce(String s);
  }

  public static void main(String[] args) {
    // // 1.일반 클래스로 구현하
    // class SelfIntro implements Intro {
    // @Override
    // public void introduce(String s) {
    // System.out.println(s);
    // }
    // }
    // SelfIntro selfintro = new SelfIntro();
    // selfintro.introduce("일반클래스입니다");

    // 2.익명 클래스로 구현하기 - v1
    // Intro anonymousIntro1 = new Intro() {
    // @Override
    // public void introduce(String s) {
    // System.out.println(s);
    // }
    // };
    // anonymousIntro1.introduce("익명클래스1입니다");
    //
    // // 2.익명 클래스로 구현하기 - v2
    // new Intro() {
    // @Override
    // public void introduce(String s) {
    // System.out.println(s);
    // }
    // }.introduce("익명클래스2입니다");


    // 3.람다식으로 구현하기 - v1
    Intro lambdaIntro1 = (String s) -> {
      System.out.println(s);
    };
    lambdaIntro1.introduce("람다1입니다");

    // 3.람다식으로 구현하기 - v2
    Intro lambdaIntro2 = s -> System.out.println(s);
    lambdaIntro2.introduce("람다2입니다");
  }
}
