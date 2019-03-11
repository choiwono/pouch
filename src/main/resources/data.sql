insert into account (id,email,name,nick_name,passwd,reg_date)
values(1,'treasureb1220@gmail.com','최원오','choiwono1','{bcrypt}$2a$10$o3eFgx5gkg0UPctYnvje7OMSTKhfZJ9H2.X/60UKxH382LeCIcHJ.',NOW());

insert into account (id,email,name,nick_name,passwd,reg_date)
values(2,'treasureb1330@gmail.com','최원오','choiwono2','{bcrypt}$2a$10$o3eFgx5gkg0UPctYnvje7OMSTKhfZJ9H2.X/60UKxH382LeCIcHJ.',NOW());

insert into account (id,email,name,nick_name,passwd,reg_date)
values(3,'admin','최원오','choiwono2','{bcrypt}$2a$10$o3eFgx5gkg0UPctYnvje7OMSTKhfZJ9H2.X/60UKxH382LeCIcHJ.',NOW());


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

insert into product_category (id,category_name,reg_date,ordering)
values(1,'귀여운',NOW(),1);
insert into product_category (id,category_name,reg_date,ordering)
values(2,'재밌는',NOW(),2);
insert into product_category (id,category_name,reg_date,ordering)
values(3,'메시지',NOW(),3);
insert into product_category (id,category_name,reg_date,ordering)
values(4,'일러스트',NOW(),4);
insert into product_category (id,category_name,reg_date,ordering)
values(5,'카카오공식',NOW(),5);
insert into product_category (id,category_name,reg_date,ordering)
values(6,'동물',NOW(),6);
insert into product_category (id,category_name,reg_date,ordering)
values(7,'주제',NOW(),7);
insert into product_category (id,category_name,reg_date,ordering)
values(8,'스타/방송',NOW(),8);
insert into product_category (id,category_name,reg_date,ordering)
values(9,'만화/웹툰',NOW(),9);

insert into product (id,amount,name,price,rating,reg_date,category_id)
values(1,100,'귀여운이모티콘',2300,0.0,NOW(),1);
insert into product (id,amount,name,price,rating,reg_date,category_id)
values(2,100,'귀여운이모티콘2',2300,0.0,NOW(),1);
insert into product (id,amount,name,price,rating,reg_date,category_id)
values(3,100,'귀여운이모티콘3',2300,0.0,NOW(),1);
insert into product (id,amount,name,price,rating,reg_date,category_id)
values(4,100,'귀여운이모티콘4',2300,0.0,NOW(),1);
insert into product (id,amount,name,price,rating,reg_date,category_id)
values(5,100,'귀여운이모티콘5',2300,0.0,NOW(),1);

insert into product (id,amount,name,price,rating,reg_date,category_id)
values(6,100,'재밌는이모티콘',2300,0.0,NOW(),2);
insert into product (id,amount,name,price,rating,reg_date,category_id)
values(7,100,'재밌는이모티콘2',2300,0.0,NOW(),2);
insert into product (id,amount,name,price,rating,reg_date,category_id)
values(8,100,'재밌는이모티콘3',2300,0.0,NOW(),2);
insert into product (id,amount,name,price,rating,reg_date,category_id)
values(9,100,'재밌는이모티콘4',2300,0.0,NOW(),2);
insert into product (id,amount,name,price,rating,reg_date,category_id)
values(10,100,'재밌는이모티콘5',2300,0.0,NOW(),2);

insert into product (id,amount,name,price,rating,reg_date,category_id)
values(11,100,'멋있는이모티콘1',2300,0.0,NOW(),3);
insert into product (id,amount,name,price,rating,reg_date,category_id)
values(12,100,'멋있는이모티콘2',2300,0.0,NOW(),3);
insert into product (id,amount,name,price,rating,reg_date,category_id)
values(13,100,'멋있는이모티콘3',2300,0.0,NOW(),3);
insert into product (id,amount,name,price,rating,reg_date,category_id)
values(14,100,'멋있는이모티콘4',2300,0.0,NOW(),3);
insert into product (id,amount,name,price,rating,reg_date,category_id)
values(15,100,'멋있는이모티콘5',2300,0.0,NOW(),3);

insert into product (id,amount,name,price,rating,reg_date,category_id)
values(16,100,'일러스트이모티콘1',2300,0.0,NOW(),4);
insert into product (id,amount,name,price,rating,reg_date,category_id)
values(17,100,'일러스트이모티콘2',2300,0.0,NOW(),4);
insert into product (id,amount,name,price,rating,reg_date,category_id)
values(18,100,'일러스트이모티콘3',2300,0.0,NOW(),4);
insert into product (id,amount,name,price,rating,reg_date,category_id)
values(19,100,'일러스트이모티콘4',2300,0.0,NOW(),4);
insert into product (id,amount,name,price,rating,reg_date,category_id)
values(20,100,'일러스트이모티콘5',2300,0.0,NOW(),4);


insert into review (id,content,rating,reg_date,title,product_id,account_id)
values(1,'별로네요 칫',4.5,NOW(),'정말 좋아요 ^^',1,1);
insert into review (id,content,rating,reg_date,title,product_id,account_id)
values(3,'별로네요 칫칫',4.0,NOW(),'정말 좋아요 ^^',1,1);
insert into review (id,content,rating,reg_date,title,product_id,account_id)
values(2,'별로네요 칫칫칫',5.0,NOW(),'정말 좋아요 ^^!!!',1,2);

