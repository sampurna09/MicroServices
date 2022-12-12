create table exchange_value (id bigint not null, conversion_multiple numeric(38,2), currency_from varchar(255), port integer not null, currency_to varchar(255), primary key (id));
insert into exchange_value (conversion_multiple, currency_from, port, currency_to, id) 
values (132, 'USD', 8000, 'NRS', 1);
insert into exchange_value (conversion_multiple, currency_from, port, currency_to, id) 
values (75, 'UAE', 8001, 'NRS', 2);
insert into exchange_value (conversion_multiple, currency_from, port, currency_to, id) 
values (75, 'AUS', 8002, 'NRS', 3);
insert into exchange_value (conversion_multiple, currency_from, port, currency_to, id) 
values (75, 'JAP', 8003, 'NRS', 4);