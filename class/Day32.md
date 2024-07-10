<h1> Iterator 디자인패턴 </h1>
<h2 style="margin-left: 10px;"> 1.Iterator 패턴의 개념 </h2>
<ul>
    <li>Iterator 디자인 패턴은 컬렉션(예: 리스트, 트리, 해시맵 등)의 내부 구조를 노출하지 않고 그 요소들에 순차적으로 접근할 수 있는 방법을 제공하는 패턴이다.
    <li>반복자(Iterator)를 사용하여 컬렉션의 요소를 하나씩 차례대로 탐색할 수 있다.
</ul>
<h2 style="margin-left: 10px;"> 2.Iterator 설계패턴 </h2>
    <h3 style="margin-left: 20px;"> 1) Iterator의 필요성 </h3>
    <ul>
        <li> 자료 구조마다 메서드를 호출하는 방법이 상이하다. </li>
        <li> 자료 구조마다 동일 기능을 하는 메서드가 다르면 유지보수에 불리하다. </li>
    </ul>
        <div style = "text-align : center;">
        <img width="437" alt="image" src="https://github.com/kknaks/bitcamp-mystudy/assets/118641096/e4dce468-ae5b-43f2-b604-45bef3549f46"></div>
    <h3 style="margin-left: 20px;"> 2) Iterator 패턴적용하기 </h3>
    <ul>
        <li> Iterator 인터페이스를 활용하여 동일한 메서드를 만든다.</li>
        <li> 객체 안에 실질적인 조회 코드를 숨긴다.</li>
        <li> 기존의 객체가 직접적인 조회를 하는 것이 아닌 Iterator가 대신하게 한다.</li>
    </ul>
        <div style = "text-align : center;">
        <img width="538" alt="image" src="https://github.com/kknaks/bitcamp-mystudy/assets/118641096/c4917bdb-f7f5-46ee-8d53-baf55e52810c">
        </div>
<h2 style="margin-left: 10px;"> 3.실습프로젝트에 적용하기 </h2>
    <ul>
    <li> 실습프로젝트에서는 List구조에 대해서 적용한다.</li>
    </ul>
    <h3 style="margin-left: 20px;"> 1) 기존코드 분석 </h3>
    <ul>
        <li> 기존의 Command 구조는 List에서 toArray()를 호출하여 리스트업하는 구조이다.
    </ul>
        <div style = "text-align : center;">
        <img width="1180" alt="image" src="https://github.com/kknaks/bitcamp-mystudy/assets/118641096/b1bb5d7b-cf7c-457b-b925-c0d8913d2fb3">
        </div>

```java
    //BoardCommand
    private void listBoard() {
        System.out.println("번호 제목 작성일 조회수");
        for (Object obj : boardList.toArray()) {
        Board board = (Board) obj;
        System.out.printf("%d %s %tY-%3$tm-%3$td %d\n",
            board.getNo(), board.getTitle(), board.getCreatedDate(), board.getViewCount());
        }
    }
```

<h3 style="margin-left: 20px;"> 2) Iterator 적용 </h3>
<ul>
<li> 조회코드를 캡슐화하고 사용규칙을 통일한다 => 인터페이스를 생성한다.</li>
<li> 생성한 인터페이스를 구현하여 ListIterator을 생성한다.</li>
<li> ListIterator에 기존 List 배열을 받아서 조회를 한다.</li>
</ul>
<div style = "text-align : center;">
<img width="923" alt="image" src="https://github.com/kknaks/bitcamp-mystudy/assets/118641096/2b1e615f-65ec-4c4a-98aa-ccd9e8582eae">
</div>
<details>
<summary> 코드 접기/펴기</summary>

