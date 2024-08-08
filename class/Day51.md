# MySQL
## MySQL 시작하기
### MySQL 서버에 접속하기 
  - 로컬 MySQL에 접속하기
    > mysql -u root -p 
    
  -  원격 MySQL에 접속하기
        > mysql -h 서버주소 -u root -p

### MySQL 설정하기
  - mysql root 암호변경
    > alter user 'root'@'localhost' identified by '비밀번호'

  -  MySQL 사용자 추가
     > CREATE USER '사용자아이디'@'서버주소' IDENTIFIED BY '암호';
    
  - 로컬에서만 접속할 수 있는 사용자를 만들기:
      > CREATE USER 'study'@'localhost' IDENTIFIED BY '비밀번호';
  
    - 이 경우 stidu 사용자는 오직 로컬(서버를 실행하는 컴퓨터)에서만 접속 가능한다.
    - 다른 컴퓨터에서 실행하는 MySQL 서버에 접속할 수 없다는 것을 의미한다.
    
  - 원격에서만 접속할 수 있는 사용자를 만들기:
    > CREATE USER 'study'@'%' IDENTIFIED BY '1111';
    - 이 경우 study 사용자는 원격에서만 접속 가능하다.
    - 
### MySQL 조회하기
  - MySQL 사용자 목록 조회
    > select user from 데이터베이스명.테이블명;" <br>
    select user from mysql.user;
    
  - MySQL 데이터베이스 생성
    > CREATE DATABASE 데이터베이스명<br>
    DEFAULT CHARACTER SET utf8<br>
    DEFAULT COLLATE utf8_general_ci;<br><br>
    CREATE DATABASE studydb /* 인코딩 방식*/<br>
    DEFAULT CHARACTER SET utf8 /* 정렬 및 비교 방식*/<br>
    DEFAULT COLLATE utf8_general_ci;
    
    - MySQL 사용자에게 데이터베이스 사용 권한 부여
    > GRANT ALL ON 데이터베이스명.* TO '사용자아이디'@'서버주소';<br>
    GRANT ALL ON studydb.* TO 'study'@'localhost';
    
  - 데이터베이스 목록 조회
    > show databases;
    
  - 사용자 교체
    > quit    (프로그램 종료 후)<br>
    mysql -u study -p   (다시 실행)
    
  - 기본으로 사용할 데이터베이스 지정하기
    > use 데이터베이스명<br>
    use studydb;
    
  - 데이터베이스의 전체 테이블 목록 조회
    > show tables;
  
## DDL (Data Definition Language)
- **기능** : 데이터베이스 구조를 정의하고 수정
- **종류**
    - DB 객체(테이블, 뷰, 프로시저, 함수, 트리거) 등을 생성, 변경, 삭제하는 SQL 명령이다.
  - 데이터베이스(database) = 스키마(schema)
  - 테이블(table)
  - 뷰(view)
  - 트리거(trigger=listener)
      - 특정 조건에서 자동으로 호출되는 함수
      - 특정 조건? SQL 실행 전/후 등
      - OOP 디자인 패턴에서 옵저버에 해당한다.
  - 함수(function)
  - 프로시저(procedure)
  - 인덱스(index)
### 테이블 생성
  - 데이터베이스에 사용 할 테이블을 생성한다.
    > create table 테이블명(<br>
      컬럼명 타입 NULL여부 옵션,<br>
      컬럼명 타입 NULL여부 옵션,<br>
      컬럼명 타입 NULL여부 옵션,<br>
      컬럼명 타입 NULL여부 옵션<br>
        );

  - 테이블 정보 보기 
    > describe 테이블명; <br>
    desc 테이블명;
  
    > 예제<br>
    <img width="814" alt="image" src="https://github.com/user-attachments/assets/9bfbc4fe-8a07-4275-a798-b4aa92d50e50">

  - 테이블 삭제 하기
    > drop table 테이블명;

### 테이블 옵션
- null 허용을 하면 데이터를 입력하지 않아도 된다.
- not null 옵션을 주면, insert into 시에 해당 컬럼을 생략 할 수 없다.
  ```java
  데이터를 입력하지 않아도 된다.
  create table test1 (
  no int,
  name varchar(20)
  );
    
  데이터 입력 테스트:
  insert into test1(no, name) values(1, 'aaa');
  insert into test1(no, name) values(null, 'bbb');
  insert into test1(no, name) values(3, null);
  insert into test1(no, name) values(null, null);
  select * from test1;
  ```

  <img width="847" alt="image" src="https://github.com/user-attachments/assets/93a9d33c-ceae-44e7-a479-89fc1d7484e9">

