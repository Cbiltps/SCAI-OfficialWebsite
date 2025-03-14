-- 创建数据库
CREATE DATABASE IF NOT EXISTS scai_official_website CHARACTER SET utf8mb4;

-- 使用数据库
USE scai_official_website;

DROP TABLE IF EXISTS message;

-- 创建留言表
CREATE TABLE message (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    message_content TEXT NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS news;

-- 创建新闻表
CREATE TABLE news (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    -- 新闻描述
    new_description VARCHAR(500),
    new_content TEXT NOT NULL,
    -- 新闻发布时间
    publish_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    author VARCHAR(50),
    -- 新闻图片链接
    image_url VARCHAR(255)
);

DROP TABLE IF EXISTS projects;

-- 创建项目表
CREATE TABLE projects (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(255) NOT NULL,
    -- 项目描述
    project_description VARCHAR(500),
    project_content TEXT NOT NULL,
    start_date DATE,
    end_date DATE,
    project_status VARCHAR(20)
);