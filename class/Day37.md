<h1> 메서드 레퍼런스 </h1>
<h2 style="margin-left: 10px;"> 1. 메소드 레퍼런스의 개념</h2>
<ul>
<li>메서드를 참조해서 매개변수의 정보 및 리턴 타입을 알아내 람다식을 간소화 하는 것을 목적으로 사용한다.</li>
<li>정적 메소드는 클래스 이름 뒤에 ::기호를 붙인다.</li>

```java
클래스::메서드
```
<li>인스턴스 메서드는 객체를 생성하여 레퍼런스를 만들고 ::기호를 붙인다.</li>

```java
레퍼런스::메서드
```
</ul>
<h2 style="margin-left: 10px;"> 2. 스태틱 메서드 레퍼런스</h2>
<h3 style="margin-left: 20px;"> 1) 스태틱 메서드 레퍼런스 구현 </h3>
<ul>
<li> 기존 람다식 구현하는 방법에 ::기호를 추가한다.</li>
<li> 기존 람다식 구현하는 방법</li>
<ol style="padding-left: 10px;">
<li>일반클래스로 만든다.</li>
<li>익명클래스로 만든다.</li>
<li>람다식으로 만든다.</li>
<li>메서드레퍼런스로 변경한다.</li>
</ol>
<li> 다음과 같이 인터페이스와 static 클래스를 구성한다.</li>

```java
static class My{
    public static int plus(int a, int b){
        return a + b;
    }
}

interface Cal{
    int compute(int x, int y);
}
```
<ol style="padding-left: 10px;">
<li> 일반클래스 구현</li>

```java
public class Test{
    public static void main(String[] args){
        class CalPlus implements Cal{
            @Override
            public int compute(int x, int y){
                return My.plus(x,y);
            }
        }
        Cal c1 = new CalPlus();
        System.out.println(c1.compute(100,200));
        //300 출력
    }
}
```
<li> 익명클래스 구현</li>

```java
public class Test{
    public static void main(String[] args){
        Cal c2 = new Cal{
            @Override
            public int compute(int x, int y){
                return My.plus(x,y);
            }
        };
        System.out.println(c2.compute(100,200));
        //300 출력
    }
}
```
<li> 람다클래스 구현</li>

```java
public class Test{
    public static void main(String[] args){
        Cal c3 = (x, y) -> My.plus(x,y);
        System.out.println(c3.compute(100,200));
        //300 출력
    }
}
```
<li> 메서드 레퍼런스</li>

```java
public class Test{
    public static void main(String[] args){
        Cal c4 = My::plus
        System.out.println(c4.compute(100,200));
        //300 출력
    }
}
```
</ol>
</ul>
<h3 style="margin-left: 20px;"> 2) 스태틱 메서드 레퍼런스 원리 </h3>
<ul>
<li> 메서드 레퍼런스는 다음과 같이 구현한다.</li>

```java
static class My{
    public static int plus(int a, int b){
        return a + b;
    }
}

interface Cal{
    int compute(int x, int y);
}

public class Test{
    public static void main(String[] args){
        Cal c4 = Plus::Cal
    }
}
```
<li> 컴파일 단계에서 My::plus는 익명 클래스로 전환된다</li>
<li> 컴파일 단계</li>
<ol style="padding-left: 10px">
<li>Cal인터페이스를 구현는 클래스를 하나 만든다.</li>
<li>Cal인터페이스의 compute(int,int)를 오버라이딩한다.</li>
<li>static class My에 있는 메서드 plus를 호출한다.</li>
</ol>

```java
Cal c4 = My::plus

class $1 implements Cal{
    @Override
    public int compute(int x, int y){
        return My.plus(x,y)
    }
}
Cal c4 = new $1();
```
</ul>
<h3 style="margin-left: 20px;"> 3) 스태틱 메서드 레퍼런스 특징 </h3>
<ul>
<li>메서드 레퍼런스의 특징은 추상메서드에 의해 생긴다.</li>
<li><b>매개변수</b> : 추상메서드의 매개변수와 동일해야 사용가능하다.</li>
<details>
<summary> 예제코드 접기/펼치기</summary>

```java
  static class MyCalculator {
    public static int plus(int a, int b) return a + b;
    public static int minus(int a, int b) return a - b;
    public static int multiple(int a, int b) return a * b;
    public static int divide(int a, int b) return a / b;
    public static int power(int a) return a * 2;
  }
  interface Calculator {
    int compute(int a, int b);
  }

  public static void main(String[] args){
    //인터페이스의 매개변수 (int,int)
    // -> 메서드 레퍼런스의 매개변수 (int,int)
    Calculator c01 = MyCalculator::plus;      //OK
    Calculator c02 = MyCalculator::minus;     //OK
    Calculator c03 = MyCalculator::multiple;  //OK
    Calculator c04 = MyCalculator::divide;    //OK
    //Calculator c05 = MyCalculator::power;   //NG
  }
```
</details>
<li><b>반환값</b> : 추상메서드의 반환값을 반환 할 수 있는 메서드만 가능하다. </li>
<details>
<summary> 예제코드 접기/펼치기</summary>

