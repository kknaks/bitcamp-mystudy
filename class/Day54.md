# Foreign key
## Foreign key의 개념
- **Foreign Key(외래 키)** 는 데이터베이스에서 한 테이블의 필드(또는 컬럼)가 다른 테이블의 기본 키(Primary Key)를 참조하는 키를 말한다.
- 데이터베이스의 테이블 간 관계를 설정하는 데 사용되며, 데이터의 무결성을 유지하는 데 핅요하다.

### 외래 키의 주요 특징
1. **참조 무결성**: 외래 키는 한 테이블의 필드가 다른 테이블의 기본 키와 일치하도록 강제한다.
2. **연결 관계**: 외래 키는 테이블 간의 관계를 정의한다.
3. **제약 조건**: 외래 키는 일반적으로 제약 조건으로 설정되며, 이 제약 조건은 테이블 간의 참조 관계를 강제한다.
4. **ON DELETE / ON UPDATE 동작**: 외래 키는 기본 테이블에서 데이터가 삭제되거나 업데이트될 때의 동작을 지정할 수 있다.

### Referential Intergrity(참조 무결성)
- 데이터베이스에서 외래 키 관계가 올바르게 유지되도록 보장한다.
- myApp에서 User 정보가 삭제되면 Project의 meeber도 삭제된다. -> 참조무결성 위반

## Foreign key 적용
1. 테이블의 데이터 관계를 파악
   ![image](https://github.com/user-attachments/assets/677a122a-b2fe-4223-b83b-25b9adb7cae8)
- procject는 user와 1대 다 관계를 맺고 있다.
- 1대다/다대다의 관계를 직접적으로 연결하면, 무효한 데이터 입출력을 막을 수 없다.
- 외부키와 별도의 테이들을 만들어서 관리해야 데이터의 무결성을 유지할 수 있다.

2. 다대다 관계에서 중간 테이블 만들기
   ![image](https://github.com/user-attachments/assets/c5681a20-d3a9-4777-9031-a6c65c3ee5c2)
- 다대다 관계에서 직접적인 연결을 제거하고, 중간에 별도의 테이블로 관리한다.
- 별도의 테이블(myapp_members)에 외부 키를 적용하면, project_id가 user_id에 연결되어 무효한 데이터의 입출력을 막을 수 있다.

## Foreign key 사용
- 게시판에 첨부파일 올리는 구조로 Foreign key 적용해보기 
### 게시글과 첨부파일을 같이 올리는 DB구축
- RBMS에 게시글 및 첨부파일 올리기
```mysql
create table test1(
  no int primary key auto_increment,
  title varchar(255) not null,
  content text,
  rdt datetime default now(),
  filepath1 varchar(255),
  filepath2 varchar(255),
  filepath3 varchar(255),
  filepath4 varchar(255),
  filepath5 varchar(255)
);
```
- DB구조
  <img width="800" alt="image" src="https://github.com/user-attachments/assets/c4189b50-46d9-4d4a-b7c6-14002385a45f">

  
- 문제점 
1. 첨부파일을 최대 5개 까지만 올릴수 있다.
2. 첨부파일이 5개가 안되는 게시글도 무조건 5개 파일 경로를 차지하게 된다. 
3. 파일 입출력으로 인한 속도 저하

### 게시글과 첨부파일을 이원화하여 관리하는 DB변경
```mysql
/* 게시판 테이블 */
create table test1(
  no int not null primary key auto_increment,
  title varchar(255) not null,
  content text,
  rdt datetime not null default now()
);

/* 첨부 파일 테이블 */
create table test2(
  fno int not null primary key auto_increment, /* 첨부파일 고유번호 */
  filepath varchar(255) not null, /* 파일시스템에 저장된 첨부파일의 경로 */
  bno int not null /* 게시글 번호 */
);
```
- DB구조
  <img width="838" alt="image" src="https://github.com/user-attachments/assets/47f4d488-a992-4b6f-b7f5-173e2d5c346f">

- 문제점
1. 데이터 무결성 에러(파일관리시스템과 게시글의 연결성이 없다.)

### 외부키 적용하기
- 다른 데이터를 참조하는 경우 해당 데이터의 존재 유무를 검사하도록 강제한다.
- 데이터를 삭제하는 경우 다른 테이터에 의해 참조되는지 여부를 검사하도록 강제한다.
```mysql
alter table 테이블명
    add constraint 제약조건이름 foreign key (컬럼명) references 테이블명(컬럼명);

alter table test2
   add constraint test2_bno_fk foreign key (bno) references test1(no);
```

<img width="679" alt="image" src="https://github.com/user-attachments/assets/bace4f9a-b46e-41ee-8894-ca5ae0db2504">

# SQL 기본문법(추가)
## select column
### 컬럼 가져오기
```mysql
select all 컬럼명 from 테이블명;

-- all은 생략 가능하다.
select 컬럼명 from 테이블명;
```
### 중복제외하기
```mysql
select distinct 컬럼명 from 테이블명;
```

## Order by
### 오름차순으로 불러오기 
```mysql
select 컬럼명 from 테이블명 order by 정렬할_컬럼명 asc;

-- 기본이 오름차순으로 asc(ascending)은 생략가능하다.
select 컬럼명 from 테이블명 order by 정렬할_컬럼명;
```
### 내림차순으로 불러오기
```mysql
select 컬럼명 from 테이블명 order by 정렬할_컬럼명 desc;
```

## As 별명
### 별명설정하기
```mysql
select 컬럼명 as 별명, 컬럼명 as 별명 from 테이블명 order by 정렬할_컬럼명;

-- as 는 생략가능하다
select 컬럼명 별명, 컬럼명 별명 ..... from 테이블명 order by 정렬할_컬럼명;

-- 별명에 공백을 삽입하려면 ''으로 묶어서 표기한다.
select 컬럼명 '별  명'.....from 테이블명 order by 정렬할_컬럼명;
```

## count()
### null아닌 데이터 갯수 세기
```mysql
select count(컬럼명) 별명 from 테이블명;
```

## 집합관계
### union all
- 중복 포함하여 데이터 합치기 
```mysql
select distinct 컬럼명 from 테이블명
union all 
select distinct 컬럼명 from 테이블명
```

- 중복 제외하여 데이터 합치기
```mysql
select distinct 컬럼명 from 테이블명
union 
select distinct 컬럼명 from 테이블명
```

### 차집합
- MySQL은 차집합을 지원하지 않는다. 
```mysql
select distinct 컬럼명 from 테이블명
where 컬럼명 not in (select distinct 컬럼명 from 테이블명2);
```
### 교집합
- MySQL은 교집합을 지원하지 않는다.
```mysql
select distinct 컬럼명 from 테이블명
where 컬럼명 in (select distinct 컬럼명 from 테이블명2);
```
