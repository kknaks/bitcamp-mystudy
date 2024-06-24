package bitcamp.myapp2.command;

import bitcamp.myapp2.vo.User;

public class ArrayList {
  private static final int MAX_SIZE = 100;

  private final Object[] list = new Object[MAX_SIZE];
  private int size;

  public void add(Object obj) {
    list[size++] = obj;
  }

  public Object remove(int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    Object deletedObj = list[index];
    for (int i = index + 1; i < size; i++) {
      list[i - 1] = list[i];
    }
    list[--size] = null;
    return deletedObj;
  }

  public Object[] toArray() {
    Object[] arr = new Object[size];
    System.arraycopy(list, 0, arr, 0, size);
    return arr;
  }

  public int indexOf(Object obj) {
    for (int i = 0; i < size; i++) {
      if (list[i] == obj) {
        return i;
      }
    }
    return -1;
  }

  public int size() {
    return size;
  }

  public Object get(int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    return list[index];
  }

  public boolean contain(User user) {
    return indexOf(user) != -1;
  }
}
