package study.oop.nested;

import java.util.Random;

public class CreateRandom {
  interface RandomAction {
    public abstract int randomDice(int n);
  }

  static class RandomNum implements RandomAction {
    @Override
    public int randomDice(int n) {
      Random random = new Random();
      int randomNum = random.nextInt(n);
      return randomNum;
    }
  }

  static class RandomZero implements RandomAction {
    @Override
    public int randomDice(int n) {
      double probability = ((double) n) / 100;
      Random random = new Random();
      return random.nextDouble() < probability ? 0 : 1;
    }
  }

  public static void main(String[] args) {
    RandomAction randomNum = new CreateRandom.RandomNum();
    System.out.println("Random number: " + randomNum.randomDice(10));

    RandomAction randomZero = new CreateRandom.RandomZero();
    System.out.println("Random zero or one: " + randomZero.randomDice(0));
  }
}