```java
 static class MyCalculator {
    public static int plus(int a, int b) {
      return a + b;
    }
  }

  interface Calculator1 {double compute(int a, int b);}
  interface Calculator2 {float compute(int a, int b);}
  interface Calculator3 {short compute(int a, int b);}
  interface Calculator4 {void compute(int a, int b);}
  interface Calculator5 {Object compute(int a, int b);}
  interface Calculator6 {String compute(int a, int b);}

  public static void main(String[] args) {
    // 리턴 타입 int ===> double
    Calculator1 c1 = MyCalculator::plus; // OK!

    // 리턴 타입 int ===> float
    Calculator2 c2 = MyCalculator::plus; // OK!

    // 리턴 타입 int ===> short
    //    Calculator3 c3 = MyCalculator::plus; // 컴파일 오류!

    // 리턴 타입 int ===> void
    Calculator4 c4 = MyCalculator::plus; // OK!

    // 리턴 타입 int ===> Object
    Calculator5 c5 = MyCalculator::plus; // OK!

    // 리턴 타입 int ===> String
    // Calculator6 c6 = MyCalculator::plus; // 컴파일 오류!
  }
```
</details>
</ul>

<h2 style="margin-left: 10px;"> 3. 인스턴스 메서드 레퍼런스</h2>
<h3 style="margin-left: 20px;"> 1) 인스턴스 메서드 레퍼런스 구현 </h3>
<ul>
<li> FunctionalInterface를 기존 인스턴스 매서드를 활용하여 구현체로 만들 수 있다.</li>
<li> 단, 스태틱 메서드 레퍼런스와 마찬가지로 추상메서드의 특징을 만족해야한다.</li>
<ol style="padding-left: 10px;">
<li> 추상매서드의 매개변수의 개수와 인스턴스 매서드의 개수는 동일</li>
<li> 추상매서드의 매개변수 타입보다 큰 범위의 인스턴스 매서드의 매개변수 타입</li>
<li> 추상매서드의 리턴값보다 작은 범위의 인스턴스 매서드의 리턴값</li>
</ol>
<li> 인스턴스 매서드를 만드는 방법은 동일하다.</li>
<li> 다만 인스턴스 매서드의 래퍼런스를 먼저 생성해야한다.</li>
<details>
<summary> 예제코드 접기/펼치기</summary>

```java
public class Test {

  static class Calculator {
    double rate;
    public Calculator(double rate) {this.rate = rate;}
    public double year(int money) {return money * rate / 100;}
    public double month(int money) {return money * rate / 100 / 12;}
    public double day(int money) {return money * rate / 100 / 365;}
    public double bonus() {return 100000;}
  }

  static interface Interest {double compute(int money);}

  public static void main(String[] args) {
    //인스턴스 객체 생성
    Calculator 보통예금 = new Calculator(0.5);

    // 인터페이스 레퍼런스 = 객체::인스턴스매서드
    Interest i1 = 보통예금::year; 

    // 람다 문법으로 표현하면:
    //    Interest i1 = money -> 보통예금.year(money);
    System.out.printf("년 이자: %.1f\n", i1.compute(10_0000_0000));

    i1 = 보통예금::month;
    System.out.printf("월 이자: %.1f\n", i1.compute(10_0000_0000));

    i1 = 보통예금::day;
    System.out.printf("일 이자: %.1f\n", i1.compute(10_0000_0000));
  }
}
```
</details>

</ul>
<h2 style="margin-left: 10px;"> 4. 생성자 레퍼런스</h2>
<h3 style="margin-left: 20px;"> 1) 생성자 래퍼런스의 구현 </h3>
<ul>
<li>인터페이스의 추상메서드가 생성자의 형식과 일치 한다면 생성자 래퍼런스를 구현 할 수 있다.</li>
<li></li>
<li></li>

```java
public class Test {

  static class Message {
    String name;
    public Message() { this.name = "이름없음"; }
    public Message(String name) { this.name = name; }
    public void print() {
      System.out.printf("%s님 반갑습니다!\n", name);
    }
  }

  static interface Factory1 {
    Message get();
  }

  static interface Factory2 {
    Message get(String name);
  }
  public static void main(String[] args) {
    Factory1 f1 = Message::new; 
    Message m1 = f1.get();
    m1.print(); //이름없음님 반갑습니다!

    Factory2 f2 = Message::new; 
    Message m2 = f2.get("홍");
    m2.print(); //홍님 반갑습니다!
  }
}
```
</ul>
