package bitcamp.myapp.util;

public class LinkedList extends AbstractList {

  private Node first;
  private Node last;

  @Override
  public void add(Object value) {
    Node newNode = new Node(value);
    if (first == null) {
      last = first = newNode;
    } else {
      last.next = newNode;
      last = newNode;
    }
    size++;
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
    Node deletedNode = null;

    Node cursor = first;
    int currentIndex = 0;

    if (index == 0) {
      deletedNode = first;
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

  public int index(Object value) {
    Node cursor = first;
    int currentIndex = 0;

    while (cursor != null) {
      if (cursor.value == value) {
        return currentIndex;
      }
      cursor = cursor.next;
      currentIndex++;
    }
    return -1;
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

  @Override
  public Object[] toArray() {
    Object[] arr = new Object[size];

    Node cursor = first;
    for (int i = 0; i < size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    return arr;
  }

  //static nested class
  private static class Node {
    Object value;
    Node next;

    public Node(Object value) {
      this.value = value;
    }
  }

}

