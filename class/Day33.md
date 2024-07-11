<h1> 중첩클래스 (이어서) </h1>
<h2 style="margin-left: 10px;"> 1.정적 중첩 클래스(static nested class) </h2>
<h3 style="margin-left: 20px;"> 1) 개념 </h3>
<ul>
<li> 바깥 클래스의 인스턴스에 종속되지 않는 클래스.</li>
<li> top level class와 동일하게 사용된다.</li>
</ul>
<h3 style="margin-left: 20px;"> 2) 접근제한자(modifier) </h3>
<ul>
<li> 정적 중첩클래스도 클래스의 멤버이기 때문에 필드나 메서드처럼 접근 제한자를 붙일수 있다.</li>
<li> 클래스 내부에서 사용되기도 하지만, 외부에서 사용되는 경우도 많아 default나 public을 많이 쓴다.₩</li>
</ul>

```java 
    public class StaticNestedClass {
        private static class A1 {} 
        static class A2 {} //(package-private)
        protected static class A3 {}
        public static class A4 {}
    }
```
<h3 style="margin-left: 20px;"> 3) 클래스의 정의와 인스턴스 생성 </h3>
<ul>
<li> 정적 중첩 클래스 내부에 스태틱 멤버와 인스턴스 멤버 모두 선언 가능하다.</li>
<li> 호출 시에도 static 메서드 처럼 레퍼런스로 바로 선언 하거나, 인스턴스를 생성 후 호출 할 수 있다.</li>
</ul>
<details>
<summary> 코드 펼치기 / 접기 </summary>

```java
    class A2 {
        static class X {
            // top level class 처럼 스태틱 멤버 선언 가능
            static int v1;
            static void m1() {}
            static {}

            // top level class 처럼 인스턴스 멤버 선언 가능
            int v2;
            void m2() {}
            {}
        }
    }

    public class StaticNested{
        public static void main(String[] args) {
            // 레퍼런스 선언
            A2.X obj;

            // 인스턴스 생성
            obj = new A2.X();
        }
    }
```
</details>
<h3 style="margin-left: 20px;"> 4) 바깥클래스에 접근하기 </h3>
<ul>
<li> 일반적으로 클래스의 스태틱 멤버에 접근할 때는 클래스 이름을 명시해야한다. - Case 1</li>
<li> 클래스 X도 B2의 클래스 멤버이기 때문에 바깥클래스 이름을 생략할 수 있다. - Case 2</li>
<li> 바깥 클래스의 인스턴스 주소를 담는 B2.this 라는 인스턴스 멤버가 없다. 바깥 클래스의 인스턴스 멤버에 접근할 수 없다. - Case 3</li>
<li> Static으로 선언된 멤버는 메모리상에 MethodArea에 생성되여 직접 접근 가능하지만, non-static은 Heap 인스턴스를 받아와야한다.</li>
</ul>

<details>
<summary> 코드 펼치기 / 접기 </summary>

```java
    class B2 {
        // 클래스 멤버
        static int v1;
        static void m1() {}

        // 인스턴스 멤버
        int v2;
        void m2() {}

        static class X {
            void test() {
            //Case1
            B2.v1 = 100;
            B2.m1();
            //Case2
            v1 = 200;
            m1();
            //Case3
            // v2 = 100; // 컴파일 오류!
            // m2(); // 컴파일 오류!
            }
        }
    }

    public class StaticNestedAcessMember {
        public static void main(String[] args) {
            B2.X obj = new B2.X();
            obj.test();
        }
    }
```
</details>

<h3 style="margin-left: 20px;"> 5) 정적 중첩클래스에 접근하기 </h3>
<ul>
<li> 바깥 클래스에서 같은 스태틱 멤버는 접근가능하다.</li>
<li> 인스턴스 멤버는 스태틱 멤버는 사용가능하다.</li>
</ul>
<details>
<summary> 코드 펼치기 / 접기 </summary>

```java
    class C {
        static void m1() {
            // 같은 스태틱 멤버는 사용 가능!
            X obj = new X();
            obj.test();//인스턴스 메서드는 객체생성 후 호출가능
            X.test2(); //static 메서드는 바로 호출 가능 
        }

        void m2() {
            // 인스턴스 멤버는 스태틱 멤버 사용 가능!
            m1(); // OK!
            X obj = new X();
            obj.test();
        }

        static class X {
            void test() {
            System.out.println("X.test()");
            }

            static void test2() {
            System.out.println("X.test2()");
            }
        }
    }


    public class StaticNestedAcessTopClass {
        public static void main(String[] args) {
            //static 호출
            C.m1();
            //인스턴스 호출
            C outer = new C();
            outer.m2();
        }
    }

```
</details>

