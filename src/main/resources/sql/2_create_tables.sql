create table products
(
    id           serial not null
        constraint products_pkey
            primary key,
    name         varchar,
    price        varchar,
    is_promotion boolean
);

alter table products
    owner to postgres;


create table discount_cards
(
    id                  serial not null
        constraint discount_cards_pkey
            primary key,
    discount_percentage integer
);

alter table discount_cards
    owner to postgres;