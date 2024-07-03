package study.pattern.ex01_re.step1;

public class K7Factory implements CarFactory {
  @Override
  public Sedan createCar() {
    Sedan s = new Sedan();
    s.model = "K7";
    s.maker = "기아";
    s.cc = 2500;
    s.sunroof = false;
    s.auto = true;
    return s;
  }
}
