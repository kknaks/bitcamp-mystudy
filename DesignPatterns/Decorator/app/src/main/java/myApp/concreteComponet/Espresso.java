package myApp.concreteComponet;

import myApp.Beverage;

public class Espresso extends Beverage {
  public Espresso() {
    description = "Espresso";
  }

  @Override
  public double cost() {
    return 1.99;
  }
}
