# 1. 실습프로젝트 
## 1.1 properties 구현 <br>
### properties 파일 만들기 <br>
### properties 적용하기 <br>

## 1.2 로그인 기능 구현하기 <br>
### 로그인 기능구현 <br>

## 1.3 게시글에 사용자 정보 추가하기 <br>
### myapp_boards 테이블 변경 및 예제 데이터 변경 <br>
### Board 클래스 변경 <br>
### BoardDaoImpl 클래스 변경 <br>
### BoardCommand 변경 <br>
- BoardListCommand 클래스 변경
- BoardViewCommand 클래스 변경 
- BoardAddCommand 클래스 변경 
- BoardUpdateCommand 클래스 변경 
- BoardDeleteCommand 클래스 변경

# 2. Java프로그래밍 기초(JDBC) <br>
## 2.1 JDBC 드라이버 준비
- JDBC 드라이버 구조는 다음 그림과 같다.
  ![image](https://github.com/user-attachments/assets/fa50e547-0d5f-4cca-a902-d5ad59e32c6b)
- Library에 다운로드 하여 받아야 한다.
- gradle.build에 해당 라이브러리 추가
    ```java
    //Oracle JDBC Driver
    implementation 'com.oracle.database.jdbc:ojdbc11-production:21.15.0.0'
    
    //MS-SQL JDBC Driver
    implementation 'com.microsoft.sqlserver:mssql-jdbc:12.8.0.jre11'
    
    //MySQL JDBC Driver
    implementation 'com.mysql:mysql-connector-j:8.4.0'
    
    //Maria JDBC Driver
    implementation 'org.mariadb.jdbc:mariadb-java-client:3.4.1'
    ```
- gradle.build 업데이트 
    - Intelli j : IDE에서 업데이트 진행 
    - esclipse : 터미널에서 cd root_project로 이동후 ``` $> gradle esclipse 진행``` <br>

## 2.2 JDBC 드라이버 등록

### DriverManager.registerDriver <br>

### new jdbc.Driver경로 <br>

### Class.forName("jdbc.Driver경로") <br>

### propreties <br>

### service-provider loading <br>

## 2.3 DBMS에 연결하기

1) DBMS 연결구조

2) DBMS close