```java
    // Iterator 인터페이스 생성
    public interface Iterator {
    boolean hasNext();
    Object next();
    }

    // ListIterator 구현체 생성
    public class ListIterator implements Iterator {
    private List list;
    private int cursor;
    
    public ListIterator(List list) {
        this.list = list;
    }
    
    @Override
    public boolean hasNext() {
        return cursor < list.size();
    }

    @Override
    public Object next() {
        return list.get(cursor++);
    }
    }
    // list 인터페이스 변경
    public interface List {
    void add(Object obj);

    Object remove(int index);

    Object[] toArray();

    int indexOf(Object obj);

    int size();

    Object get(int index);

    Iterator iterator();
    }
    // AbstractList 변경
    public abstract class AbstractList implements List {
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator iterator() {
        return new ListIterator(this);
    }
    }
    // BoardCommand 메서드변경
    private void listBoard() {
        System.out.println("번호 제목 작성일 조회수");
        Iterator iterator = boardList.iterator();
        while (iterator.hasNext()) {
        Board board = (Board) iterator.next();
        System.out.printf("%d %s %tY-%3$tm-%3$td %d\n", board.getNo(), board.getTitle(),
            board.getCreatedDate(), board.getViewCount());
        }
    }

```
</details>
<h2 style="margin-left: 10px;"> 4.Enhanced for문 </h3>

```java
for (변수타입 변수명 : 배열 or Iterable 구현체) {}
```
<ul>
<li> 진보된 for문에는 배열 또는 Iterable이 가능하다.</li>
<li> ArrayList와 같은 데이터구조는 Iterable의 인터페이스를 사용한 구현체이므로 가능하다.</li>
<li> 직접적으로 Iterable의 구현체를 생성하여도 가능하다.</li>
</ul>

<h1> 중첩클래스 </h1>
<h2 style="margin-left: 10px;"> 1.중첩클래스의 개념 </h2>
<ul>
<li> 클래스 내부에 선언한 클래스이다.</li>
<li> 특정클래스와만 관계를 맺을 경우 중첩클래스로 선언하는 것이 유지보수에 유리하다.</li>
<li> 외부에는 중첩관계를 감춤으로써 코드의 복잡성을 줄일 수 있다.</li>
</ul>
<h2 style="margin-left: 10px;"> 2.중첩클래스의 종류 </h2>
<ul>
<li> 중첩클래스는 크게 클래스의 멤버로서 선언되는 멤버클래스와 메서드내부에 선언 되는 중첩클래스로 나뉜다.</li>
<li> 멤버클래스는 다시 인스턴스 멤버 클래스와 정적멤버클래스로 나뉜다.</li>
</ul>
<div style = "text-align : center;">
<img width="300" alt="image" src="https://github.com/kknaks/bitcamp-mystudy/assets/118641096/12b3ccc0-cba1-4677-94a8-cf30fd51d771">
</div>
<details>
<summary> 코드 접기/펴기</summary>

```java
//중첩 클래스 예시 
public class LinkedList extends AbstractList {

  private Node first;
  private Node last;
  int size;

    /* 내부 코드 생략*/ 
  
  // 중첩클래스 삽입
  public static class Node {

    Object value;
    Node next;

    public Node(Object value) {
      this.value = value;
    }
  }
}
```
</details>

<h3 style="margin-left: 20px;"> 1) 정적멤버 클래스 </h3>
<ul>
<li> static 키워드와 함께 선언된 클래스를 말한다.</li>
<li> 바깥 클래스를 생성하지 않아도 내부 클래스를  생성할 수 있다.</li>
</ul>

```java
[public] class A{
    [public | private] static class B{
        //정적멤버클래스 
    }
}
```
<h4 style="margin-left: 20px;"> ListIterator에 적용 해보기 </h4>
<details>
<summary> 코드 접기/펴기</summary>

