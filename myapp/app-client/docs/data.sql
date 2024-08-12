-- 회원 데이터
insert into myapp_users(user_id,name,email,pwd) values
(1,"user1","user1@test.com",sha1("1111")),
(2,"user2","user2@test.com",sha1("1111")),
(3,"user3","user3@test.com",sha1("1111")),
(4,"user4","user4@test.com",sha1("1111")),
(5,"user5","user5@test.com",sha1("1111")),
(6,"user6","user6@test.com",sha1("1111")),
(7,"user7","user7@test.com",sha1("1111"));


insert into myapp_boards(board_id,title,content) values
    (1,'제목1','내용1'),
     (2,'제목2','내용1'),
     (3,'제목3','내용1'),
     (4,'제목4','내용1'),
     (5,'제목5','내용1'),
     (6,'제목6','내용1'),
     (7,'제목7','내용1');

insert into myapp_projects(project_id,title,description,start_date, end_date, members) values
(101,'프로젝트1','설명','2024-1-1','2024-2-15','1,2,5'),
(102,'프로젝트2','설명','2024-2-1','2024-3-15','5,6,9'),
(103,'프로젝트3','설명','2024-3-1','2024-4-15','4,7,9');
