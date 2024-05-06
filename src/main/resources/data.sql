insert into account_balance (customer_id, currency, balance)
select '12345678' as customer_id, 'HKD' as currency, 1000000.0000 as balance
where not exists (select customer_id from account_balance where customer_id='12345678');

insert into account_balance (customer_id, currency, balance)
select '88888888' as customer_id, 'HKD' as currency, 1000000.0000 as balance
where not exists (select customer_id from account_balance where customer_id='88888888');

commit;
