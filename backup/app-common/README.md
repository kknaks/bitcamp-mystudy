# 36. 애플리케이션 시작/종료 상태일 때 알림 받기 : GoF의 Observer 패턴 적용

## 학습목표

- GoF의 Observer 설계 패턴을 이해하고 적용할 수 있다.

## 요구사항

- 애플리케이션이 시작되거나 종료될 때 초기화 작업이나 자원 해제 작업을 할 수 있도록 설계를 개선하라.

## 실행 결과

- 이전과 같다.

## 작업

- Observer 객체의 사용 규칙을 정의
  - ApplicationListener 인터페이스 추가
- Observer 객체 구현
  - InitApplicationListener 클래스 추가
- 애플리케이션 환경 정보를 다룰 객체 정의 
  - ApplicationContext 클래스 추가
- App 클래스 변경
  - Observer를 등록하고 실행하도록 변경
  
## 소스 파일

- App.java
- ApplicationListener.java
- InitApplicationListener.java
- ApplicationContext.java