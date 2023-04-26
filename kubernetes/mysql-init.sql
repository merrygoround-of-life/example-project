create table cat_fact (
    id bigint not null auto_increment,
    len integer not null,
    fact_key bigint not null,
    fact varchar(2048) not null,
    primary key (id)
);

alter table cat_fact add constraint uk_cat_fact_fact_key unique (fact_key);
