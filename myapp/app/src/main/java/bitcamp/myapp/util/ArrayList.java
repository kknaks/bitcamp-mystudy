package bitcamp.myapp.command;

import java.util.Arrays;

public class ArrayList implements List {

  private static final int MAX_SIZE = 3;

  private Object[] list = new Object[MAX_SIZE];
  private int size = 0;

  @Override //재정의 또는 추상메서드 구현
  public void add(Object obj) {
    if (size == list.length) {
      //grow();
      int oldSize = list.length;
      int newSize = oldSize + (oldSize >> 1);
      list = Arrays.copyOf(list, newSize);
    }
    list[size++] = obj;
  }

  private void grow() {
    int oldSize = list.length;
    int newSize = oldSize + (oldSize >> 1);
    Object[] arr = new Object[newSize];
    for (int i = 0; i < oldSize; i++) {
      arr[i] = list[i];
    }
    list = arr;
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
    for (int i = 0; i < arr.length; i++) {
      arr[i] = list[i];
    }
    return arr;
  }

  @Override
  public int indexOf(Object obj) {
    for (int i = 0; i < size; i++) {
      if (list[i] == obj) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Object get(int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    return list[index];
  }

  public boolean contains(Object obj) {
    return indexOf(obj) != -1;
  }
}
