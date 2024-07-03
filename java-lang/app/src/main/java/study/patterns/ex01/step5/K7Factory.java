package study.patterns.ex01.step5;

public class K7Factory implements CarFactory {

  private K7Factory() {}

  // 오직 한개의 객체만 저장할 스태틱 필드를 준비

  private static K7Factory instance;

  public static K7Factory getInstance() {
    if (instance == null) {
      instance = new K7Factory();
    }
    return instance;
  }

  @Override
  public Sedan createCar() {
    Sedan s = new Sedan();

    s.maker = "기아자동차";
    s.model = "K7";
    s.cc = 2500;
    s.auto = true;
    s.sunroof = false;
    return s;
  }
}
