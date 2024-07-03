package study.patterns.ex01.step2;

public class Sedan extends Car {
  boolean sunroof;
  boolean auto;

  @Override
  public String toString() {
    return "Sedan [maker=" + maker + ", model=" + model + ", cc=" + cc + ", sunroof=" + sunroof
        + ", auto=" + auto + "]";
  }

  private Sedan() {}

  public static Sedan create(String model) {
    Sedan S = new Sedan();

    switch (model) {
      case "소나타":
        S.maker = "현대자동차";
        S.model = "소나타";
        S.cc = 1998;
        S.auto = true;
        S.sunroof = true;
        break;

      case "K7":
        S.maker = "기아자동차";
        S.model = "K7";
        S.cc = 2500;
        S.auto = true;
        S.sunroof = false;
        break;
    }
    return S;
  }
}
