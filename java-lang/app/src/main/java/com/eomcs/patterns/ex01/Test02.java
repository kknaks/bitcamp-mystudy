package study.patterns.ex01;

import study.patterns.ex01.step2.Sedan;

public class Test02 {
  public static void main(String[] args) {
    Sedan sonata = Sedan.create("소나타");
    Sedan k7 = Sedan.create("K7");

    System.out.println(sonata);
    System.out.println(k7);
  }
}
