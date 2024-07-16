<h1> 람다 표현식 </h1>
<div>
<h2 style="margin-left: 10px;"> 1.람다식의 개념 </h2>
<h3 style="margin-left: 20px;"> 1) 함수프로그래밍 </h3>
<ul>
<li> 함수를 정의하고 이 함수를 데이터 처리부로 보내 데이터를 처리하는 기법이다..</li>
<li> 데이터 처리부는 데이터만 가지고 있으며 처리 방법이 따로 정해지지 않는다.</li>
<li> 같은 데이터가 주어지더라도 처리하는 함수에 따라 결과가 달라 질 수 있다.</li>
<li> 데이터 처리의 다형성이라고도 볼 수 있다.</li>
</ul>
<h3 style="margin-left: 20px;"> 2) 람다식 </h3>
<ul>
<li> 자바 8부터 함수형 프로그래밍을 위해 람다식을 지원한다.</li>
<li> 람다식은 데이터 처리부에 제공되는 함수 역할을 하는 매개변수를 가진 중괄호 블록이다.</li>

```java
람다식 : (매개변수,...) -> {처리내용}
```
</ul>
<h3 style="margin-left: 20px;"> 3) 람다식의 구현 </h3>
<ul>
<li> 람다식은 익명구현 객체로 변환한다.</li>

```java 
//인터페이스
public interface Calculable{
    //추상메서드
    void calculate(int x, int y);
}

new Calculabe(){
    @Override
    public void calculate(int x, int y){
        //처리내용
    }
}
```
<li> 주어진 인터페이스와 익명구현 객체를 람다식으로 변환 할 수 있다.</li>

```java
(x,y) -> {/*처리내용*/}
```
<li> 이와 같이 람다식을 구현하기 위해서는 하나의 추상메서드를 가진 인터페이스가 필요하다.</li>
<li> 이러한 인터페이스를 함수형 인터페이스라고 부르며, 어노테이션은 @FunctionalInterface라고 한다.</li>
</ul>
</div>

<div>
<h2 style="margin-left: 10px;"> 2.매개변수가 없는 람다식</h2>
<h3 style="margin-left: 20px;"> 1) 기본구조 </h3>
<ul>
<li>매개변수가 없는 람다식의 구조는 아래와 같다.</li>

```java
람다식 : ()->{ /*오버라이딩*/ }
```
</ul>
<h3 style="margin-left: 20px;"> 2) 람다식의 변환 </h3>
<ul>
<li>람다식의 변환과정은 다음과 같다.</li>

