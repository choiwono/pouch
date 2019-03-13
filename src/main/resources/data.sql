insert into account (id,email,name,nick_name,passwd,reg_date)
values(1,'treasureb1220@gmail.com','최원오','choiwono1','{bcrypt}$2a$10$o3eFgx5gkg0UPctYnvje7OMSTKhfZJ9H2.X/60UKxH382LeCIcHJ.',NOW());

insert into account (id,email,name,nick_name,passwd,reg_date)
values(2,'treasureb1330@gmail.com','최원오','choiwono2','{bcrypt}$2a$10$o3eFgx5gkg0UPctYnvje7OMSTKhfZJ9H2.X/60UKxH382LeCIcHJ.',NOW());

insert into account (id,email,name,nick_name,passwd,reg_date)
values(3,'admin','최원오','choiwono2','{bcrypt}$2a$10$o3eFgx5gkg0UPctYnvje7OMSTKhfZJ9H2.X/60UKxH382LeCIcHJ.',NOW());

insert into account (id,email,name,nick_name,passwd,reg_date)
values(4,'jaehee@gmail.com','박재희','jaehee','{bcrypt}$2a$10$o3eFgx5gkg0UPctYnvje7OMSTKhfZJ9H2.X/60UKxH382LeCIcHJ.',NOW());


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

INSERT INTO board(id, email, favorites, reg_date, repository, title, url, account_id)
VALUES(1, 'jaehee@gmail.com', 0, NOW(), 0, '[JAVA] 변수', 'https://jaehee0145.github.io/java/JAVA-%EB%B3%80%EC%88%98/', 1);
INSERT INTO board(id, email, favorites, reg_date, repository, title, url, account_id)
VALUES(2, 'jaehee@gmail.com', 1, NOW(), 0, '[JAVA] 자바 시작하기', 'https://jaehee0145.github.io/java/JAVA-%EC%9E%90%EB%B0%94-%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0/', 1);
INSERT INTO board(id, email, favorites, reg_date, repository, title, url, account_id)
VALUES(3, 'jaehee@gmail.com', 1, NOW(), 1, '[Programming] 객체 지향 프로그래밍이란?', 'https://jaehee0145.github.io/programming/Programming-%EA%B0%9D%EC%B2%B4-%EC%A7%80%ED%96%A5-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D/', 1);

INSERT INTO board(id, email, favorites, reg_date, repository, title, url, account_id)
VALUES(4, 'treasureb1330@gmail.com', 1, NOW(), 1, '[기술면접] HTTP란?', 'https://choiwono.github.io/http/', 2);


INSERT INTO tag(id, account_id, reg_date, tag_name)
VALUES (1, 1, NOW(), 'JAVA');
INSERT INTO tag(id, account_id, reg_date, tag_name)
VALUES (2, 1, NOW(), 'variables');
INSERT INTO tag(id, account_id, reg_date, tag_name)
VALUES (3, 2, NOW(), 'network');

INSERT INTO tag_mapping(tag_id, board_id)
VALUES (1, 1);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (1, 2);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (2, 1);
INSERT INTO tag_mapping(tag_id, board_id)
VALUES (3, 4);