- default 옵션은 입력값을 **생략**하면 자동으로 지정되는 값이다.
- 값을 입력시에 null로 입력값을 주면 NULL값이 들어간다
  ```java
  // 입력 값을 생략하면 해당 컬럼에 지정된 기본값이 대신 입력된다.
  create table test1(
  no int not null,
  name varchar(20) default 'noname',
  age int default 20
  );
    
  insert into test1(no, name, age) values(1, 'aaa', 30);
  // 컬럼 값을 null로 지정하면 기본 값이 사용되지 않는다.
  insert into test1(no, age, name) values(6, null, null);
    
  // 값을 입력하지 않는 컬럼은 이름과 값 지정을 생략한다.
  insert into test1(name, age) values('aaa', 30); /* 오류! no는 not null*/
    
  // 컬럼에 default 값이 설정된 경우, 컬럼 값의 입력을 생략하면 기본값이 사용된다.
  insert into test1(no) values(5);
    
  ```
  <img width="847" alt="image" src="https://github.com/user-attachments/assets/8661bcc6-1a7c-4e91-abd9-1b023832d77b">

### 컬럼 타입
#### int
- 4바이트 크기의 정수 값 저장
- 기타 tinyint(1바이트), smallint(2바이트), mediumint(3바이트), bigint(8바이트)

#### float
- 부동소수점 저장

#### numeric = decimal
- 전체 자릿수와 소수점 이하의 자릿수를 정밀하게 지정할 수 있다.
- numeric(n,e) : 전체 n 자릿수 중에서 소수점은 e 자릿수다.
    - 예) numeric(10,2) : 12345678.12
- numeric : numeric(10, 0) 과 같다.

#### char(n)
- 최대 n개의 문자를 저장.
- 0 <= n <= 255
- 고정 크기를 갖는다.
- 한 문자를 저장하더라도 n자를 저장할 크기를 사용한다.
- 메모리 크기가 고정되어서 검색할 때 빠르다.

#### varchar(n)
- 최대 n개의 문자를 저장.
- 0 ~ 65535 바이트 크기를 갖는다.
- n 값은 문자집합에 따라 최대 값이 다르다.
- 한 문자에 1바이트를 사용하는 ISO-8859-n 문자집한인 경우 최대 65535 이다.
- 그러나 UTF-8로 지정된 경우는, n은 최대 21844까지 지정할 수 있다.
- 가변 크기를 갖는다.
- 한 문자를 저장하면 한 문자 만큼 크기의 메모리를 차지한다.
- 메모리 크기가 가변적이라서 데이터 위치를 찾을 때 시간이 오래 걸린다.
  그래서 검색할 때 위치를 계산해야 하기 때문에 검색 시 느리다.

#### text(65535), mediumtext(약 1.6MB), longtext(약 4GB)
- 긴 텍스트를 저장할 때 사용하는 컬럼 타입이다.
- 오라클의 경우 long 타입(2GB)과 CLOB(character large object) 타입(4GB)이 있다.

##### date
- 날짜 정보를 저장할 때 사용한다.
- 년,월,일 정보를 저장한다.
- 오라클의 경우 날짜 뿐만 아니라 시간 정보도 저장한다.

##### time
- 시간 정보를 저장할 때 사용한다.
  - 시, 분, 초 정보를 저장한다.

##### datetime
- 날짜와 시간 정보를 함께 저장할 때 사용한다.

### key column : 데이터를 구분하는 기준

- **key** : 데이터를 구분 할 수 있는 컬럼들의 집합
  - ex) {email}, {jumin}, {id}, {name, tel}, {tel, basic_addr, gender, name} <br>
        <span>{name, jumin}, {email, id}, {id, name, email} ...

- **Candidate key** : 키 중에서 최소 항목으로 줄인 키
  - ex) {jumin}, {email}, {id}, {name, tel}

- **Primary key** : Candidate key 중에서 컬럼의 데이터를 구분하는 키
    - ex) {jumin}

- **alternate key** : Primary key가 되지 못한 Candidate key
    - ex) {email}, {id}, {name, tel}  

- **artificial key** : (인공키; surrogate key(대리키)))
  - Primary key로 사용하기에 적절한 컬럼을 찾을 수 없는 경우 새로운 구분자를 Key로 column에 추가한다.
      - ex) 게시글 : 제목, 내용, 작성자, 등록일, 조회수
  - 보통 일련번호를 저장할 정수 타입의 컬럼을 추가한다.
      - ex) 게시글 : 게시글 번호

