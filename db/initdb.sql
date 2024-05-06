CREATE SCHEMA IF NOT EXISTS acctmgrdb;
SET SCHEMA acctmgrdb;

create table if not exists account_balance (
	customer_id varchar(8) not null,
	currency varchar(3) not null,
	balance decimal(18,4) not null default 0.0000,
	constraint pk_account_balance primary key (customer_id)
);

insert into account_balance (customer_id, currency, balance)
select '12345678' as customer_id, 'HKD' as currency, 1000000.0000 as balance
where not exists (select customer_id from account_balance where customer_id='12345678');

insert into account_balance (customer_id, currency, balance)
select '88888888' as customer_id, 'HKD' as currency, 1000000.0000 as balance
where not exists (select customer_id from account_balance where customer_id='88888888');

commit;

create table if not exists transfer_transaction (
	transfer_transaction_id bigint not null auto_increment,
	transaction_datetime datetime not null,
	source_customer_id varchar(8) not null,
	currency varchar(3) not null,
	transfer_amount decimal(18,4) not null default 0.0000,
	destination_customer_id varchar(8) not null,
	constraint pk_transfer_transaction primary key (transfer_transaction_id)
);

