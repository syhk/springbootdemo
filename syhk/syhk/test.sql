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



