package study.thisIsJava;

import java.util.ArrayList;
import java.util.List;

interface box {
}


class A implements box {
  private String name;

  public A(String s) {
    this.name = s;
  }

  public String getName() {
    return name;
  }
}


public class TestClass {
  List<A> list = new ArrayList<>();
  {
    list.add(new A("a"));
    list.add(new A("b"));
    list.add(new A("c"));
  }

  public static void main(String[] args) {
    String[] arr = new String[] {};
    System.out.println(arr.getClass());
    TestClass testClass = new TestClass();
    System.out.println(A.class);
    testClass.setList(testClass.list, "d");
    testClass.setList2(testClass.list, "f", A.class);

    for (A a : testClass.list) {
      System.out.println(a.getName());
    }



  }

  public void setList(List<A> list, String s) {
    list.add(new A(s));
  }

  public <E> void setList2(List<E> list, String s, Class<E> elements) {
    System.out.println(list.getClass());
    System.out.println(elements);
    System.out.println(elements.getInterfaces()[0]);
    Class<?> type = box.class;
    Class<box> type2 = box.class;
    System.out.println(type);
    System.out.println(type2);
    System.out.println(elements.getInterfaces()[0] == type);
    System.out.println("-----");
    list.add((E) (new A(s)));
  }
}