<h2 style="margin-left: 10px;"> 2.인스턴스 중첩 클래스(non-static nested class) </h2>
<h3 style="margin-left: 20px;"> 1) 개념 </h3>
<ul>
<li> 바깥 클래스의 인스턴스 클래스에 종속되는 클래스.</li>
<li> 바깥 클래스의 인스턴스 멤버를 사용할 수 있다.</li>
<li> 바깥클래스의 인스턴스 없이 생성할 수 없다. </li>
</ul>

<h3 style="margin-left: 20px;"> 2) 접근제한자(modifier) </h3>
<ul>
<li> 인스턴스 중첩클래스도 클래스의 멤버이기 때문에 필드나 메서드처럼 접근 제한자를 붙일수 있다.</li>
<li> 바깥 클래스의 객체가 생성되어야 쓸수 있기때문에 일반적으로 private를 많이 사용한다.</li>
</ul>

```java
    public class NonStaticNestedClass {
        private class A1 {} 
        class A2 {} //(package-private)
        protected class A3 {}
        public class A4 {}
    }
```
<h3 style="margin-left: 20px;"> 3) 클래스의 정의와 인스턴스 생성 </h3>
<ul>
<li> 컴파일 시 inner클래스에 바깥 클래스의 인스턴스주소를 받는 생성자를 만든다.</li>
<li> 바깥 클래스의 객체가 생성된 이후에 인스턴스 중첩클래스를 선언해야한다.</li>
</ul>
<details>
<summary> 코드 펼치기 / 접기 </summary>

```java
    class A {
        static void m1(int a) {
            // static 메서드는 A의 인스턴스를 저장하는 this 라는 변수가 없다.
        }
        void m2(int b) {
            // non-static 메서드는 A의 인스턴스를 저장할 this라는 변수가 있다.
        }
        class X { // inner class
            // 컴파일러는 inner 클래스를 컴파일 할 때 다음과 같이
            // - 바깥 클래스의 인스턴스 주소를 저장할 필드를 추가하고,
            // - 바깥 클래스의 인스턴스의 주소를 파라미터로 받는 생성자를 만든다.
            //
            // A outer;
            //
            // public X(A obj) {
            // this.outer = obj;
            // }
        }
        static class Y {
        }
    }

    public class NonStaticNested {
        public static void main(String[] args) {
            // 레퍼런스 선언
            A.X obj;
            A.Y obj2;

            // 인스턴스 생성
            obj2 = new A.Y(); // 스태틱 중첩 클래스는 바깥 클래스의 인스턴스가 없어도 생성할 수 있다.
            // obj = new A.X(); // 컴파일 오류! 바깥 클래스의 인스턴스 주소 없이 생성 불가!

            A.m1(0); // 스태틱 멤버를 사용할 때는 인스턴스가 없어도 된다.
            // A.m2(0); // 인스턴스 멤버를 사용하려면 반드시 해당 객체가 있어야 한다.

            // 1) 바깥 클래스의 인스턴스 준비
            A outer = new A();

            // non-static 메서드를 호출하려면 인스턴스 주소가 필요하듯이,
            outer.m2(0);

            // 2) inner class의 인스턴스를 생성할 때도 바깥 클래스의 인스턴스 주소가 필요하다.
            obj = outer.new X();
            A.X obj3 = new A().new X();

            // 컴파일러는 컴파일 할 때 다음과 같이
            // - 바깥 클래스의 객체를 생성자에 전달하는 코드로 변경한다.
            // obj = new A.X(outer);
        }
    }
```
</details>
<h3 style="margin-left: 20px;"> 4) 바깥클래스에 접근하기 </h3>
<ul>
<li> 중첩 클래스에서 바깥 클래스의 스태틱 멤버에 접근할 때는 바깥 클래스 이름을 생략할 수 있다.</li>
<li> 인스턴스 중첩 클래스 생성자에서 내부 this를 통해 바깥 클래스의 주소를 넘기기 때문이다.</li>
</ul>
<details>
<summary> 코드 펼치기 / 접기 </summary>

```java
    class B2 {
        // 인스턴스 멤버
        int v2;
        void m2() {
            System.out.println("B2.v2 = " + this.v2);
        }
        class X {
            // 바깥 객체의 주소를 저장할 빌트인 필드
            //    B2 this$0;

            // inner 객체를 생성할 때 바깥 객체의 주소를 받는 생성자
            //    public X(B2 p) {
            //      this.this$0 = p;
            //    }
            void test() {
            // 즉 inner 객체를 만들 때 사용한 바깥 객체에 접근하고 싶다면
            // =>  B2.this  문법을 사용하라!
            //
            System.out.println(B2.this.v2); // ---> this$0.v2
            B2.this.m2();
            }
        }
    }

    public class NonStaticNestedAcessMember {
        public static void main(String[] args) {
            B2 outer = new B2();
            outer.v2 = 100;
            outer.m2();

            // inner 객체 생성
            B2.X inner = outer.new X(); // --> new X(outer)
            B2.X inner2 = outer2.new X(); // --> new X(outer2)

            inner.test();
            inner2.test();
        }
    }
```
</details>
<ul>
<li> 컴파일러가 변수를 찾을 때 순서: 로컬 변수 -> 인스턴스 변수 -> 바깥 객체의 인스턴스 변수</li>
</ul>
<details>
<summary> 코드 펼치기 / 접기 </summary>

