drop table myapp_users;
drop table myapp_boards;
drop table myapp_projects;

create table myapp_users(
   user_id int not null,
   name varchar(20) not null,
   email varchar(100) not null,
   pwd varchar(100) not null,
   tel varchar(20)
);

alter table myapp_users
add constraint primary key (user_id),
modify column user_id int not null auto_increment,
add constraint myapp_users_uk unique(email);

create table myapp_boards(
board_id int not null,
title varchar(255) not null,
content text not null,
created_date datetime default now(),
view_count int default 0
    );

alter table myapp_boards
add constraint primary key (board_id),
modify column board_id int not null auto_increment;


create table myapp_projects(
    project_id int not null,
    title varchar(255) not null,
    description text not null,
    start_date date not null,
    end_date date not null,
    members varchar(20)
);

alter table myapp_projects
add constraint primary key (project_id),
modify column project_id int not null auto_increment;
