package study.oop.lambda;

public class Test02 {
  interface Player {
    void play();
  }

  public static void main(String[] args) {
    class MyPlayer implements Player {
      @Override
      public void play() {
        System.out.println("1111");
      }
    }
    test(new MyPlayer());

    Player p2 = new Player() {
      @Override
      public void play() {
        System.out.println("2222");
      }
    };
    test(p2);
    test(new Player() {
      @Override
      public void play() {
        System.out.println("3333");
      }
    });

    Player p4 = () -> {
      System.out.println("4444");
    };
    test(p4);

    Player p5 = () -> System.out.println("5555");
    test(p5);

    test(() -> System.out.println("6666"));
  }

  static void test(Player player) {
    player.play();
  }

}
