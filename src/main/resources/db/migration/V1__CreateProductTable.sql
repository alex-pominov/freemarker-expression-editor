CREATE TABLE IF NOT EXISTS product (
   id serial primary key,
   shortDesc text
);


CREATE TABLE IF NOT EXISTS contract (
    id serial primary key,
   description text
);

CREATE TABLE IF NOT EXISTS currency (
    id serial primary key,
    name text
);

CREATE TABLE IF NOT EXISTS price (
    id serial primary key,
    product integer references product(id),
    product_key integer,

    currencyId integer references currency(id),
    price integer,
    validFromQuantity float,
    contractId integer references contract(id)
);