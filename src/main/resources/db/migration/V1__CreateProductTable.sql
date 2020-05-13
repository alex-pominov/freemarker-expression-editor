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


CREATE TABLE IF NOT EXISTS classification_group (
    id serial primary key,
    product integer references product(id),
    product_key integer,

    class_group_name text
);

CREATE TABLE IF NOT EXISTS classification (
    id serial primary key,
    classification_group integer references classification_group(id),
    classification_group_key integer,
    classification_name text
);