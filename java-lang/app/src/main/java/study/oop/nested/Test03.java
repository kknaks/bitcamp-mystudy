package study.oop.nested;

import java.util.Random;

public class Test03 {
  public static void main(String[] args) {
    Test03 test = new Test03();
    test.doAction();
  }

  public void doAction() {
    RandomAction randomAction = (int n) -> randomNum(n);
    System.out.println(randomAction.randomDice(10));

    RandomAction randomAction2 = (int n) -> randomZero(n);
    System.out.println(randomAction2.randomDice(80));
  }

  public int randomZero(int n) {
    double probability = ((double) n) / 100;
    Random random = new Random();
    return random.nextDouble() < probability ? 0 : 1;
  }

  public int randomNum(int n) {
    Random random = new Random();
    int randomNum = random.nextInt(n);
    return randomNum;
  }
}