insert into file_image (id,file_name,length,mime_type,options,reg_date,save_file_name,product_id)
values(1,'8809538000146_AW_00.jpg',104619,'image/jpeg',1,NOW(),'/tmp/2019/3/2/29131514-d1dc-4e10-b83a-714e71371a34',1);
insert into file_image (id,file_name,length,mime_type,options,reg_date,save_file_name,product_id)
values(2,'8809538000146_AW_00.jpg',104619,'image/jpeg',1,NOW(),'/tmp/2019/3/2/29131514-d1dc-4e10-b83a-714e71371a34',2);
insert into file_image (id,file_name,length,mime_type,options,reg_date,save_file_name,product_id)
values(3,'8809538000146_AW_00.jpg',101449,'image/jpeg',1,NOW(),'/tmp/2019/3/2/eba5dd12-b224-4537-8a73-45a8309f198d',3);
insert into file_image (id,file_name,length,mime_type,options,reg_date,save_file_name,product_id)
values(4,'8809538000146_AW_00.jpg',101449,'image/jpeg',1,NOW(),'/tmp/2019/3/2/eba5dd12-b224-4537-8a73-45a8309f198d',4);
insert into file_image (id,file_name,length,mime_type,options,reg_date,save_file_name,product_id)
values(5,'8809538000146_AW_00.jpg',101449,'image/jpeg',1,NOW(),'/tmp/2019/3/2/eba5dd12-b224-4537-8a73-45a8309f198d',5);

insert into file_image (id,file_name,length,mime_type,options,reg_date,save_file_name,product_id)
values(6,'8809538000146_AW_00.jpg',104619,'image/jpeg',1,NOW(),'/tmp/2019/3/2/29131514-d1dc-4e10-b83a-714e71371a34',6);
insert into file_image (id,file_name,length,mime_type,options,reg_date,save_file_name,product_id)
values(7,'8809538000146_AW_00.jpg',101449,'image/jpeg',1,NOW(),'/tmp/2019/3/2/eba5dd12-b224-4537-8a73-45a8309f198d',7);

insert into file_image (id,file_name,length,mime_type,options,reg_date,save_file_name,product_id)
values(8,'8809538000146_AW_00.jpg',101449,'image/jpeg',1,NOW(),'/tmp/2019/3/2/eba5dd12-b224-4537-8a73-45a8309f198d',8);
insert into file_image (id,file_name,length,mime_type,options,reg_date,save_file_name,product_id)
values(9,'8809538000146_AW_00.jpg',101449,'image/jpeg',1,NOW(),'/tmp/2019/3/2/eba5dd12-b224-4537-8a73-45a8309f198d',9);
insert into file_image (id,file_name,length,mime_type,options,reg_date,save_file_name,product_id)
values(10,'8809538000146_AW_00.jpg',101449,'image/jpeg',1,NOW(),'/tmp/2019/3/2/eba5dd12-b224-4537-8a73-45a8309f198d',10);

insert into file_image (id,file_name,length,mime_type,options,reg_date,save_file_name,product_id)
values(11,'8809538000146_AW_00.jpg',104619,'image/jpeg',1,NOW(),'/tmp/2019/3/2/29131514-d1dc-4e10-b83a-714e71371a34',11);
insert into file_image (id,file_name,length,mime_type,options,reg_date,save_file_name,product_id)
values(12,'8809538000146_AW_00.jpg',101449,'image/jpeg',1,NOW(),'/tmp/2019/3/2/eba5dd12-b224-4537-8a73-45a8309f198d',12);

insert into file_image (id,file_name,length,mime_type,options,reg_date,save_file_name,product_id)
values(13,'8809538000146_AW_00.jpg',101449,'image/jpeg',1,NOW(),'/tmp/2019/3/2/eba5dd12-b224-4537-8a73-45a8309f198d',13);
insert into file_image (id,file_name,length,mime_type,options,reg_date,save_file_name,product_id)
values(14,'8809538000146_AW_00.jpg',101449,'image/jpeg',1,NOW(),'/tmp/2019/3/2/eba5dd12-b224-4537-8a73-45a8309f198d',14);
insert into file_image (id,file_name,length,mime_type,options,reg_date,save_file_name,product_id)
values(15,'8809538000146_AW_00.jpg',101449,'image/jpeg',1,NOW(),'/tmp/2019/3/2/eba5dd12-b224-4537-8a73-45a8309f198d',15);

insert into orders (id,addr1, addr2, email1, email2, message, order_no, order_status, payment, phone, receiver_name, receiver_phone, reg_date, total_price, user_auth, user_name, zip_code)
values(1,'서울 노원구 상계동 720', '주공아파트 609동 806호', 'treasureb1220', 'gmail.com', '배송메시지를 남겨주셔요~~', 'S20190308213028a228e022-832c-4c35-965e-6e84062a3556', 0, 1, '01062923620', '최원오', '01062923620', NOW(), 4600, 1, '최원오', '1752');

insert into order_product(id,amount,name,reg_date,total_price,order_id,proudct_id)
values(1,1,'멋있는이모티콘3',NOW(),2300,1,13);

insert into order_product(id,amount,name,reg_date,total_price,order_id,proudct_id)
values(2,1,'멋있는이모티콘4',NOW(),2300,1,14);