package bitcamp.myapp.util;

public class Stack extends LinkedList {
  public static void main(String[] args) {
    Stack stk = new Stack();
    stk.push("111");
    stk.push("222");
    stk.push("333");

    System.out.println(stk.pop());
    System.out.println(stk.pop());
    System.out.println(stk.pop());
    System.out.println(stk.pop());
  }

  public void push(Object value) {
    add(value);
  }

  public Object pop() {
    return remove(size() - 1);
  }

  public boolean isEmpty() {
    return size() == 0;
  }
}
