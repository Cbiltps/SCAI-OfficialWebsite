-- 创建数据库
CREATE DATABASE IF NOT EXISTS scai_official_website CHARACTER SET utf8mb4;

-- 使用数据库
USE scai_official_website;

DROP TABLE IF EXISTS message;

-- 创建留言表
CREATE TABLE message
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    userName       VARCHAR(50)                        NOT NULL comment '用户名称',
    userPhone      VARCHAR(50)                        NOT NULL comment '用户电话',
    userEmail      VARCHAR(50)                        NOT NULL comment '用户邮件',
    messageContent TEXT                               NOT NULL comment '留言正文',
    editTime       datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete       tinyint  default 0                 not null comment '是否删除',
    INDEX idx_userName (userName)
);

DROP TABLE IF EXISTS news;

-- 创建新闻表
CREATE TABLE news
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    title           VARCHAR(255)                       NOT NULL comment '标题',
    newsDescription VARCHAR(500)                       not null comment '描述',
    newsContent     TEXT                               NOT NULL comment '正文',
    newsType        VARCHAR(20)                        not null comment '新闻类型:公司新闻/行业新闻',
    author          VARCHAR(50)                        null comment '作者',
    imageUrl        VARCHAR(255)                       not null comment '图片链接',
    newsUrl         VARCHAR(255)                       not null comment '图片链接',
    editTime        datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime      datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete        tinyint  default 0                 not null comment '是否删除',
    UNIQUE KEY uk_title (title),
    INDEX idx_author (author),
    INDEX idx_newsType (newsType)

);

DROP TABLE IF EXISTS projects;

-- 创建项目表
CREATE TABLE projects
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    projectName        VARCHAR(255)                          NOT NULL comment '项目名称',
    projectDescription VARCHAR(500)                          not null comment '描述',
    projectContent     TEXT                                  NOT NULL comment '正文',
    projectStatus      VARCHAR(20) default '可进行'             not null comment '项目状态:准备中/可进行/已下马',
    projectType        VARCHAR(20)                           not null comment '项目类型:AI模型/网页设计/APP设计',
    imageUrl           VARCHAR(255)                          not null comment '图片链接',
    projectUrl         VARCHAR(255)                          not null comment '图片链接',
    editTime           datetime    default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime         datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime         datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete           tinyint     default 0                 not null comment '是否删除',
    UNIQUE KEY uk_projectName (projectName),
    INDEX idx_projectType (projectType)
);

DROP TABLE IF EXISTS user;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment primary key comment 'id',
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin',
    editTime     datetime     default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    UNIQUE KEY uk_userAccount (userAccount),
    INDEX idx_userName (userName)
);

