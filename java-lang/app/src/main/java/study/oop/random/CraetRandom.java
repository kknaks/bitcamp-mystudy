package study.oop.random;

import java.util.Random;

public class CraetRandom {
  public static final Random random = new Random();

  public static void main(String[] args) {
    RandomAction r1 = n -> random.nextInt(n);

    System.out.println(r1.randomDice(10));
  }


  public interface RandomAction {
    int randomDice(int n);
  }


  public static class RandomNum implements RandomAction {
    @Override
    public int randomDice(int n) {
      return random.nextInt(n);
    }
  }


  public static class RandomZero implements RandomAction {
    @Override
    public int randomDice(int n) {
      double probability = ((double) n) / 100;
      return random.nextDouble() < probability ? 0 : 1;
    }
  }
}
