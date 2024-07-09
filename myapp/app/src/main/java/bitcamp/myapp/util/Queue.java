package bitcamp.myapp.util;

public class Queue extends LinkedList {
  public static void main(String[] args) {
    Queue q = new Queue();
    q.offer("111");
    q.offer("222");
    q.offer("333");

    System.out.println(q.poll());
    System.out.println(q.poll());
    System.out.println(q.poll());
    System.out.println(q.poll());
  }

  public void offer(Object obj) {
    add(obj);
  }

  public Object poll() {
    return remove(0);
  }

  public boolean isEmpty() {
    return size() == 0;
  }

}
