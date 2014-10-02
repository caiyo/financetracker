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

create table bill (
  id                        integer not null,
  title                     varchar(255),
  due_date                  timestamp,
  is_recuring               boolean,
  amount                    decimal(12,2),
  account_email             varchar(255),
  paid                      boolean,
  constraint pk_bill primary key (id))
;

create table finance_folder (
  id                        integer not null,
  name                      varchar(255),
  total                     decimal(12,2),
  account_email             varchar(255),
  constraint uq_finance_folder_1 unique (name,account_email),
  constraint pk_finance_folder primary key (id))
;

create table transaction (
  id                        integer not null,
  finance_folder_id         integer,
  long_description          varchar(255),
  short_description         varchar(255),
  amount                    decimal(12,2),
  creation_date             timestamp,
  constraint pk_transaction primary key (id))
;

create table transaction_category (
  id                        integer not null,
  category                  varchar(255),
  constraint pk_transaction_category primary key (id))
;

create sequence account_seq;

create sequence bill_seq;

create sequence finance_folder_seq;

create sequence transaction_seq;

create sequence transaction_category_seq;

alter table bill add constraint fk_bill_account_1 foreign key (account_email) references account (email) on delete restrict on update restrict;
create index ix_bill_account_1 on bill (account_email);
alter table finance_folder add constraint fk_finance_folder_account_2 foreign key (account_email) references account (email) on delete restrict on update restrict;
create index ix_finance_folder_account_2 on finance_folder (account_email);
alter table transaction add constraint fk_transaction_financeFolder_3 foreign key (finance_folder_id) references finance_folder (id) on delete restrict on update restrict;
create index ix_transaction_financeFolder_3 on transaction (finance_folder_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists account;

drop table if exists bill;

drop table if exists finance_folder;

drop table if exists transaction;

drop table if exists transaction_category;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists account_seq;

drop sequence if exists bill_seq;

drop sequence if exists finance_folder_seq;

drop sequence if exists transaction_seq;

drop sequence if exists transaction_category_seq;

