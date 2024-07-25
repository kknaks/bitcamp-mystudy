# 33. Apache POI 라이브러리 활용하기 : 데이터를 엑셀 포맷의 파일로 입출력

## 학습목표

- Apache POI 라이브러리를 이용하여 엑셀 포맷의 파일을 다룰 수 있다.

## 요구사항

- 데이터를 엑셀 포맷으로 파일에 저장하고 읽기

## 실행 결과

- 이전과 같다.

## 작업

- Google gson 라이브러리 제거
  - build.gradle 변경
- 데이터 로딩 및 저장 코드 변경
  - loadData(), saveData() 메서드 변경
  - loadUsers(), saveUsers() 메서드 추가
  - loadProjects(), saveProjects() 메서드 추가
  - loadBoards(), saveBoards() 메서드 추가
- User, Project, Board 클래스 변경
  - valueOf(), toCsvString() 메서드 제거
- App 클래스에 적용
 
## 소스 파일

- App.java
- Board.java
- Project.java
- User.java