### PK(primary key) 적용
- pk를 적용하기 위해서는 옵션에 primary key라고 선언한다.
    ```java
    create table test1(
    -> no int primary key,
    -> name varchar(20),
    -> age int,
    -> kor int);
    
    mysql> insert into test1(no,name,age,kor) values(1,'a',10,10);
    mysql> insert into test1(no,name,age,kor) values(2,'b',10,10);
    // mysql> insert into test1(no,name,age,kor) values(1,'c',10,10); 실행오류
    
    // 이름과 나이 국어 성적이 동일하지만 키가 다르면 다른 데이터로 인식한다.
    // 이는 데이터의 중복성을 가중시킨다.
    mysql> insert into test1(no,name,age,kor) values(3,'a',10,10);
    Query OK, 1 row affected (0.01 sec)
    
    ```
    <img width="847" alt="image" src="https://github.com/user-attachments/assets/38b32506-c897-4672-b8f8-ced8382527d3">

- pk가 그룹으로 지정되어야 한다면, constraint 제약조건이름 primary key (컬럼명, 컬럼명, ...) 으로 선언한다.
  ```java
  create table test1(
  -> no int,
  -> name varchar(20),
  -> age int,
  -> kor int
  -> constraint primary key (name,age));
    ```
  <img width="847" alt="image" src="https://github.com/user-attachments/assets/64581b70-8542-42bf-9063-bbe7dc64fdb4">

### uk(unique key) 적용
- primary key 만 적용하면 다른 칼럼들의 중복을 막을 수 없다. 
- primay key는 아니지만 다른 값들이 중복되면 안된느 컬럼을 지정 할 때 사용한다. 
  ```java
  create table test1(
    no int,
    name varchar(20),
    age int,
    kor int,
    eng int,
    math int,
    constraint primary key(no),
    constraint test1_uk unique (name, age)
    );

  insert into test1(no,name,age,kor,eng,math) values(1,'a',10,90,90,90);
  insert into test1(no,name,age,kor,eng,math) values(2,'a',11,91,91,91);
  insert into test1(no,name,age,kor,eng,math) values(3,'b',11,81,81,81);
  insert into test1(no,name,age,kor,eng,math) values(4,'c',20,81,81,81);
    
    /* 번호가 중복되었기 때문에 입력 거절 */
    //insert into test1(no,name,age,kor,eng,math) values(4,'d',21,81,81,81);
    
    /* 비록 번호가 중복되지 않더라도 name, age가 unique 컬럼으로 지정되었기
    때문에 중복저장될 수 없다.*/
    //insert into test1(no,name,age,kor,eng,math) values(5,'c',20,81,81,81);
   ```
  <img width="847" alt="image" src="https://github.com/user-attachments/assets/7460f70d-fecb-496f-83b7-bc73a02f277c">

### index 적용
 - 검색 조건으로 사용되는 컬럼인 경우 따로 정렬 해 두면 데이터를 빨리 찾을 수 있다.
 - index를 설정하여 별도의 테이블로 관리하는 것이 데이터 조회에 유리하다.
 - 인덱스로 지정된 컬럼의 값이 추가/변경/삭제 될 때 인덱스 정보도 갱신한다.
 - 입력/변경/삭제 시에는 속도가 느려지지만, 조회에서는 속도가 빠르다.
    ```java
    create table test1(
    no int ,
    name varchar(20),
    age int,
    score int,
    constraint primary key(no),
    constraint test1_uk unique (name, age),
    fulltext index test1_name_idx (name)
    );
    
    insert into test1(no,name,age,kor,eng,math) values(1,'aaa',20,80,80,80);
    insert into test1(no,name,age,kor,eng,math) values(2,'bbb',21,90,80,80);
    insert into test1(no,name,age,kor,eng,math) values(3,'ccc',20,80,80,80);
    insert into test1(no,name,age,kor,eng,math) values(4,'ddd',22,90,80,80);
   ```
   <img width="847" alt="image" src="https://github.com/user-attachments/assets/28bf6d9f-20a1-44fe-bdf0-12352ec9ac0c">

### 컬럽 값 자동 증가
- 숫자 타입의 PK 컬럼 또는 Unique 컬럼인 경우 값을 1씩 자동 증가시킬 수 있다.
- 삭제를 통해 중간에 비어있는 번호는 다시 채우지 않는다.
- 한 테이블 당 하나만 지정 가능하다.
  <img width="847" alt="image" src="https://github.com/user-attachments/assets/59167873-31e3-481e-840c-c074798a4d55">


### 테이블 변경

#### 테이블 컬럼 추가
 ```java
  alter table 테이블명
    add column 컬럼명 변수타입;  
 ```

#### pk,uk,idx 지정
 ```java
  alter table 테이블명
    add constraint 키이름 primary key(컬럼명),  // 키이름 생략 가능
    add constraint 키이름 unique(컬럼명),       // 키이름 생략 가능
    add fulltext index 인덱스이름(컬럼명);       //문자열만 인덱스 가능
 ```
#### 옵션 변경
 ```java
 alter table 테이블명
    modify column 컬럼명 변수명 (not null/null),
    modify column 컬럼명 int not null auto_increment;
 ```
