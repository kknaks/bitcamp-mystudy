package study.pattern.ex01_re;

import study.pattern.ex01_re.step1.Car;
import study.pattern.ex01_re.step1.CarFactory;
import study.pattern.ex01_re.step1.K7Factory;
import study.pattern.ex01_re.step1.Sedan;
import study.pattern.ex01_re.step1.SonataFactory;



public class Test {
  public static void main(String[] args) {
    Sedan sonata = Sedan.create("소나타");
    Sedan k7 = Sedan.create("K7");

    System.out.println(sonata);
    System.out.println(k7);

    SonataFactory sonataFactory = new SonataFactory();
    K7Factory k7Factory = new K7Factory();

    printCar(sonataFactory);
    printCar(k7Factory);

  }

  static void printCar(CarFactory carFactory) {
    Car car = carFactory.createCar();
    System.out.println(car);
  }
}
