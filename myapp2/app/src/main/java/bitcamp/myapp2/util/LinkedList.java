package bitcamp.myapp2.util;

public class LinkedList extends AbstractList {
  Node first;
  Node last;

  @Override
  public void add(Object value) {
    size++;
    Node newNode = new Node(value);
    if (first == null) {
      first = last = newNode;
      return;
    }
    last.next = newNode;
    last = newNode;
  }

  @Override
  public Object get(int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    Node cursor = first;
    int currentIndex = 0;

    while (cursor != null) {
      if (currentIndex == index) {
        return cursor.value;
      }
      cursor = cursor.next;
      currentIndex++;
    }
    return null;
  }

  @Override
  public Object remove(int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    size--;
    Node cursor = first;
    Node deletedNode = null;
    int currentIndex = 0;

    if (index == 0) {
      deletedNode = cursor;
      first = first.next;
      if (first == null) {
        last = null;
      }
      return deletedNode.value;

    }
    while (cursor != null) {
      if (currentIndex == index - 1) {
        break;
      }
      cursor = cursor.next;
      currentIndex++;
    }
    deletedNode = cursor.next;
    cursor.next = cursor.next.next;
    if (cursor.next == null) {
      last = cursor;
    }
    return deletedNode.value;
  }

  @Override
  public Object[] toArray() {
    Object[] arr = new Object[size];
    Node cursor = first;
    for (int i = 0; i < arr.length; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    return arr;
  }

  @Override
  public int indexOf(Object value) {
    Node cursor = first;
    int currentIndex = 0;
    while (cursor != null) {
      if (cursor.value.equals(value)) {
        return currentIndex;
      }
      cursor = cursor.next;
      currentIndex++;
    }
    return -1;
  }

  public static class Node {
    Object value;
    Node next;

    public Node(Object value) {
      this.value = value;
    }
  }

}
