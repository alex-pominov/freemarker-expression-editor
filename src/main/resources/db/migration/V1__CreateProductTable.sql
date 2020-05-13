CREATE TABLE IF NOT EXISTS product (
   id serial primary key,
   short_desc text
);


CREATE TABLE IF NOT EXISTS contract (
    id serial primary key,
   contract_description text
);

CREATE TABLE IF NOT EXISTS currency (
    id serial primary key,
   currency_type text
);

CREATE TABLE IF NOT EXISTS price (
    id serial primary key,
    product integer references product(id),
    product_key integer,

    currency_id integer references currency(id),
    price integer,
    valid_from_quantity float,
    contract_id integer references contract(id)
);