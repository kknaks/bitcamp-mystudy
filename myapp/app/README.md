# 31. File I/O API 활용하기 IV :  데이터를 CSV 형식의 텍스트로 입출력

## 학습목표

- CSV(comma-seperated values) 이해하고 설명할 수 있다.
- File I/O API의 데코레이터 클래스를 사용하여 텍스트를 입출력 할 수 있다.

## 요구사항

- File I/O API의 데코레이터를 사용하여 객체를 텍스트로 변환하여 입출력 하기

## 실행 결과

- 이전과 같다.

## 작업

- User, Project, Board 클래스 변경
  - 필드 값을 CSV 형식으로 리턴 : toCsvString() 추가
  - CSV 형식의 문자열을 가지고 객체 생성: valueOf() 추가
- 데이터 로딩 및 저장 코드 변경
  - loadUsers(), saveUsers() 메서드 변경
  - loadProjects(), saveProjects() 메서드 변경
  - loadBoards(), saveBoards() 메서드 변경
  
## 소스 파일

- App.java
- Board.java
- Project.java
- User.java