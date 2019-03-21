insert into account (id,email,name,nick_name,passwd,reg_date)
values(1,'jaehee@gmail.com','박재희','jj','{bcrypt}$2a$10$o3eFgx5gkg0UPctYnvje7OMSTKhfZJ9H2.X/60UKxH382LeCIcHJ.',NOW());

insert into account (id,email,name,nick_name,passwd,reg_date)
values(2,'treasureb1330@gmail.com','최원오','choiwono2','{bcrypt}$2a$10$o3eFgx5gkg0UPctYnvje7OMSTKhfZJ9H2.X/60UKxH382LeCIcHJ.',NOW());

insert into account (id,email,name,nick_name,passwd,reg_date)
values(3,'admin','최원오','choiwono2','{bcrypt}$2a$10$o3eFgx5gkg0UPctYnvje7OMSTKhfZJ9H2.X/60UKxH382LeCIcHJ.',NOW());

insert into account (id,email,name,nick_name,passwd,reg_date)
values(4,'jaehee1@gmail.com','박재희','jaehee','{bcrypt}$2a$10$o3eFgx5gkg0UPctYnvje7OMSTKhfZJ9H2.X/60UKxH382LeCIcHJ.',NOW());

insert into account_theme (id,color_name,link_option,list_option,background_id,reg_date,account_id)
values(1,'black',0,0,1,NOW(),1);

insert into account_theme (id,color_name,link_option,list_option,background_id,reg_date,account_id)
values(2,'black',0,0,1,NOW(),2);

insert into account_theme (id,color_name,link_option,list_option,background_id,reg_date,account_id)
values(3,'black',0,0,1,NOW(),3);

insert into account_theme (id,color_name,link_option,list_option,background_id,reg_date,account_id)
values(4,'black',0,0,1,NOW(),4);

insert into roles (id,name,reg_date)
values(1,'ADMIN',NOW());

insert into roles (id,name,reg_date)
values(2,'USER',NOW());

insert into account_roles (account_id,role_id)
values(1,2);

insert into account_roles (account_id,role_id)
values(2,2);

insert into account_roles (account_id,role_id)
values(3,1);

INSERT INTO category (id,category_name,reg_date,ordering,account_id)
Values(1,'백엔드',NOW(),1,1);

INSERT INTO category (id,category_name,reg_date,ordering,account_id)
Values(2,'프론트',NOW(),2,2);

INSERT INTO link(id,board_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(1,1,'jaehee@gmail.com',NOW(),0,'[JAVA] 변수', 'https://jaehee0145.github.io/java/JAVA-%EB%B3%80%EC%88%98/',1,1);
INSERT INTO link(id,board_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(2,2,'jaehee@gmail.com',NOW(),0,'[JAVA] 자바 시작하기', 'https://jaehee0145.github.io/java/',1,1);
INSERT INTO link(id,board_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(3,3,'jaehee@gmail.com',NOW(),0,'[Programming] 객체 지향 프로그래밍이란?', 'https://jaehee0145.github.io/programming/',1,1);
INSERT INTO link(id,board_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(4,4,'treasureb1330@gmail.com',NOW(),1,'[기술면접] HTTP란?', 'https://choiwono.github.io/http/',1,1);

INSERT INTO message_option(id,opt_type,reg_date,type_id)
VALUES(1,1,NOW(),1);

INSERT INTO message(id,receive_id,use_yn,reg_date,account_id,options_id)
VALUES(1,'treasureb1330@gmail.com','YES',NOW(),1,1);

INSERT INTO tag(id, account_id, tag_name,options,reg_date)
VALUES (1,1,'JAVA',0,NOW());
INSERT INTO tag(id, account_id, tag_name,options,reg_date)
VALUES (2,1,'variables',0,NOW());
INSERT INTO tag(id, account_id, tag_name,options,reg_date)
VALUES (3,2,'network',0,NOW());

INSERT INTO tag_mapping(tag_id, board_id)
VALUES (1, 1);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (1, 2);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (2, 1);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (3, 4);

