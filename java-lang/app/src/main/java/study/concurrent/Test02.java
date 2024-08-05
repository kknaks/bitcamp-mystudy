package study.concurrent;

class MyThread2 extends Thread {
  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      System.out.printf("자식 스레드 ---> %d\n", i);
    }
    super.run();
  }
}


public class Test02 {
  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      System.out.printf("=====> %d\n", i);
    }
    System.out.println("----------------------");

    Thread t = new MyThread2();
    t.start();

    for (int i = 0; i < 100; i++) {
      System.out.printf("main ~~~~~> %d\n", i);
    }
  }
}
