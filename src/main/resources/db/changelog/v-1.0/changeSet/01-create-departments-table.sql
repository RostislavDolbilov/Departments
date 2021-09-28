CREATE TABLE departments (
    id SERIAL PRIMARY KEY ,
    parent_id INT,
    department_name VARCHAR(256),
    creation_date DATE,

    FOREIGN KEY (parent_id) REFERENCES departments (id)

);

