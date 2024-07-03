package study.pattern.ex01_re.step1;

public class Sedan extends Car {
  boolean sunroof;
  boolean auto;

  @Override
  public String toString() {
    return "Sedan [surroof=" + sunroof + ", auto=" + auto + ", maker=" + maker + ", model=" + model
        + ", cc=" + cc + "]";
  }

  protected Sedan() {}

  public static Sedan create(String model) {
    Sedan s = new Sedan();
    switch (model) {
      case "소나타":
        s.model = "소나타";
        s.maker = "현대";
        s.cc = 2000;
        s.sunroof = true;
        s.auto = true;
        break;
      case "K7":
        s.model = "K7";
        s.maker = "기아";
        s.cc = 2500;
        s.sunroof = false;
        s.auto = true;
        break;
    }
    return s;
  }
}
