<h1> Composit Pattern </h1>
<h2 style="margin-left: 10px;"> 1.정적 중첩 클래스(static nested class) </h2>
<h3 style="margin-left: 20px;"> 1) 개념 </h3>
<ul>
<li> 객체들의 트리를 구성하여 부분-전체 계층 구조를 나타내는 패턴이다.</li>
<li> <b>Component</b> : 공통 인터페이스를 정의하여 단일 객체와 복합 객체가 동일한 방식으로 처리될 수 있도록 한다. </li>
<li> <b>Leaf</b> : 트리의 말단 요소로 더이상 하위 요소를 가지지 않는 객체를 나타낸다.</li>
<li> <b>Composite</b> : 하위 요소를 가지는 복합 객체로, 하위 요소들을 관리하고 해당 요소들에게 작업을 전달한다.</li>
</ul>
<h3 style="margin-left: 20px;"> 2) UML으로 이해하기 </h3>
<ul>
<li> 패턴 적용 전 : 여러객체에서 중복된 코드가 발생하며, 하나의 객체가 여러개의 역할을 한다.</li>

![image](https://github.com/user-attachments/assets/a1f77d26-abd6-484f-8496-240b1b081da5)

<li> 패턴 적용 후 : 여러객체에서 중복된 코드가 발생</li>
</ul>