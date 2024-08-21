package study.oop.random;

import java.util.Random;
import study.oop.random.CraetRandom.RandomAction;

public class Test {
  public static void main(String[] args) {
    Random random = new Random();
    RandomAction r1 = n -> random.nextInt(n);
    RandomAction r2 = random::nextInt(n);
    System.out.println(r1.randomDice(10));
  }
}
