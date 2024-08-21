package study.patterns.ex01;

import study.patterns.ex01.step6.Car;
import study.patterns.ex01.step6.CarFactory;
import study.patterns.ex01.step6.K7Factory;
import study.patterns.ex01.step6.SonataFactory;


public class Test06 {
  public static void main(String[] args) {
    SonataFactory sonataFactory = SonataFactory.getInstance();
    K7Factory k7Factory = K7Factory.getInstance();
    K7Factory k7Factory2 = K7Factory.getInstance();
    K7Factory k7Factory3 = K7Factory.getInstance();
    Sedan sedan = new Sedan();
    System.out.println(k7Factory == k7Factory2);
    printCar(sonataFactory);
    printCar(k7Factory);

  }

  static void printCar(CarFactory carFactory) {
    Car car = carFactory.createCar();
    car.play();
  }
}
