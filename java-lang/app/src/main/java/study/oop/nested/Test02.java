package study.oop.nested;

import java.util.Random;

public class Test02 {
  interface RandomDice {
    public abstract int randomDice(int n);
  }

  public static void main(String[] args) {
    RandomDice dice3;
    int n2 = (dice3 = (int n) -> {
      Random random = new Random();
      int randomNum = random.nextInt(n);
      return randomNum;
    }).randomDice(10);
    System.out.println(n2);

    int n = new RandomDice() {
      @Override
      public int randomDice(int n) {
        Random random = new Random();
        int randomNum = random.nextInt(n);
        return randomNum;
      }
    }.randomDice(10);
    System.out.println(n);

    RandomDice dice2 = new RandomDice() {
      @Override
      public int randomDice(int n) {
        Random random = new Random();
        int randomNum = random.nextInt(n);
        return randomNum;
      }
    };
    System.out.println(dice2.randomDice(10));

    class DiceImpl implements RandomDice {
      @Override
      public int randomDice(int n) {
        Random random = new Random();
        int randomNum = random.nextInt(n);
        return randomNum;
      }
    }

    RandomDice dice = new DiceImpl();
    System.out.println(dice.randomDice(10));
  }
}
