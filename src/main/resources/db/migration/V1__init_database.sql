drop table if exists order_header cascade;

create table order_header
(
    id bigint not null auto_increment primary key ,
    customer_name varchar(255) NOT NULL
) engine = InnoDB;