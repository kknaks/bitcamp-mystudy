package study.oop.lambda;

public class Test01 {
  interface Player {
    void play();
  }

  public static void main(String[] args) {
    class MyPlayer implements Player {
      @Override
      public void play() {
        System.out.println("일반 클래스");
      }
    }
    Player p1 = new MyPlayer();
    p1.play();

    Player p2 = new Player() {
      @Override
      public void play() {
        System.out.println("추상 클래스");
      }
    };
    p2.play();


    new Player() {
      @Override
      public void play() {
        System.out.println("추상 클래스2");
      }
    }.play();

    Player p3 = () -> {
      System.out.println("람다");
    };
    p3.play();

    Player p4 = () -> System.out.println("람다2");
    p4.play();



  }
}
