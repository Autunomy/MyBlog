项目名称：MyBlog

# 前端

## main.html

首页展示，利用缩略图的方式展示最近发布的5五篇博客，展示形式类似于CSDN的形式，右侧第一块是一个搜索栏，第二块是全部的博文分类

在中间偏左部分是博客的缩略图，这个每一个缩略图的左下角显示作者及发布日期，右下角显示浏览量，点赞量，在这几个信息下加一个分割线，表示与下一个缩略图有间隔

## guidang.html

这个是归档页面，中间有一个时间线，时间线的左侧是时间，右侧是博文的名字，可以将所有的博文进行归档

## show_blog.html

博文展示页，需要非常简洁，在博文的右侧有一个目录栏，可以将markdown语法中所有的标题都读取出来，然后归结在右边的目录栏

## show_all_blog.html

展示所有的博客信息，给出一个大表格，显示所有的博客信息

## commons

这是一个目录，用来抽取公共部分

### nav.html

抽取出来的导航栏，导航栏中的内容有全部博文，归档，友链，博主介绍  最右边有搜索框

展示博主的个人信息，可以直接跳转到博文展示页面，展示个人信息

友情链接页面，直接跳转到一个博文展示页面，来展示推荐的链接

### footer.html

底部的一些信息，备案信息

### cdn.html

抽取出全部的cdn

### comment.html

将评论页抽取出来

## manage

这是一个目录，用来存放所有的后台页面

### user_manage.html

用来管理用户信息

### add_blog.html

博文添加页面，在本地使用typora编辑好之后转化为html格式，然后将其中的内容复制进来，在输入框的上面需要填写文章的分类以及文章的标题信息

### show_all_blog.html

展示所有的博客信息并提供修改和删除操作

### show_all_tags.html

展示所有分类并提供修改和删除操作

### add_tag.html

添加一个标签分类页面

### tag_manage.html

在此页面可以管理博客的分类，由于是后台页面，所以就不用做的太好看

### update_blog.html

用来对博客信息进行修改的页面

### commons

对manage中的公共部分进行抽取

#### nav.html

抽取导航部分，导航部分的所有博文功能中应该包含修改博文和删除博文功能，修改博文也可以直接使用添加博文的页面，在跳转前先将所有博文信息，然后添加到请求域中，然后在页面中直接将所有信息显示，进行修改

## editor

这个目录主要放markdown编辑页面

# sql

```sql
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
```



# 开发日志

## 2022-03-28

编写了main页面，提取出来了导航，底部，cdn等公共页面

编写了manage下的blog添加页面add_blog

## 2022-03-29

考虑使用editormd这个文本编辑器，可以直接上传md格式文件或者直接在线编辑并进行上传，之后需要使用md转html技术，进行转换并显示在页面中

编写了show_all_blog.html页面，但是目前直接使用表格这种粗暴的方式进行展示，以后需要对其进行美化处理，可以使用缩略图形式或者其他好的形式

编写了show_blog页面，博客内容还没有加入进去，还需要一个评论的功能，需要解决

## 2022-03-31

在add_blog.html页面集成了editormd插件可以编写markdown语言也可以上传文件，在show_blog页面实现了markdown转html显示在页面上的功能。准备将manage中的所有页面进行公共抽取，将公共部分抽取出来

编写了manage下的show_all_blog.html页面，其实本质上还是templates下的show_all_blog.html页面的一个变种，只是在后面加了一列，增加了操作列，提供了删除和更新操作，删除操作需要给后端传递博文的编号，更新页面直接跳转到博文添加页面只不过会自动将信息补充上去提供修改。

编写了comment.html实现了简易的评论功能，主要是使用表格来进行实现的

## 2022-04-01

编写了简易版的tag_manage页面，所有的页面基本都已经成功写完，设计了基础的几个部分的数据库的表，并且将实体类，mapper的接口都编写完成，接下来就是编写mapper.xml和service层以及controller层

## 2022-04-03

将xml文件和service层的代码都编写完成了(是个体力活)，现在开始编写blog下的Controller，blog层下的内容所有人都能访问，所以不需要过滤器进行拦截。今天也是开始了Controller层的编写，发现之前的blog表中没有tag字段，所以就直接添加了一个tag字段。Controller层主要就是编写了主页的缩略图展示，重心放在了manage部分的编写，实现了增删改的功能，目前已经完善了。购买了一台阿里云的服务器用来发布博客项目。在服务器上安装了mysql,jdk和tomcat。

完成了博客分类的增删改。

目前有一个没完成的任务就是在添加分类的时候，需要使用分类名称来判断是否已经创建这个分类，防止重复创建分类，但是编写的queryTagByName方法查出来为null时会报错

下次任务，完成点赞按钮，解决博客中图片上传的问题

## 2022-04-04

给comment评论表添加了一个博客id字段为了对应博客信息，完成了评论功能，此时就需要在后台中对所有的评论进行管理，我们只需要一个功能就是删除，不需要更新以及增加。后台中也需要对用户进行管理，需要有用户管理页

完成了对分类的增删改查，但是目前的blog表的tag外键是tag表的name，这个是有问题的，如果我们修改了这个tag的name就会报错，所以需要对代码进行修改，让blog表的tag外键指向tag表的id，然后每次在使用blog表的tag字段时需要对分类进行一次单独的查询

## 2022-05-10

复习了一下thymeleaf的使用并且编写了普通用户的博客展示页面

## 2022-05-11

今日的目标是完成编写友情链接的页面，需要自己编写CSS样式，但是导航栏不能自己编写，需要使用bootstrap编写好的导航栏

目前将样式和页面大概写好了，但是无法加载css文件，下次要解决css文件无法访问的问题

## 2022-05-12

解决了CSS无法引入的问题，问题应该是出在thymeleaf导入部分，使用include进行导入，将原来的CSS的部分覆盖掉了。

引入CSS之后发现编写的DIV大小太大了，已经超过了这个页面的大小

为这几个友情链接添加字体图标，已经引入了字体图标
