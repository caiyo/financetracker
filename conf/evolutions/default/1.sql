# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  salt                      varchar(255),
  constraint pk_account primary key (email))
;

create table finance_folder (
  id                        integer not null,
  name                      varchar(255),
  total                     float,
  account_email             varchar(255),
  constraint uq_finance_folder_name unique (name),
  constraint uq_finance_folder_1 unique (name,account_email),
  constraint pk_finance_folder primary key (id))
;

create table transaction (
  id                        integer not null,
  finance_folder_id         integer,
  long_description          varchar(255),
  short_description         varchar(255),
  amount                    float,
  creation_date             timestamp,
  constraint pk_transaction primary key (id))
;

create table transaction_category (
  id                        integer not null,
  category                  varchar(255),
  constraint pk_transaction_category primary key (id))
;

create sequence account_seq;

create sequence finance_folder_seq;

create sequence transaction_seq;

create sequence transaction_category_seq;

alter table finance_folder add constraint fk_finance_folder_account_1 foreign key (account_email) references account (email) on delete restrict on update restrict;
create index ix_finance_folder_account_1 on finance_folder (account_email);
alter table transaction add constraint fk_transaction_financeFolder_2 foreign key (finance_folder_id) references finance_folder (id) on delete restrict on update restrict;
create index ix_transaction_financeFolder_2 on transaction (finance_folder_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists account;

drop table if exists finance_folder;

drop table if exists transaction;

drop table if exists transaction_category;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists account_seq;

drop sequence if exists finance_folder_seq;

drop sequence if exists transaction_seq;

drop sequence if exists transaction_category_seq;

