drop table user;

create table user (
	user_id varchar(20),
	user_name varchar(100) not null,
	user_password varchar(20),
	user_is_manage integer,
	user_create_time date,
	user_blog_num integer,
	user_fans_num integer,
	user_focus_num integer,
	user_email varchar(30),
	user_phone varchar(15),
	user_age integer,
	primary key (user_name)
);
alter table user add unique(user_id);
alter table user add unique(user_email);
alter table user add unique(user_phone);


drop table tag;

create table tag (
	tag_id int auto_increment not null ,
	tag_name varchar(20),
	primary key (tag_id)
);

drop table blog;

create table blog (
	blog_id varchar(50),
	blog_name varchar(50),
	blog_content text,
	blog_tag varchar(50),
	blog_author varchar(100) not null,
	blog_date date,
	blog_watch_num int,
	blog_heart_num int,
	primary key (blog_author)
);

ALTER TABLE blog ADD CONSTRAINT fk_blog_blog_author FOREIGN KEY(blog_author) REFERENCES user(user_name);
ALTER TABLE blog ADD CONSTRAINT fk_blog_blog_tag FOREIGN KEY(blog_tag) REFERENCES tag(tag_name);
alter table blog add unique(blog_id);
alter table blog add unique(blog_author);


drop table comment;

create table comment (
	comment_author varchar(100) not null,
	comment_content long varchar,
	comment_time date,
	primary key (comment_author)
);

ALTER TABLE comment ADD CONSTRAINT fk_comment_comment_author FOREIGN KEY(comment_author) REFERENCES user(user_name);
alter table comment add comment_id varchar(30);

