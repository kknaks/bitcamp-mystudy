package study.oop.nested;

import java.util.Random;

public class Test03 {
  public static void main(String[] args) {
    Test03 test = new Test03();
    test.doAction();
  }

  public void doAction() {
    RandomAction randomAction = (int n) -> num(10);
    System.out.println("Random number: " + randomAction.randomDice(10));
  }

  public int num(int n) {
    Random random = new Random();
    int randomNum = random.nextInt(n);
    return randomNum;
  }
}
