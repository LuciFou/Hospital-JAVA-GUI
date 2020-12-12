-- CREATE TABLE user
-- ( id int(11) primary key auto_increment not null,
--   name varchar(250) NOT NULL,
--   username varchar(250) NOT NULL,
--   password varchar(250) not null,
--   usertype varchar(250) not null
-- );
-- /*Alter table doctor
-- add column log_id int(11) not null auto_increment unique key;*/
-- CREATE TABLE item
-- ( itemid varchar(250) primary key not null,
--   itemname varchar(250) not null,
--   description varchar(250) not null,
--   sellprice int(11) not null,
--   buyprice int(11) not null,
--   qty int(11) not null
-- );
-- create table prescription
-- ( pid varchar(250) primary key not null,
--   channelid varchar(250) not null,
--   doctorname varchar(250) not null,
--   dtype varchar(250) not null,
--   description varchar(250) not null
-- );
create table sales
( id int(11) primary key auto_increment not null,
  date date not null,
  subtotal int(11) not null,
  pay int(11) not null,
  balance int(11) not null
);
-- create table sale_product
-- ( id int(11) primary key auto_increment not null,
--   sales_id int(11) not null,
--   prod_id varchar(250) not null,
--   sellprice int(11) not null,
--   qty int(11) not null,
--   total int(11) not null
-- );