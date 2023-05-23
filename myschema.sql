drop database if exists article;

create database article;

use article;

create table article (

    post_id varchar(64) not null,
    media_type varchar(128) not null,
    content mediumblob,
    primary key(post_id)

);