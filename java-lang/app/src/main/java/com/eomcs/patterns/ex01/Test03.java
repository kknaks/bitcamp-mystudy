package study.patterns.ex01;

import study.patterns.ex01.step3.Car;
import study.patterns.ex01.step3.CarFactory;
import study.patterns.ex01.step3.K7Factory;
import study.patterns.ex01.step3.SonataFactory;


public class Test03 {
  public static void main(String[] args) {
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