![image](https://github.com/user-attachments/assets/7d8959fd-d736-4ee8-8d36-2eeef1d5ed02)
</ul>
<ol>
<li> 일반 클래스로 구현하기 </li>

```java
public class Test{
    //함수형 인터페이스 구현
    @FunctionalInterface
    interface Player{
        void play();
    }

    public static void main(String[] args){
        // 1. 일반클래스로 구현하기
        class MyPlayer implements Player{
            @Override
            public void play(){
                System.out.println("일반클래스로 재생합니다.");
            }
        }

        //실행하기
        Player player = new MyPlayer();
        player.play();
    }
}
```
<ul><li> 실행결과 </li></ul>
<img width="600" alt="image" src="https://github.com/user-attachments/assets/693171f3-ee5e-4195-9442-6569c4075fc2">

<br>
<li> 익명 클래스로 구현하기 </li>

```java
public class Test{
    //함수형 인터페이스 구현
    @FunctionalInterface
    interface Player{
        void play();
    }

    public static void main(String[] args){
        // 2. 익명 클래스로 구현하기 -v1
        Player player = new Player(){
            @Override
            public void play(){
                System.out.println("익명클래스1로 재생합니다.");
            }
        };

        //실행하기
        Player player = new MyPlayer();
        player.play();

        // 2. 익명 클래스로 구현하기 -v2
        new Player(){
            @Override
            public void play(){
                System.out.println("익명클래스2로 재생합니다.");
            }
        }.play();
    }
}
```
<ul><li> 실행결과 </li></ul>
<img width="600" alt="image" src="https://github.com/user-attachments/assets/1a817da6-0afe-44e2-a396-70a5cd7b103c">

<br>
<li> 람다식으로 구현하기 </li>

```java
public class Test{
    //함수형 인터페이스 구현
    @FunctionalInterface
    interface Player{
        void play();
    }

    public static void main(String[] args){
        // 3. 람다식으로 구현하기 -v1
        Player player = ()->{
            System.out.println("람다식1로 재생합니다.");
        };

        //실행하기
        player.play();

        // 3. 람다식으로 구현하기 -v2
        Player player2 = ()->System.out.println("람다식2로 재생합니다.");

    }
}
```
<ul><li> 실행결과 </li></ul>
<img width="600" alt="image" src="https://github.com/user-attachments/assets/06dedfec-b685-4191-8a6c-af02adf94365">
</ol>
<h3 style="margin-left: 20px;"> 3) 매개변수가 없는 람다식의 정리 </h3>
<ul>
<li>매개변수가 없는 경우 추상메서드의 ()을 그대로 사용한다.</li>
<li>{}의 명령어가 하나인 경우 람다식의 바깥부분 {};을 생략할 수 있다.</li>
<li>람다식의 반환값은 객체가 생성되어 <b><u>반환되는 인스턴스 값</b></u>이다.</li>
</ul>
<h2 style="margin-left: 10px;"> 3.매개변수가 있는 람다식</h2>
<h3 style="margin-left: 20px;"> 1) 기본구조 </h3>
<ul>
<li>매개변수가 없는 람다식의 구조는 아래와 같다.</li>

```java
람다식 : (매개변수...)->{ /*오버라이딩*/ }
```
</ul>
<h3 style="margin-left: 20px;"> 2) 람다식의 변환 </h3>
<ul>
<li>람다식의 변환과정은 다음과 같다.</li>

</ul>
<ol>
<li> 일반 클래스로 구현하기 </li>

```java
public class Test{
    interface Intro {
        void introduce(String s);
    }

    public static void main(String[] args) {
        // 1.일반 클래스로 구현하기
        class SelfIntro implements Intro {
        @Override
        public void introduce(String s) {
            System.out.println(s);
        }
        }
        SelfIntro selfintro = new SelfIntro();
        selfintro.introduce("일반클래스입니다");
    }
}
```
<ul><li> 실행결과 </li></ul>
<img width="600" alt="image" src="https://github.com/user-attachments/assets/849b2166-f2ba-454b-a280-3dcaebe4c96c">

<br>
<li> 익명 클래스로 구현하기 </li>

```java
public class Test{
    interface Intro {
        void introduce(String s);
    }

    public static void main(String[] args) {
        // 2.익명 클래스로 구현하기 - v1
        Intro anonymousIntro1 = new Intro() {
            @Override
            public void introduce(String s) {
            System.out.println(s);
            }
        };
        anonymousIntro1.introduce("익명클래스1입니다");

        // 2.익명 클래스로 구현하기 - v2
        new Intro() {
            @Override
            public void introduce(String s) {
            System.out.println(s);
            }
        }.introduce("익명클래스2입니다");
    }
}
```
<ul><li> 실행결과 </li></ul>
<img width="600" alt="image" src="https://github.com/user-attachments/assets/c3bfc81d-a73b-4502-8dfd-be0284969623">

<br>
<li> 람다식으로 구현하기 </li>

```java
public class Test{
    interface Intro {
        void introduce(String s);
    }

    public static void main(String[] args) {
        // 3.람다식으로 구현하기 - v1
        Intro lambdaIntro1 = (String s) -> {
            System.out.println(s);
        };
        lambdaIntro1.introduce("람다1입니다");

        // 3.람다식으로 구현하기 - v2
        Intro lambdaIntro2 = s -> System.out.println(s);
        lambdaIntro2.introduce("람다2입니다");
    }
}
```
<ul><li> 실행결과 </li></ul>
<img width="600" alt="image" src="https://github.com/user-attachments/assets/6f27bcdc-2e63-452b-860e-7684e1961174">
</ol>
<h3 style="margin-left: 20px;"> 3) 매개변수가 있는 람다식의 정리 </h3>
<ul>
<li>(타입 매개변수) 또는 (매개변수)를 사용 할 수 있다.</li>
<li>매개변수가 하나인 경우 ()을 생략할 수 있다.</li>
</ul>
</div>

