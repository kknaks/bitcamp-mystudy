package study.oop.clazz;

import java.util.Iterator;

public class Test06 implements Iterable {
  String name = "kknaks";
  String home = "seoul";
  int age = 19;
  boolean study = true;

  class todo implements Iterable {

    public boolean hasNext() {

    }

    public Object next() {

    }

    @Override
    public Iterator iterator() {
      // TODO Auto-generated method stub
      return null;
    }
  }
}
