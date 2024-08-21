package study.oop.clazz;

public class TestThis {

  static class A {
    String name = "A";
    boolean working = true;

    void print() {
      System.out.println("A.print():");
      System.out.printf("  => this.name(%s)\n", this.name);
      System.out.printf("  => this.working(%s)\n", this.working);
    }

    void m1() {

      System.out.println("super.m1() 메서드=> A.m1()");
    }
  }


  static class A2 extends A {
    String name = "A2";
    int age = 20;

    @Override
    void print() {
      System.out.println("A2의 print문");
      System.out.printf("this.name 필드 = > %s\n", this.name);
      this.m1();
      System.out.printf("super.name 필드 = > %s\n", super.name);
      super.m1();
    }

    @Override
    void m1() {
      System.out.println("A2.m1()");
    }
  }


  static class A3 extends A2 {
    String name = "A3";
    String gender = "남";

    @Override
    void m1() {
      System.out.println("this.m1() 메서드=> A3.m1()");
    }
  }

  public static void main(String[] args) {
    A2 obj = new A3();
    obj.print();
  }
}
