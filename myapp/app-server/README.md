# 56. 비즈니스 로직 분리하기 - 서비스 컴포넌트 도입

## 학습목표

- 서비스 컴포넌트의 역할을 이해하고 구현할 수 있다.
- 인터페이스와 구현을 분리하는 이유를 설명할 수 있다.

## 요구사항

- 서블릿 클래스에서 비즈니스 로직을 분리하라.

## 실행 결과

- 이전과 같다.

## 작업

- 서비스 컴포넌트 추가
  - BoardService 인터페이스와 DefaultBoardService 구현체 생성
  - UserService 인터페이스와 DefaultUserService 구현체 생성
  - ProjectService 인터페이스와 DefaultProjectService 구현체 생성
- 서블릿 클래스 변경
  - DAO 대신 서비스 컴포넌트를 사용하도록 변경

## 소스 파일

