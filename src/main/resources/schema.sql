CREATE SCHEMA IF NOT EXISTS acctmgrdb;
SET SCHEMA acctmgrdb;

create table if not exists account_balance (
	customer_id varchar(8) not null,
	currency varchar(3) not null,
	balance decimal(18,4) not null default 0.0000,
	constraint pk_account_balance primary key (customer_id)
);

create table if not exists transfer_transaction (
	transfer_transaction_id bigint not null auto_increment,
	transaction_datetime datetime not null,
	source_customer_id varchar(8) not null,
	currency varchar(3) not null,
	transfer_amount decimal(18,4) not null default 0.0000,
	destination_customer_id varchar(8) not null,
	constraint pk_transfer_transaction primary key (transfer_transaction_id)
);

