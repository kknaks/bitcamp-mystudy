package bitcamp.myapp.command;


//데이터 목록을 다루는 일을 할 객체의 사용법
// => 즉 그 객체에게 일을 시킬 때 다음의 메서드를 호출하여 일을 시켜라
// 라고 지정하는 문법
public interface List {
  //데이터를 더할 때 호출할 메서드
  //(public abstract)
  void add(Object value);

  Object remove(int index);

  Object get(int index);

  int indexOf(Object obj);

  Object[] toArray();

  int size();

}
