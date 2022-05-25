create table `order`
(
id    int auto_increment comment '主键'
primary key,
status int default 0 not null comment '状态 0:创建 1:完成'
);