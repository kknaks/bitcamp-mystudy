package study.patterns.ex01.step4;

public class Sedan extends Car {
  boolean sunroof;
  boolean auto;

  @Override
  public String toString() {
    return "Sedan [maker=" + maker + ", model=" + model + ", cc=" + cc + ", sunroof=" + sunroof
        + ", auto=" + auto + "]";
  }

  protected Sedan() {}
}
