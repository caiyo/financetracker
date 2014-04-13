# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table finance_folder (
  id                        integer not null,
  name                      varchar(255),
  total                     double,
  user_email                varchar(255),
  constraint pk_finance_folder primary key (id))
;

create table transaction (
  id                        integer not null,
  finance_folder_id         integer,
  long_description          varchar(255),
  short_description         varchar(255),
  amount                    double,
  creation_date             timestamp,
  constraint pk_transaction primary key (id))
;

create table transaction_category (
  id                        integer not null,
  category                  varchar(255),
  constraint pk_transaction_category primary key (id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (email))
;

create sequence finance_folder_seq;

create sequence transaction_seq;

create sequence transaction_category_seq;

create sequence user_seq;

alter table finance_folder add constraint fk_finance_folder_user_1 foreign key (user_email) references user (email) on delete restrict on update restrict;
create index ix_finance_folder_user_1 on finance_folder (user_email);
alter table transaction add constraint fk_transaction_financeFolder_2 foreign key (finance_folder_id) references finance_folder (id) on delete restrict on update restrict;
create index ix_transaction_financeFolder_2 on transaction (finance_folder_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists finance_folder;

drop table if exists transaction;

drop table if exists transaction_category;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists finance_folder_seq;

drop sequence if exists transaction_seq;

drop sequence if exists transaction_category_seq;

drop sequence if exists user_seq;

