CREATE TABLE public.products (
     id serial PRIMARY KEY,
     account_number integer,
     balance decimal,
     type varchar(100),
     user_id integer references public.users(id)
);