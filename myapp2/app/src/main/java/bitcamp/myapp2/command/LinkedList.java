package bitcamp.myapp2.command;

public class LinkedList {
  Node first;
  Node last;
  int size;

  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.append("v1");
    list.append("v2");
    list.printAll();
  }

  public void append(Object value) {
    size++;
    Node newNode = new Node(value);
    if (first == null) {
      first = last = newNode;
      return;
    }
    last.next = newNode;
    last = newNode;
  }

  public Object getValue(int index) {
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

  public Object[] getArray(int index) {
    Object[] arr = new Object[size];
    Node cursor = first;
    for (int i = 0; i < arr.length; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    return arr;
  }

  public int size() {
    return size;
  }

  public void printAll() {
    Node current = first;
    while (current != null) {
      System.out.println(current.value);
      current = current.next;
    }
  }
}
