# UML
## UML이란?
- 

## UML case
- UML은 크게 6가지로 구분  할 수 있다.
![alt text](image.png)
![alt text](image-1.png)

# 실습프로젝트 리팩토링하기
## 인터페이스의 활용
- 기존 프로젝트의 List 항목들에서 중복된 코드를 줄이자.
- 다음가 같은 UML을 가지도록 수정 한다.

<p align ="center">
![alt text](image-2.png)
</p>

- 먼저 ArrayList와 LinkedList의 공통 코드를 추상 메소드(pulic abstract)로 선언한다.
``` java 
//List 인터페이스
public interface List {
  void add(Object obj);

  Object remove(int index);

  Object[] toArray();

  int indexOf(Object obj);

  int size();

  Object get(int index);
}
```

- 다음 ArrayList와 LinkedList의 동일한 코드를 Abstract class    로 오버라이딩을 한다.

```java 
public abstract class AbstractList implements List {
  protected int size = 0;

  @Override
  public int size() {
    return size;
  }
}
```

- 이후 ArrayList와 LinkedList의 상세코드를 오버라이딩으로 수정한다.

```java
public class ArrayList extends AbstractList {

  private static final int MAX_SIZE = 3;

  private Object[] list = new Object[MAX_SIZE];
  private int size = 0;

  @Override
  public void add(Object obj) {
    if (size == list.length) {
      int oldSize = list.length;
      int newSize = oldSize + (oldSize >> 1);
      list = Arrays.copyOf(list, newSize);
    }
    list[size++] = obj;
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
    System.arraycopy(list, 0, arr, 0, arr.length);
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
    java.util.ArrayList l;
    return list[index];
  }

  public boolean contains(Object obj) {
    return indexOf(obj) != -1;
  }

}
```

```java
public class LinkedList extends AbstractList {

  Node first;
  Node last;
  int size;

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

    Node deletedNode = null;
    size--;

    if (index == 0) {
      deletedNode = first;
      first = first.next;
      if (first == null) {
        last = null;
      }
      return deletedNode.value;
    }

    Node cursor = first;
    int currentIndex = 0;

    while (cursor != null) {
      if (currentIndex == (index - 1)) {
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
  public int indexOf(Object value) {
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
  public Object[] toArray() {
    Object[] arr = new Object[size];

    Node cursor = first;
    for (int i = 0; i < size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }

    return arr;
  }

  @Override
  public int size() {
    return size;
  }
}
```

## 