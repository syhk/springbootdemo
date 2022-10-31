-- 重新设计整个表
create database demo;

use demo;

-- drop table if exists  `user`;
-- 设计： 用户名唯一，可用邮箱号或用户名配合密码登录
create table  if not exists `user`(
`id` int(20) not null  auto_increment,
`name` varchar(100) not null,
`avatarUrl` varchar(200) default null, -- 用户头像
`gender` int(1)  not null default(1) COMMENT '1 表示男 0 表示女', -- 1 表示男 0 表示女
`email`  varchar(100) not null unique COMMENT '注册的时候需要用这个',
`status` int(1) not null COMMENT '1  表示正常 0 表示禁用',
`createTime` Date not null COMMENT '用户创建时间',
`updateTime` Date  not null COMMENT '用户更新时间',
`role` int(1) not null COMMENT ' 1 表示管理员 0 表示普通用户',
primary key(`id`) using BTREE
)engine=InnoDB default CHARSET=utf8 collate=utf8_bin comment='用户信息表';
insert  into `user` values(1,"syhk","null",1,"725482520@qq.com",1,"2022-10-10","2022-10-10",1);
select  * from `user`;

insert  into `user` values(1,"syhk","null",1,"725482520@qq.com",1,"2022-10-10","2022-10-10",1);

select  * from `user`;

-- 以下代码还没有写
--  设计文章表
create table article(
`article_id` BIGINT not null auto_increment COMMENT '博客文章ID',
`push_time` DATETIME COMMENT '发布日期',
`article_user` VARCHAR(32) COMMENT '发表用户', -- 思考是否需要连接外键 user 表
`title` VARCHAR(1024)  COMMENT '文章标题',
`like_count` INT COMMENT '点赞数',
`remark_coutn` INT COMMENT '评论数',
`read_count` INT COMMENT '浏览量',
`create_time` DATETIME COMMENT '创建时间',
`article_summary` VARCHAR(1024) COMMENT '文章摘要',
primary key (article_id)
) COMMENT = '文章';;


-- 文章详情表
create table if not exists `article_detail`(
`article_detail_id` BIGINT not null auto_increment COMMENT '文章详情id',
`content_md`  TEXT COMMENT '文章markdown 内容',
`content_html` TEXT COMMENT '文章html内容',
`article_id` BIGINT COMMENT '文章id',
primary key (article_detail_id),
foreign key (`article_id`) references article(`article_id`)
)COMMENT = '文章详情';


-- 文章标签表
create table if not exists `article_tag_referenced`(
`atr_id` BIGINT not null auto_increment COMMENT '引用id'，
`article_id` BIGINT COMMENT '文章id',
`tag_id` BIGINT COMMENT '标签id',
primary key(atr_id)
) COMMENT = '文章标签';;


-- 文章分类
create table if not exists  `category`(
`category_id` BIGINT not null auto_increment COMMENT '引用id',
`category_name` VARCHAR(64) COMMENT '分类名称',
`alias_name`  VARCHAR(64) COMMENT '分类别名',
`description` VARCHAR(1024) COMMENT '分类描述',
`parennt_id` BIGINT COMMENT '父分类id',
`create_time` DATETIME COMMENT '创建时间',
primary key(category_id)
) COMMENT ='分类';;



-- 标签表
create table if not exists  `tag`(
`tag_di` BIGINT not null auto_increment COMMENT '标签id',
`tag_name` VARCHAR(64) COMMENT '标签名称',
`alias_name` VARCHAR(64) COMMENT '标签别名',
`description`  VARCHAR(1024) COMMENT '标签描述',
`ceate_time` DATETIME COMMENT '创建时间',
primary key (tag_id)
)COMMENT = '标签';;


-- 评论表
create table if not exists `discuss` (
`discuss_id` BIGINT not null auto_increment COMMENT '评论id'，
`create_time` DATETIME COMMENT '评论日期',
`like_count` INT COMMENT '点赞数',
`discuss_user` BIGINT COMMENT '发表用户',
`article_id` BIGINT COMMENT '评论文章id',
`content` VARCHAR(3072) COMMENT '评论内容',
`parent_id` BIGINT COMMENT '父评论id',
primary key(discuss_id)
)COMMENT ='评论';;











