CREATE SEQUENCE IF NOT EXISTS sq_users;

CREATE TABLE IF NOT EXISTS users(
    id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname1 VARCHAR(50),
    surname2 VARCHAR(50),
    email VARCHAR(255),
    phone_number VARCHAR(15),
    nif VARCHAR(11) NOT NULL,
    nickname VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(255),
    entry_date TIMESTAMP NOT NULL,
    cancel_date TIMESTAMP,
    modified_date TIMESTAMP
);

ALTER TABLE users
    ADD CONSTRAINT users_id_pk PRIMARY KEY (id);