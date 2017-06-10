insert into user_role (role) values ('ADMIN');
insert into user_role (role) values ('LIBRARIAN');
insert into user_role (role) values ('STUDENT');

insert into student_repo (name,email,year,stream,roll) values ('Sandip','sandip@gmail.com','2010','ECE',1);
insert into student_repo (name,email,year,stream,roll) values ('Shreya','shreya@gmail.com','2010','ECE',2);
insert into student_repo (name,email,year,stream,roll) values ('Sujit','sujit@gmail.com','2010','ECE',3);
insert into student_repo (name,email,year,stream,roll) values ('Sourav','sourav@gmail.com','2010','ECE',28);

insert into library_user (role,name,email,password,active_till) values (1,'Sourabh','admin@lbry.com','$2a$10$DUv434yMpQHAiguKGRnsjOXJV427C6PFpcklI.cHWt4akgcRbej4K','2017-10-30');
insert into library_user (role,name,email,password,active_till) values (2,'Gourab','lbry@lbry.com','$2a$10$DUv434yMpQHAiguKGRnsjOXJV427C6PFpcklI.cHWt4akgcRbej4K','2017-10-30');
insert into library_user (role,name,email,password,active_till) values (3,'Sandip','sandip@gmail.com','$2a$10$DUv434yMpQHAiguKGRnsjOXJV427C6PFpcklI.cHWt4akgcRbej4K','2017-10-30');
insert into library_user (role,name,email,password,active_till) values (3,'Shreya','shreya@gmail.com','$2a$10$DUv434yMpQHAiguKGRnsjOXJV427C6PFpcklI.cHWt4akgcRbej4K','2017-10-30');
insert into library_user (role,name,email,password,active_till) values (3,'Sujit','sujit@gmail.com','$2a$10$DUv434yMpQHAiguKGRnsjOXJV427C6PFpcklI.cHWt4akgcRbej4K','2017-10-30');
insert into library_user (role,name,email,password,active_till) values (3,'Sourav','sourav@gmail.com','$2a$10$DUv434yMpQHAiguKGRnsjOXJV427C6PFpcklI.cHWt4akgcRbej4K','2017-10-30');

insert into book_repo (name,author,category,sub_category,count,total) values ('Java Core','MKYONG','Programming','ECE,IT',0,2);
insert into book_repo (name,author,category,sub_category,count,total) values ('Java Spring','MKYONG','Programming','ECE,IT',0,2);
insert into book_repo (name,author,category,sub_category,count,total) values ('Java JDBC','MKYONG','Programming','ECE,IT',0,2);


--date update query Example
--UPDATE library_user SET active_till = TO_DATE('2017-12-30','yyyy-mm-dd');