<h2 style="margin-left: 10px;"> 4.반환값이 있는 람다식</h2>
<h3 style="margin-left: 20px;"> 1) 기본구조 </h3>
<ul>
<li>매개변수가 없는 람다식의 구조는 아래와 같다.</li>

```java
람다식 : (매개변수...)->{ /*오버라이딩*/ return 반환 값;};
```
</ul>
<h3 style="margin-left: 20px;"> 2) 람다식의 변환 </h3>
<ul>
<li>람다식의 변환과정은 같다.</li>

</ul>
<ol>
<li> 일반 클래스로 구현하기 </li>

```java
interface InterestCalculator {
  double compute(int money);
}


public class Factory {
  // 1.일반 클래스1
  static InterestCalculator create(double rate) {
    class GeneralClass implements InterestCalculator {
      private double rate;

      public GeneralClass(double rate) {
        this.rate = rate;
      }

      @Override
      public double compute(int money) {
        return money * (1 + rate);
      }
    }
    return new GeneralClass(rate);
  }

  // 1.일반 클래스2
  // 로컬 클래스로 선언 할 경우 클래스가 속한 static메서드의 
  // 변수값(여기서는rate) 사용할 수 있다.
  static InterestCalculator create(double rate) {
    class GeneralClass2 implements InterestCalculator {
      @Override
      public double compute(int money) {
        return money * (1 + rate);
      }
    }
    return new GeneralClass2();
  }
}
```
<br>
<li> 익명 클래스로 구현하기 </li>

```java
public class Factory {
  // 2.익명클래스1
  static InterestCalculator create3(double rate) {
    InterestCalculator anonymousClass1 = new InterestCalculator() {
      @Override
      public double compute(int money) {
        return money * (1 + rate);
      }
    };
    return anonymousClass1;
  }

  // 2.익명클래스2
  static InterestCalculator create4(double rate) {
    return new InterestCalculator() {
      @Override
      public double compute(int money) {
        return money * (1 + rate);
      }
    };
  }
}
```
<br>
<li> 람다식으로 구현하기 </li>

```java
interface InterestCalculator {
  double compute(int money);
}


public class Factory {
  // 3.람다식 1
  static InterestCalculator create5(double rate) {
    return (int money) -> {
      return money * (1 + rate);
    };
  }

  // 4.람다식 2
  static InterestCalculator create6(double rate) {
    return money -> money * (1 + rate);
  }
}
```
<li> 실행결과 </li>

```java
public class Test {
  public static void main(String[] args) {
    InterestCalculator c1 = Factory.create1(0.025);
    InterestCalculator c2 = Factory.create2(0.025);
    InterestCalculator c3 = Factory.create3(0.025);
    InterestCalculator c4 = Factory.create4(0.025);
    InterestCalculator c5 = Factory.create5(0.025);
    InterestCalculator c6 = Factory.create6(0.025);

    System.out.println(c1.compute(10000000));
    System.out.println(c2.compute(10000000));
    System.out.println(c3.compute(10000000));
    System.out.println(c4.compute(10000000));
    System.out.println(c5.compute(10000000));
    System.out.println(c6.compute(10000000));
  }
}
```
<img width="600" alt="image" src="https://github.com/user-attachments/assets/10975f3e-7a30-4ae6-a1cf-ce564c19ca9f">
</ol>
<h3 style="margin-left: 20px;"> 3) 반환값이 있는 람다식의 정리 </h3>
<ul>
<li>반환값이 있는 람다식을 활용하여 인터페이스의 반환형으로 받을 수 있다.</li>
<li>중괄호를 생략 하려면 "{return"과 "};"을 같이 생략해야한다.</li>
</ul>