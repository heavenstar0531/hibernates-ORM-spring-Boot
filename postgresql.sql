/**
 * Author:  Igor Morenko <morenko at lionsoft.ru>
 * Created: Jun 7, 2017
 */

-- Drop

DROP TABLE companies;

DROP TYPE department_typ;

DROP TYPE employee_typ;

DROP TYPE address_typ;

-- Create

CREATE TYPE address_typ AS  (
    "COUNTRY" VARCHAR(200),
    "CITY"    VARCHAR(200),
    "STREET"  VARCHAR(200),
    "HOUSE"   VARCHAR(200),
    "OFFICE"  VARCHAR(200),
    "ZIP"     NUMERIC(6,0)
);

CREATE TYPE employee_typ AS (
    "NUM"        NUMERIC,
    "FIRST_NAME" VARCHAR(200),
    "LAST_NAME"  VARCHAR(200),
    "JOB"        VARCHAR(200),
    "SALARY"     NUMERIC
);

CREATE TYPE department_typ AS (
    "ID"        NUMERIC,
    "NAME"      VARCHAR(200),
    "EMPLOYEES" employee_typ[]
);

CREATE TABLE companies (
    "INN"         NUMERIC,
    "NAME"        VARCHAR(200),
    "ADDRESS"     address_typ,
    "DEPARTMENTS" department_typ[],
    CONSTRAINT companies_pk PRIMARY KEY ("INN")
);

--ALTER TABLE companies ADD CONSTRAINT companies_pk PRIMARY KEY ("INN");

--ALTER TABLE department_tab ADD CONSTRAINT departments_pk PRIMARY KEY (id);

--ALTER TABLE employee_tab ADD CONSTRAINT employees_pk PRIMARY KEY (num);

-- Load

INSERT INTO companies
VALUES (
    1234567
  , 'LionSoft LLC'
  , ('Russia', 'Tula', 'Lenina ave.', 102, 4, 300026)::address_typ
  , ARRAY[
        (
            1
          , 'Accounting'
          , ARRAY[
                (1, 'Igor', 'Morenko', 'President', 5000)::employee_typ,
                (2, 'Alexander', 'Morenko', 'Manager', 3000)::employee_typ
            ]
        )::department_typ
    ]
);

COMMIT;
