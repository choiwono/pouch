insert into account (id,email,name,nick_name,passwd,reg_date)
values(1,'jaehee@gmail.com','박재희','jj','{bcrypt}$2a$10$o3eFgx5gkg0UPctYnvje7OMSTKhfZJ9H2.X/60UKxH382LeCIcHJ.',NOW());

insert into account (id,email,name,nick_name,passwd,reg_date)
values(2,'treasureb1330@gmail.com','최원오','choiwono2','{bcrypt}$2a$10$o3eFgx5gkg0UPctYnvje7OMSTKhfZJ9H2.X/60UKxH382LeCIcHJ.',NOW());

insert into account (id,email,name,nick_name,passwd,reg_date)
values(3,'admin','최원오','choiwono2','{bcrypt}$2a$10$o3eFgx5gkg0UPctYnvje7OMSTKhfZJ9H2.X/60UKxH382LeCIcHJ.',NOW());

insert into account (id,email,name,nick_name,passwd,reg_date)
values(4,'james@gmail.com','James','toto','{bcrypt}$2a$10$o3eFgx5gkg0UPctYnvje7OMSTKhfZJ9H2.X/60UKxH382LeCIcHJ.',NOW());



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

insert into account_roles (account_id,role_id)
values(4,2);




INSERT INTO category (id,category_name,reg_date,ordering,account_id)
Values(1,'Backend',NOW(),1,2);

INSERT INTO category (id,category_name,reg_date,ordering,account_id)
Values(2,'Frontend',NOW(),2,2);


INSERT INTO category (id,category_name,reg_date,ordering,account_id)
Values(3,'Backend',NOW(),1,1);

INSERT INTO category (id,category_name,reg_date,ordering,account_id)
Values(4,'Spring',NOW(),2,1);

INSERT INTO category (id,category_name,reg_date,ordering,account_id)
Values(5,'Algorithm',NOW(),3,1);


INSERT INTO category (id,category_name,reg_date,ordering,account_id)
Values(6,'Frontend',NOW(),1,4);

INSERT INTO category (id,category_name,reg_date,ordering,account_id)
Values(7,'Algorithm',NOW(),2,4);






INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(1,1,'jaehee@gmail.com',NOW(),0,'[JAVA] 변수', 'https://jaehee0145.github.io/java/JAVA-%EB%B3%80%EC%88%98/',1,3);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(2,1,'jaehee@gmail.com',NOW(),0,'[JAVA] 자바 시작하기', 'https://jaehee0145.github.io/java/',1,3);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(3,1,'jaehee@gmail.com',NOW(),0,'[Programming] 객체 지향 프로그래밍이란?', 'https://jaehee0145.github.io/programming/',1,3);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(4,1,'jaehee@gmail.com',NOW(),0,'부스트코스_웹 프로그래밍', 'https://www.edwith.org/boostcourse-web',1,3);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(5,1,'jaehee@gmail.com',NOW(),0,'TCP School', 'http://tcpschool.com/',1,3);

INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(6,1,'jaehee@gmail.com',NOW(),0,'Learn Spring', 'https://www.baeldung.com/learn-spring-course',1,4);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(7,1,'jaehee@gmail.com',NOW(),0,'Spring security', 'https://www.baeldung.com/learn-spring-security-course',1,4);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(8,1,'jaehee@gmail.com',NOW(),0,'spring boot interview question', 'https://www.baeldung.com/spring-boot-interview-questions',1,4);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(9,1,'jaehee@gmail.com',NOW(),0,'Rest Query Language', 'https://www.baeldung.com/spring-rest-api-query-search-language-tutorial',1,4);

INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(10,1,'jaehee@gmail.com',NOW(),0,'프로그래머스', 'https://programmers.co.kr/learn/challenges',1,5);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(11,1,'jaehee@gmail.com',NOW(),0,'알고스팟', 'https://algospot.com/wiki/read/%EC%95%8C%EA%B3%A0%EC%8A%A4%ED%8C%9F_%EC%98%A8%EB%9D%BC%EC%9D%B8_%EC%A0%80%EC%A7%80',1,5);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(12,1,'jaehee@gmail.com',NOW(),0,'알고리즘 팁', 'https://blog.yena.io/studynote/2018/11/14/Algorithm-Basic.html?fbclid=IwAR3voH_5IVOHXbZdFEl2IwAOeQqnnKQmNL82GmhBN16ymbhzEvNXDAzYE5c',1,5);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(13,1,'jaehee@gmail.com',NOW(),0,'코딜리티', 'https://www.codility.com/',1,5);


INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(18,1,'treasureb1330@gmail.com',NOW(),0,'[JAVA] 변수', 'https://jaehee0145.github.io/java/JAVA-%EB%B3%80%EC%88%98/',2,1);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(19,1,'treasureb1330@gmail.com',NOW(),0,'[JAVA] 자바 시작하기', 'https://jaehee0145.github.io/java/',2,1);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(20,1,'treasureb1330@gmail.com',NOW(),0,'[Programming] 객체 지향 프로그래밍이란?', 'https://jaehee0145.github.io/programming/',2,1);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(21,1,'treasureb1330@gmail.com',NOW(),0,'부스트코스_웹 프로그래밍', 'https://www.edwith.org/boostcourse-web',2,1);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(22,1,'treasureb1330@gmail.com',NOW(),0,'TCP School', 'http://tcpschool.com/',2,1);

INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(23,1,'treasureb1330@gmail.com',NOW(),0,'html_poiema', 'https://poiemaweb.com/html5-syntax',2,2);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(24,1,'treasureb1330@gmail.com',NOW(),0,'w3school', 'https://www.w3schools.com/default.asp',2,2);

INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(25,1,'james@gmail.com',NOW(),0,'html_poiema', 'https://poiemaweb.com/html5-syntax',4,6);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(26,1,'james@gmail.com',NOW(),0,'w3school', 'https://www.w3schools.com/default.asp',4,6);

INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(27,1,'james@gmail.com',NOW(),0,'프로그래머스', 'https://programmers.co.kr/learn/challenges',4,7);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(28,1,'james@gmail.com',NOW(),0,'알고스팟', 'https://algospot.com/wiki/read/%EC%95%8C%EA%B3%A0%EC%8A%A4%ED%8C%9F_%EC%98%A8%EB%9D%BC%EC%9D%B8_%EC%A0%80%EC%A7%80',4,7);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(29,1,'james@gmail.com',NOW(),0,'알고리즘 팁', 'https://blog.yena.io/studynote/2018/11/14/Algorithm-Basic.html?fbclid=IwAR3voH_5IVOHXbZdFEl2IwAOeQqnnKQmNL82GmhBN16ymbhzEvNXDAzYE5c',4,7);
INSERT INTO link(id,link_option,email, reg_date, repository,title,url,account_id,category_id)
VALUES(30,1,'james@gmail.com',NOW(),0,'코딜리티', 'https://www.codility.com/',4,7);



INSERT INTO message_option(id,opt_type,reg_date,type_id)
VALUES(1,1,NOW(),1);

INSERT INTO message(id,receive_id,use_yn,reg_date,account_id,options_id)
VALUES(1,'treasureb1330@gmail.com','YES',NOW(),1,1);


INSERT INTO tag(id, email, tag_name,options,reg_date)
VALUES (1,'jaehee@gmail.com','JAVA',0,NOW());
INSERT INTO tag(id, email, tag_name,options,reg_date)
VALUES (2,'jaehee@gmail.com','variables',0,NOW());
INSERT INTO tag(id, email, tag_name,options,reg_date)
VALUES (3,'jaehee@gmail.com','web',0,NOW());
INSERT INTO tag(id, email, tag_name,options,reg_date)
VALUES (4,'jaehee@gmail.com','OOP',0,NOW());
INSERT INTO tag(id, email, tag_name,options,reg_date)
VALUES (5,'jaehee@gmail.com','security',0,NOW());
INSERT INTO tag(id, email, tag_name,options,reg_date)
VALUES (6,'jaehee@gmail.com','문제사이트',0,NOW());
INSERT INTO tag(id, email, tag_name,options,reg_date)
VALUES (7,'jaehee@gmail.com','공부방법',0,NOW());
INSERT INTO tag(id, email, tag_name,options,reg_date)
VALUES (8,'jaehee@gmail.com','boot',0,NOW());

INSERT INTO tag(id, email, tag_name,options,reg_date)
VALUES (9,'treasureb1330@gmail.com','JAVA',0,NOW());
INSERT INTO tag(id, email, tag_name,options,reg_date)
VALUES (10,'treasureb1330@gmail.com','variables',0,NOW());
INSERT INTO tag(id, email, tag_name,options,reg_date)
VALUES (11,'treasureb1330@gmail.com','web',0,NOW());
INSERT INTO tag(id, email, tag_name,options,reg_date)
VALUES (12,'treasureb1330@gmail.com','OOP',0,NOW());
INSERT INTO tag(id, email, tag_name,options,reg_date)
VALUES (13,'treasureb1330@gmail.com','html',0,NOW());
INSERT INTO tag(id, email, tag_name,options,reg_date)
VALUES (14,'treasureb1330@gmail.com','tutorial',0,NOW());
INSERT INTO tag(id, email, tag_name,options,reg_date)
VALUES (15,'treasureb1330@gmail.com','참고',0,NOW());

INSERT INTO tag_mapping(tag_id, board_id)
VALUES (9, 18);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (10, 18);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (9, 19);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (12, 20);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (11, 21);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (13, 23);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (15, 23);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (15, 24);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (14, 24);


INSERT INTO tag_mapping(tag_id, board_id)
VALUES (1, 1);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (2, 1);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (1, 2);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (1, 3);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (4, 3);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (3, 4);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (1, 5);

INSERT INTO tag_mapping(tag_id, board_id)
VALUES (5, 7);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (5, 8);

INSERT INTO tag_mapping(tag_id, board_id)
VALUES (8, 8);

INSERT INTO tag_mapping(tag_id, board_id)
VALUES (6, 10);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (6, 11);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (6, 13);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (7, 12);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (7, 10);