```java
public abstract class AbstractList implements List {
  protected int size = 0;

  public int size() { return size;}

  public Iterator iterator() {
    //중첩클래스의 생성자에 넘겨줄 인스턴스가 필요하다.
    return new ListIterator(this);
  }

  static public class ListIterator implements Iterator {
    // AbstractList의 중첩이지만 내부적으로
    // List타입의 주소를 넘겨줘야한다.
    private List list;
    private int cursor;

    // private List와 바깥 List를 동기화하기 위해
    // 생성자가 필요하다.
    public ListIterator(List list) {
      this.list = list;
    }

    @Override
    public boolean hasNext() {
      return cursor < list.size();
    }

    @Override
    public Object next() {
      return list.get(cursor++);
    }
  }
}
```
</details>
<h3 style="margin-left: 20px;"> 2) 인스턴스멤버 클래스 </h3>
<ul>
<li> 인스턴스 멤버클래스는 바깥클래스에서 자유롭게 사용가능하다.</li>
<li> 바깥클래스를 생성해야만 내부 클래스를 생성할 수 있다.</li>
</ul>

```java
[public] class A{
    [public | private] class B{
        //인스턴스멤버클래스 
    }
}
```
<h4 style="margin-left: 20px;"> ListIterator에 적용 해보기 </h4>
<details>
<summary> 코드 접기/펴기</summary>

```java
public abstract class AbstractList implements List {
  protected int size = 0;

  public int size() { return size;}
  // 컴파일러: 바깥 클래스의 인스턴스 주소를 전달하는 코드로 자동 변환
  public Iterator iterator() {
    return new ListIterator();
  }

  public class ListIterator implements Iterator {
    private int cursor;

    @Override
    public boolean hasNext() {
      return cursor < AbstractList.this.size();
    }


    // 바깥 클래스의 인스턴스를 사용하려면
    // => 바깥클래스명.this 라고 지정해야 한다.
    // => 중첩 클래스 안에 해당 필드나 메서드가 없다면
    //    바깥클래스명.this 생략 가능
    @Override
    public Object next() {
      return get(cursor++);
    }
  }
}
```
</details>
<h3 style="margin-left: 20px;"> 3) 로컬 클래스 </h3>
<ul>
<li> 생성자 또는 메서드 내부에서 선언된 클래스를 말한다.</li>
<li> 메서드가 호출되어 있는 동안에만 내부클래스를 생성할 수 있다. </li>
</ul>

```java
[public] class A{
    public A(){
        class B1{ }
    }

    public void method(){
        class B2{ }
    }
}
```

<h4 style="margin-left: 20px;"> ListIterator에 적용 해보기 </h4>
<details>
<summary> 코드 접기/펴기</summary>

```java
public abstract class AbstractList implements List {
  protected int size = 0;

  public int size() { return size;}

  public Iterator iterator() {
    public class ListIterator implements Iterator {
    private int cursor;

    @Override
    public boolean hasNext() {
      return cursor < AbstractList.this.size();
    }
  
    @Override
    public Object next() {
      return get(cursor++);
    }
  }
    return new ListIterator();
  }
}
```
</details>
<h3 style="margin-left: 20px;"> 추가) 익명구현객체 </h3>
<ul>
<li> 익명객체는 이름이 없는 객체를 말한다.</li>
<li> 명시적으로 클래스를 선언하지 않기 때문에 쉽게 객체를 생성 할 수 있다.</li>
<li> 주로 필득밧, 로컬변수값, 매개변수값으로 사용한다.</li>
</ul>

```java
new 부모생성자(매개값,...){
    //필드
    //메서드
}
```

<h4 style="margin-left: 20px;"> ListIterator에 적용 해보기 </h4>
<details>
<summary> 코드 접기/펴기</summary>

```java
public abstract class AbstractList implements List {
  protected int size = 0;

  public int size() { return size;}

  public Iterator iterator() {
    return new Iterator(){
        private int cursor;

        @Override
        public boolean hasNext() {
        return cursor < AbstractList.this.size();
        }
    
        @Override
        public Object next() {
        return get(cursor++);
        }
    };
  }
}
```
</details>
