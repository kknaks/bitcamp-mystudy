# SQL 기본문법(join)
## join
1) cross join

2) natural join
- 같은 이름을 가진 컬럼 값을 기준으로 레코드를 연결한다.
```mysql
select 컬럼명
from 테이블명
    natural join 테이블명2
```
- 문제점
  - 같은 컬럼명이 없을 경우 연결되지 못한다.
  - 다른 의미의 컬럼들이 컬럼명이 같다는 이유로 잘못 연결된다.
  - 컬럼명이 동일한 컬럼이 여러개 있을 경우 잘못 연결된다.

3) join ~ using
- 같은 이름을 가진 컬럼이 여러 개 있을 경우 USING을 사용하여 컬럼을 명시할 수 있다.
```mysql
select 컬럼명
from 테이블명
join 테이블명2
using (동일한 이름의 컬럼명)
```
- 문제점
    - 같은 컬럼명이 없을 경우 연결되지 못한다.

4) inner join
- 조인 조건을 on에 명시하여 다른 컬럼명이더라도 같은 내용이면 연결한다.
- inner를 생략하여 사용한다. 
```mysql
select 컬럼명
from 테이블명
inner join 테이블명2
on (테이블명.컬럼명 = 테이블명2.컬럼명2)
/*on에 조건을 입력한다.*/

-- inner를 생략 할 수 있다.
select 컬럼명
from 테이블명
join 테이블명2
on (테이블명.컬럼명 = 테이블명2.컬럼명2)
```
- 문제점
    - 반드시 on 에서 지정한 컬럼의 값이 같을 경우에만 테이블의 데이터가 연결된다.
    - 같은 값을 갖는 데이터가 없다면 연결되지 않고, 결과로 출력되지 않는다.
    - 데이터가 누락될 위험이 있다.

5) outer join
- 조인 조건에 일치하는 데이터가 없더라도 두 테이블 중에서 한 테이블의 데이터를 결과로 포함시키는 명령이다.
```mysql
select 컬럼명
from 테이블명
[left|right] outer join 테이블명2
on 조건
```

# myapp에 외래키 적용하기
## 다대다 관계에서 중간 테이블 만들기
![image](https://github.com/user-attachments/assets/c5681a20-d3a9-4777-9031-a6c65c3ee5c2)
- 다대다 관계에서 직접적인 연결을 제거하고, 중간에 별도의 테이블로 관리한다.
- 별도의 테이블(myapp_members)에 외부 키를 적용하면, project_id가 user_id에 연결되어 무효한 데이터의 입출력을 막을 수 있다.

## MySQL Data 구조 수정
- myapp_projects에서 userNo를 제거하고
- myapp_project_members의 테이블을 생성하여 userNo를 관리한다.
```mysql
create table myapp_project_members (
user_id int not null,
project_id int not null
);

alter table myapp_project_members
    add constraint myapp_project_members_fk1 foreign key (user_id) references myapp_users (user_id),
    add constraint myapp_project_members_fk2 foreign key (project_id) references myapp_projects (project_id),
    add constraint primary key (user_id, project_id);
```
## projectDaoImpl 구조 수정
### insert 수정
- member를 가져오는 부분을 삭제한다.
```java
String memberNoList = project.getMembers().stream()
          .map(user -> String.valueOf(user.getNo())) 
          .collect(Collectors.joining(","));
```
- RETURN_GENERATED_KEYS 생성
  - projectNo를 받기위해서 PK가 필요하다.
  - Statement.executeUpdate()의 오버로딩으로 Statement.RETURN_GENERATED_KEYS가 있다.
  - statement에서 Pk를 리턴받고 변수에 할당을 한 다음 project객체에 대입한다.
```java
try (Statement stmt = con.createStatement()) {
      stmt.executeUpdate(sql쿼리문, Statement.RETURN_GENERATED_KEYS);
      ResultSet keyRS = stmt.getGeneratedKeys();
      keyRS.next();
      int projectNo = keyRS.getInt(1);
      project.setNo(projectNo);
    }
```
### findby 수정
- member를 가져오는 부분을 삭제한다.
```java
String members = rs.getString("members");
        String[] memberNoList = members.split(",");
        for (String memberNo : memberNoList) {
          // 회원 번호를 User 객체를 얻어서 리스트에 담는다.
          User user = userDao.findBy(Integer.parseInt(memberNo));
          if (user != null) {
            project.getMembers().add(user);
          }
        }
```
### updatate 수정
- member를 가져오는 부분을 삭제한다.

### insertMembers, getMembers, deleteMembers 추가
- myapp_project_members를 쿼리문을 통해 데이터를 입력한다.
<details>
<summary>코드 보기/숨기기</summary>

```java

  @Override
  public boolean insertMembers(int projectNo, List<User> members) throws Exception {
    try (Statement stmt = con.createStatement()) {
      for (User user : members) {

        stmt.executeUpdate(String.format(
            "insert into myapp_project_members('user_id','project_id')" +
                "values (%d,%d)",
            projectNo,
            user.getNo()
        ));
      }
      return true;
    }
  }

  @Override
  public List<User> getMembers(int projectNo) throws Exception {
    try (Statement stmt = con.createStatement()) {
      ResultSet rs = stmt.executeQuery(
          "select pm.user_id, u.name" +
              " from myapp_project_members pm" +
              " join myapp_users u" +
              " on pm.user_id = u.user_id" +
              " where pm.project_id = " + projectNo
      );
      List<User> list = new ArrayList<>();
      while (rs.next()) {
        User user = new User();
        user.setNo(rs.getInt("user_id"));
        user.setName(rs.getString("name"));
        list.add(user);
      }
      return list;
    }
  }

  @Override
  public boolean deleteMembers(int projectNo) throws Exception {
    try (Statement stmt = con.createStatement()) {
      stmt.executeUpdate("delete from myapp_project_members where project_id = " + projectNo);
    }
    return false;
  }
```
</details>

## projectCommand 수정

### projectAddCommand
- ```projectDao.insertMembers(project.getNo(), project.getMembers());``` 추가

### projectDeleteCommand 
- ```projectDao.deleteMembers(projectNo);``` 추가

### projectUpdateCommand
  - ```projectDao.deleteMembers(projectNo);``` 추가
  - ```projectDao.insertMembers(projectNo, project.getMembers()); ``` 추가

### projectViewCommand
- ```List<User> members = projectDao.getMembers(projectNo);```추가
