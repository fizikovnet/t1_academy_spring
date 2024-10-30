insert into public.products (account_number, balance, type, user_id)
values (1000000, 0.0, 'карта', (select id from public.users where username = 'admin')),
       (1000001, 100.0, 'счет', (select id from public.users where username = 'admin')),
       (1000002, 0.0, 'карта', (select id from public.users where username = 'john')),
       (1000003, 50.0, 'счет', (select id from public.users where username = 'john')),
       (1000004, 1.0, 'карта', (select id from public.users where username = 'jack')),
       (1000005, 150.0, 'счет', (select id from public.users where username = 'jack'));
