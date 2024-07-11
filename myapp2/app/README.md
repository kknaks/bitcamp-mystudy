# 23. 특정 클래스에서만 사용되는 의존 객체는 중첩 클래스로 정의하기

## 학습목표

- 중첩 클래스의 구동 원리를 이해하고 사용할 수 있다.

## 요구사항

- 특정 클래스에서만 사용되는 클래스가 있다면 중첩 클래스로 코드를 정리하기

## 실행 결과

- 이전과 같다.

## 작업

### Node 클래스를 중첩 클래스로 전환

- LinkedList 클래스 변경
  - Node 클래스를 LinkedList의 static nested class 로 옮긴다.
  
### ListIterator 클래스를 중첩 클래스로 전환

- AbstractList 클래스 변경
  - ListIterator 클래스를 이 클래스의 static nested class로 옮긴다.
    - AbstractList01 클래스 참고
  - ListIterator 클래스를 non-static nested class로 변경한다.
    - AbstractList02 클래스 참고
  - ListIterator 클래스를 local class로 변경한다.
    - AbstractList03 클래스 참고
  - ListIterator 클래스를 anonymous class로 변경한다.
    - AbstractList 클래스 참고
    
## 소스 파일

- Node.java (삭제)
- LinkedList.java
- ListIterator.java(삭제)
- AbstractList.java
  - AbstractList01.java
  - AbstractList02.java
  - AbstractList03.java
