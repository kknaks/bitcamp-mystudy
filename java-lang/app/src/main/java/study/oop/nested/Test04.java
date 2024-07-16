package study.oop.nested;

import study.oop.nested.CreateRandom.RandomAction;

public class Test04 {
  public static void main(String[] args) {
    RandomAction randomNum = new CreateRandom.RandomNum();
    System.out.println("Random number: " + randomNum.randomDice(10));

    RandomAction randomZero = new CreateRandom.RandomZero();
    System.out.println("Random zero or one: " + randomZero.randomDice(90));
  }
}
