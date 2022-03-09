create table category
(
    category_id     Integer primary key,
    department_id   Integer,
    name            character varying(100),
    description     character varying(100)
);