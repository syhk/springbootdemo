-- 重新设计整个表
create database demo;

use demo;

-- drop table if exists  `user`;
-- 设计： 用户名唯一，可用邮箱号或用户名配合密码登录
create table  if not exists `user`(
`id` int(20) not null  auto_increment,
`name` varchar(100) not null,
`avatarUrl` varchar(200) default null, -- 用户头像
`gender` int(1)  not null default(1) comment '1 表示男 0 表示女', -- 1 表示男 0 表示女
`email`  varchar(100) not null unique comment '注册的时候需要用这个',
`status` int(1) not null comment '1  表示正常 0 表示禁用',
`createTime` Date not null comment '用户创建时间',
`updateTime` Date  not null comment '用户更新时间',
`role` int(1) not null comment ' 1 表示管理员 0 表示普通用户',
primary key(`id`) using BTREE
)engine=InnoDB default CHARSET=utf8 collate=utf8_bin comment='用户信息表';
insert  into `user` values(1,"syhk","null",1,"725482520@qq.com",1,"2022-10-10","2022-10-10",1);
select  * from `user`;

insert  into `user` values(1,"syhk","null",1,"725482520@qq.com",1,"2022-10-10","2022-10-10",1);

select  * from `user`;


-- 添加文章表
--  设计文章表
create table article(
`article_id` BIGINT not null auto_increment comment '博客文章ID',
`push_time` DATETIME comment '发布日期',
`id` int(20) comment '发表用户', -- 连接 user 表， 根据 id 获取用户名
`title` VARCHAR(1024)  comment '文章标题',
`like_count` INT comment '点赞数',
`remark_count` INT comment '评论数',
`read_count` INT comment '浏览量',
`create_time` DATETIME comment '创建时间',
`article_summary` VARCHAR(1024) comment '文章摘要',
`md` VARCHAR(10000) NOT NULL COMMENT '文章内容',
primary key (article_id),
foreign key (`id`) references `user`(`id`)
) comment = '文章';


use demo;
select  * from article  ;


--   测试数据
insert  into article values(1,'2020-10-23', 1,'java',10,20,3000,'2020-10-22','这是关于 java 的相关知识点' );

insert  into article values(2,'2020-10-23', 2,'cpp',10,20,3000,'2020-10-23','这是关于 cpp 的相关知识点' );

insert  into article values(4,'2020-10-23', 4,'go',20,3000,20000,'2020-10-24','这是关于 go 的相关知识点' );

insert  into article values(6,'2020-10-23', 6,'rust',10,20,3000,'2020-10-25','这是关于 rust 的相关知识点' );


insert  into article values(7,'2020-10-23', 7,'vue',10,20,3000,'2020-10-26','这是关于 vue 的相关知识点' );

insert  into article values(8,'2020-10-23', 7,'html',10,20,3000,'2020-10-28','这是关于 html 的相关知识点' );

insert  into article values(9,'2000-10-23', 4,'cpp',20,3000,60000,'2000-10-24','这是关于 cpp 的相关知识点' );




-- 使用 id 获取发表用户的信息
select * from `user` where  `id` = 7;




select id,article_id,push_time,title,remark_count,read_count,create_time,article_summary from article;

update    article  set `title`='c加加' where `id` = 2;

update article set  article_summary='这是关于CPP 的相关知识点' where `id` = 2;

update article set  article_summary='这是关于java 的相关知识点' where `id` = 1;

update article set  article_summary='这是关于go 的相关知识点' where `id` = 4;
update article set  article_summary='这是关于RUST 的相关知识点' where `id` = 6;
update article set  article_summary='这是关于vue 的相关知识点' where `id` = 7;
update article set  article_summary='这是关于HTML 的相关知识点' where `id` = 8;
select * from article;



-- 文章详情表
-- 文章详情表
--DROP TABLE IF EXISTS article_detail;
--drop table article_detail ;
--CREATE TABLE article_detail(
--    `article_detail_id` INT NOT NULL AUTO_INCREMENT  COMMENT '文章详情 id' ,
--    `article_id` BIGINT NOT NULL unique  COMMENT '引用文章表的id' , -- 增加了唯一性约束，因为一篇详情内容只有对应一篇文章
--    `article_html` TEXT    COMMENT 'html 格式的文章' ,
--    `article_md` TEXT    COMMENT 'md 格式的文章' ,
--    PRIMARY KEY (article_detail_id,article_id),
--    foreign key (`article_id`) references article(`article_id`)
--)  COMMENT = '文章详情';
---- 测试数据
--select  * from article_detail;
--insert into demo.`article_detail` ( `article_id`, `article_html`, `article_md`) values ( 1, 'lB', 'BI');
--insert into demo.`article_detail` ( `article_id`, `article_html`, `article_md`) values ( 2, 'EVb', 'mKE2c');
--insert into demo.`article_detail` ( `article_id`, `article_html`, `article_md`) values ( 4, 'EuAZz', 'Xk0');
--insert into demo.`article_detail` ( `article_id`, `article_html`, `article_md`) values ( 6, 'kn', 'h6pbK');
--insert into demo.`article_detail` ( `article_id`, `article_html`, `article_md`) values ( 7, 'aQu', 'YS');
--insert into demo.`article_detail` ( `article_id`, `article_html`, `article_md`) values ( 8, 'w8ix', 'lLE');

-- 文章评论表
-- 新增三个字段：评论时间，点赞数量，低赞数量
DROP TABLE IF EXISTS article_remark;
drop table article_remark;
CREATE TABLE article_remark(
    `id` int(20)  ,
    `article_id` BIGINT,
    `context`  VARCHAR(1000)  NOT NULL,
    `createTime` int NOT NULL,
    high int ,
    low int,
    foreign key (`article_id`) references article(`article_id`),
    foreign key (`id`) references `user`(`id`)
);

























