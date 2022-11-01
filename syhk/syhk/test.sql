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
primary key (article_id),
foreign key (`id`) references `user`(`id`)
) comment = '文章';

use demo;
select  * from article  ;

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



































