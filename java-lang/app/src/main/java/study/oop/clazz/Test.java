package study.oop.clazz;

public class Test {
  public static void main(String[] args) {
    Integer size = 0;

    System.out.println(size.getClass());

    A a = new A();
    Aa aa = new Aa();
    B b = new B();
    System.out.println(a.getClass());
    System.out.println(aa.getClass());
    System.out.println(b.getClass());
    System.out.println(aa instanceof A);


  }

  public static class A {
    String s = "hello";


  }
  public static class B {
    String s = "hello";

  }
  public static class Aa extends A {

  }
}


