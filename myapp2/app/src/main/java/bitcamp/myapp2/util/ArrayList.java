package bitcamp.myapp2.command;

import bitcamp.myapp2.vo.User;

import java.util.Arrays;

public class ArrayList implements List {
  private static final int MAX_SIZE = 100;

  private Object[] list = new Object[MAX_SIZE];
  private int size;

  private void grow() {
    int oldSize = list.length;
    int newSize = oldSize + (oldSize >> 1);
    Object[] arr = new Object[newSize];
    System.arraycopy(list, 0, arr, 0, oldSize);
    list = arr;
  }

  @Override
  public void add(Object obj) {
    if (size == list.length) {
      int oldSize = list.length;
      int newSize = oldSize + (oldSize >> 1);
      list = Arrays.copyOf(list, newSize);
      grow();
    }
    list[size++] = obj;
  }

  @Override
  public Object get(int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    return list[index];
  }

  @Override
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

  @Override
  public Object[] toArray() {
    Object[] arr = new Object[size];
    System.arraycopy(list, 0, arr, 0, size);
    return arr;
  }

  @Override
  public int indexOf(Object obj) {
    for (int i = 0; i < size; i++) {
      if (list[i].equals(obj)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public int size() {
    return size;
  }

  public boolean contain(User user) {
    return indexOf(user) != -1;
  }
}
