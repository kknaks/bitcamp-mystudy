package bitcamp.myapp2.util;

public interface List {
  void add(Object obj);

  Object remove(int index);

  Object get(int index);

  Object[] toArray();

  int indexOf(Object obj);

  int size();
}
