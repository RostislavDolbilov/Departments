CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR (255),
    surname VARCHAR (255),
    patronymic VARCHAR (255),
    gender BOOLEAN,
    berth_day DATE,
    phone_number VARCHAR (255) UNIQUE,
    email VARCHAR (255) UNIQUE,
    employment_date DATE,
    dismissal_date DATE,
    position_id INT,
    salary DOUBLE PRECISION,
    manager BOOLEAN,

    FOREIGN KEY (position_id) REFERENCES position (id)
);

/* gender: true - male, false - female */

