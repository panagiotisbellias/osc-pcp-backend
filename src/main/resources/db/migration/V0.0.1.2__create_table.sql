CREATE TABLE people.person (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    salutation VARCHAR(255) NOT NULL
);
