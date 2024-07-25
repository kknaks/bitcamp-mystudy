package myApp.Decorator;

import myApp.Beverage;

public class Milk extends CondimentDecorator{
  public Milk(Beverage beverage) {
    this.beverage = beverage;
  }
  @Override
  public String getDescription() {
    return beverage.getDescription() + ", Milk";
  }

  @Override
  public double cost(){
    return beverage.cost() + 10;
  }
}
