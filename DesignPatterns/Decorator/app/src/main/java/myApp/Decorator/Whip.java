package myApp.Decorator;

import myApp.Beverage;

public class Whip extends CondimentDecorator{
  public Whip(Beverage beverage) {
    this.beverage = beverage;
  }
  @Override
  public String getDescription() {
    return beverage.getDescription() + ", Mocha";
  }

  @Override
  public double cost(){
    return beverage.cost() + 10;
  }
}
