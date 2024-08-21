package study.pattern.ex01_re.step1;

public class SonataFactory implements CarFactory {
  private SonataFactory() {}

  private static SonataFactory instance;

  public static SonataFactory getInstance() {
    if (instance == null) {
      instance = new SonataFactory();
    }
    return instance;
  }

  @Override
  public Sedan createCar() {
    Sedan s = new Sedan();

    s.model = "소나타";
    s.maker = "현대";
    s.cc = 2000;
    s.sunroof = true;
    s.auto = true;
    return s;
  }
}