```java
    class B3 {
        // 인스턴스 멤버
        int v1 = 10;
        int v3 = 90;
        class X {
            // non-static nested class 는 바깥 클래스의 인스턴스 주소를 저장할 변수를 갖고 있다. ex) B3 this$0;
            int v1 = 20;
            int v2 = 80;

            // non-static nested class 는 바깥 클래스의 인스턴스 주소를 받는 생성자를 갖고 있다. ex)
            // X(B3 obj) {this$0 = obj;}
            void test() {
                int v1 = 1000;
                System.out.printf("v1 = %d\n", v1); // 로컬 변수
                System.out.printf("this.v1 = %d\n", this.v1); // 인스턴스 변수
                System.out.printf("B3.this.v1 = %d\n", B3.this.v1); // 바깥 객체의 인스턴스 변수
                System.out.printf("v1 = %d\n", v2); // 인스턴스 변수
                System.out.printf("v1 = %d\n", v3); // 바깥 객체의 인스턴스 변수
            }
        }
    }


    public class NonStaticNestedAcessMember2 {
        public static void main(String[] args) {
            B3 outer = new B3();
            outer.v1 = 11;
            B3.X x1 = outer.new X();
            x1.test();
        }
    }

```
</details>

<h3 style="margin-left: 20px;"> 5) 인스턴스 중첩클래스에 접근하기 </h3>
<ul>
<li><u> Static 멤버는 레퍼런스 선언은 가능하지만 인스턴스 멤버를 사용할 수 없다</u></li>
<li> 인스턴스 멤버를 사용하기 위해서는 인스턴스를 주소를 담고 있는 this변수가 필요하다.</li>
<li> 인스턴스 멤버만 인스턴스 중첩클래스를 호출 할 수 있다.</li>
</ul>
<details>
<summary> 코드 펼치기 / 접기 </summary>

```java
    class C {
        static void m1() {
            // 스태틱 멤버는 인스턴스 멤버를 사용할 수 없다.
            X obj; // 레퍼런스 선언은 가능!
            //    obj = this.new X(); // 컴파일 오류! 인스턴스 생성 불가능!
        }

        void m2() {
            // 인스턴스 메서드는 인스턴스 주소를 담고 있는 this 변수가 있다.
            X obj = this.new X(); 
            // 위 코드는 다음과 같이 변경될 수 있다.
            // 예1)
            // X obj = new X(this); 라는 코드를 변경된다.
            //
            // 예2) 
            // X obj = new X();
            // obj.this$0 = this;
            obj.test();

            X obj2 = new X(); // 인스턴스 필드나 메서드와 마찬가지로 this를 생략할 수 있다.
            obj2.test();
        }

        class X {
            void test() {
            System.out.println("X.test()");
            }
        }
    }

    public class NonStaticNestedAcessTopClass {
        public static void main(String[] args) {
            C.m1();
            C outer = new C();
            outer.m2();
        }
    }
```
</details>



<h2 style="margin-left: 10px;"> 3.로컬 클래스(local class) </h2>
<h3 style="margin-left: 20px;"> 1) 개념 </h3>
<ul>
<li> 특정 메서드 안에서만 사용되는 클래스</li>
</ul>
<h3 style="margin-left: 20px;"> 2) 접근제한자(modifier) </h3>
<ul>
<li> 로컬 변수처럼 로컬클래스에는 접근 제한자를 붙일 수 없다.</li>
</ul>

```java
    public class NonStaticNestedClass {
        static void m1(){
            // private class A1 {} 
            class A2 {}
            // protected class A3 {}
            // public class A4 {}
        }
        
    }
```
<h3 style="margin-left: 20px;"> 3) 클래스의 정의와 인스턴스 생성 </h3>
<li> Static 로컬 클래스는 static과 동일한 성질을 같는다.</li>
<li> 인스턴스 로컬 클래스는 static과 동일한 성질을 같는다.</li>
</ul>

<h2 style="margin-left: 10px;"> 4.익명 클래스(anonymous class) </h2>
<ul>
<li> 이름이 없어 new 명령으로 인스턴스를 생성 할 수 없다. </li>
<li> 클래스를 정의(호출)하는 동시에 인스턴스를 생성 </li>
</ul>
