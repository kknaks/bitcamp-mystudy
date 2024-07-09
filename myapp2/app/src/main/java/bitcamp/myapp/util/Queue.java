package bitcamp.myapp.util;

public class Queue extends LinkedList {
  //push구현하기
  public void offer(Object obj) {
    add(obj);
  }

  //pop구현하기
  public Object poll() {
    return remove(0);
  }

  //empty구현하기
  public boolean isEmpty() {
    return size() == 0;
  }
}
