package study.oop.lambda;

public class Test01 {
  interface Player {
    void play();
  }

  public static void main(String[] args) {
    // class MyPlayer implements Player {
    // @Override
    // public void play() {
    // System.out.println("일반클래스로 재생합니다.");
    // }
    // }
    // Player p1 = new MyPlayer();
    // p1.play();

    // Player p2 = new Player() {
    // @Override
    // public void play() {
    // System.out.println("익명클래스1로 재생합니다.");
    // }
    // };
    // p2.play();
    //
    //
    // new Player() {
    // @Override
    // public void play() {
    // System.out.println("익명클래스2로 재생합니다.");
    // }
    // }.play();

    Player p3 = () -> {
      System.out.println("람다식1로 재생합니다.");
    };
    p3.play();

    Player p4 = () -> System.out.println("람다식2로 재생합니다.");
    p4.play();



  }
}
