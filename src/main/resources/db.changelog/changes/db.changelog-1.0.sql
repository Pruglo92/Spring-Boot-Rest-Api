--liquibase formatted sql

--changeset pruglo-ve:20221122-1 failOnError:true
--comment: Create saved_results table.
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_name = 'roles';
CREATE TABLE IF NOT EXISTS roles
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(50) NOT NULL UNIQUE,
    status  VARCHAR(50) NOT NULL
);

insert into roles (name, status)
values ('ROLE_ADMIN', 'ACTIVE');
insert into roles (name, status)
values ('ROLE_USER', 'ACTIVE');

--changeset pruglo-ve:20221122-2 failOnError:true
--comment:  Create users table.
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_name = 'users';
CREATE TABLE IF NOT EXISTS users (
    id                 BIGSERIAL PRIMARY KEY,
    username           VARCHAR(50) NOT NULL UNIQUE,
    password           VARCHAR(255) NOT NULL,
    first_name         VARCHAR(50) NOT NULL,
    last_name          VARCHAR(50) NOT NULL,
    email              VARCHAR(255) NOT NULL UNIQUE,
    status             VARCHAR(50) NOT NULL    DEFAULT 'ACTIVE',
    date_of_created    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_of_last_visit TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    role_id            INT         NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- password = 'test_password_user' https://bcrypt-generator.com/
insert into users (username, password, first_name, last_name, email, status, date_of_created, date_of_last_visit, role_id)
values (
           'test_username_user', '$2a$12$M90IXJ5WEA/rGdSZNEijsuhUjZyWYWeS0TDTslZfNLzHuC6usvmaC',
           'test_first_name',
           'test_last_name',
           'user@test.com',
           'ACTIVE',
           current_timestamp,
           current_timestamp,
           2
       );

insert into users (username, password, first_name, last_name, email, status, date_of_created, date_of_last_visit, role_id)
values (
           'test_username_admin', '$2a$12$2qNLC9DoqkEF3BJRhv40vOY/vRJhziow1Lb7M9tiMDSp9NSUA5C3O',
           'test_first_name',
           'test_last_name',
           'admin@test.com',
           'ACTIVE',
           current_timestamp,
           current_timestamp,
           1
       );

--changeset pruglo-ve:20221122-3 failOnError:true
--comment: Create invest_index table.
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_name = 'invest_index';
CREATE TABLE IF NOT EXISTS invest_index (
    id                BIGSERIAL    PRIMARY KEY,
    invest_name       VARCHAR(100) NOT NULL
        CONSTRAINT invest_name_uq unique
);

--changeset pruglo-ve:20221122-4 failOnError:true
--comment: Create saved_results table.
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_name = 'saved_results';
CREATE TABLE IF NOT EXISTS saved_results (
    id                BIGSERIAL   PRIMARY KEY,
    invest_index_id    INT         NOT NULL,
    FOREIGN KEY (invest_index_id) REFERENCES invest_index (id),
    users_id          INT         NOT NULL,
    FOREIGN KEY (users_id) REFERENCES users (id),
    period_num        INT         NOT NULL,
    interest_rate     REAL        NOT NULL,
    init_sum          REAL        NOT NULL,
    future_value      REAL,
    accr_per_num      INT         NOT NULL,
    annual_income     REAL,
    total_amount      REAL
);

