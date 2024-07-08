# 17. 인터페이스를 이용한 객체 사용 규칙 정의: 목록을 다루는 규칙

## 학습목표

- 인터페이스를 이용하여 객체 사용 규칙을 정의하고 사용할 수 있다.
- 패키지를 이용하여 클래스를 분류할 수 있다.
- equals()의 용도를 이해하고 재정의하여 사용할 수 있다.

## 요구사항

- ArrayList와 LinkedList의 사용 규칙을 통일하기
- 목록을 다루는 것과 관련된 클래스를 다른 프로젝트에서 재사용하기 쉽게 분류하기
- UserList, ProjectList, BoardList 클래스를 제거하고 ArrayList, LinkedList를 직접 사용하기
- 메뉴를 처리하는 클래스의 사용 규칙을 통일하기

## 실행 결과

- 이전과 같다.


## 작업

### 목록을 다루는 객체 사용 규칙을 정의

- List 인터페이스 추가 
- ArrayList와 LinkedList 클래스를 List 규칙에 따라 정의한다.
  - ArrayList와 LinkedList 변경
- ArrayList, LinkedList, List 클래스를 별도의 패키지로 분류한다.
  - 관련 클래스 이동
- 리팩토링
  - UserList, ProjectList, BoardList 에서 데이터를 찾는 기능을 equals로 대체 
  - UserList, ProjectList, BoardList 제거
- equals() 재정의
  - Board, Project, User 변경
  - UserCommand, BoardCommand, ProjectCommand 변경

### 메뉴를 처리하는 객체의 사용 규칙을 정의

- Command 인터페이스 추가
- UserCommand, ProjectCommand, BoardCommand 클래스에 인터페이스 적용
- App 클래스에서 XxxCommand 클래스 호출 코드 변경

## 소스 파일

- List.java
- ArrayList.java
- LinkedList.java
- User.java
- Project.java
- Board.java
- Command.java
- UserCommand.java
- ProjectCommand.java
- BoardCommand.java
- App.java