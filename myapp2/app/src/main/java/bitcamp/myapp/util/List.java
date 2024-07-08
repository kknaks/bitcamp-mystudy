package bitcamp.myapp.util;

public interface List {
  void add(Object obj);

  Object remove(int index);

  Object[] toArray();

  int indexOf(Object obj);

  int size();

  Object get(int index);
}
