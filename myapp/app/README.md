# 19. 리팩토링: 상속의 Generalization 적용

## 학습목표

- 상속의 Generalization을 이해하고 적용할 수 있다.
- 추상 클래스의 역할을 이해하고 적용할 수 있다.
- GoF의 Template Method 설계 패턴을 적용할 수 있다.

## 요구사항

- ArrayList, LinkedList의 공통 코드를 분리하여 상속의 generalization으로 처리
- UserCommand, ProjectCommand, BoardCommand의 공통 코드를 분리하여 상속의 generalization으로 처리

## 실행 결과

- 이전과 같다.

## 작업

### ArrayList, LinkedList 일반화 하기

- ArrayList와 LinkedList 공통 분모를 추출한다.
  - AbstractList 클래스 추가
- ArrayList, LinkedList는 AbstractList를 상속한다.
  - ArrayList, LinkedList 변경

### XxxCommand 일반화 하기

- "도움말" 메뉴를 처리할 코드를 별도의 클래스로 분리
  - HelpCommand 클래스 정의
- XxxCommand의 공통 코드를 추출하여 수퍼 클래스로 정의
  - AbstractCommand 추상 클래스 정의
  - UserCommand, ProjectCommand, BoardCommand 클래스를 서브로 클래스로 정의

## 소스 파일

- AbstractList.java
- ArrayList.java
- LinkedList.java
- HelpCommand.java
- UserCommand.java
- ProjectCommand.java
- BoardCommand.java
- App.java