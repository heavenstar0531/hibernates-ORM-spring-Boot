/**
 * Author:  Igor Morenko <morenko at lionsoft.ru>
 * Created: Jun 7, 2017
 */

-- Drop

DROP TABLE companies;

DROP TYPE company_typ;

DROP TYPE department_list;

DROP TYPE department_typ;

DROP TYPE employee_list;

DROP TYPE employee_typ;

DROP TYPE address_typ;

-- Create

CREATE TYPE address_typ AS OBJECT (
    "COUNTRY" VARCHAR2(200),
    "CITY"    VARCHAR2(200),
    "STREET"  VARCHAR2(200),
    "HOUSE"   VARCHAR2(200),
    "OFFICE"  VARCHAR2(200),
    "ZIP"     NUMBER(6)
);
/

CREATE TYPE employee_typ AS OBJECT (
    "NUM"        NUMBER,
    "FIRST_NAME" VARCHAR2(200),
    "LAST_NAME"  VARCHAR2(200),
    "JOB"        VARCHAR2(200),
    "SALARY"     NUMBER
);
/

CREATE TYPE employee_list AS TABLE OF employee_typ;
/

CREATE TYPE department_typ AS OBJECT (
    "ID"        NUMBER,
    "NAME"      VARCHAR2(200),
    "EMPLOYEES" EMPLOYEE_LIST
);
/

CREATE TYPE department_list AS TABLE OF department_typ;
/

CREATE TYPE company_typ AS OBJECT (
    "INN"         NUMBER,
    "NAME"        VARCHAR2(200),
    "ADDRESS"     ADDRESS_TYP,
    "DEPARTMENTS" DEPARTMENT_LIST
);
/

CREATE TABLE companies OF company_typ
    NESTED TABLE departments STORE AS department_tab (
      NESTED TABLE employees STORE AS employee_tab
    )
;

ALTER TABLE companies ADD CONSTRAINT companies_pk PRIMARY KEY (inn);

ALTER TABLE department_tab ADD CONSTRAINT departments_pk PRIMARY KEY (id);

ALTER TABLE employee_tab ADD CONSTRAINT employees_pk PRIMARY KEY (num);

-- Load

INSERT INTO companies
VALUES (
    COMPANY_TYP(
        1234567
      , 'LionSoft LLC'
      , ADDRESS_TYP('Russia', 'Tula', 'Lenina ave.', 102, 4, 300026)
      , DEPARTMENT_LIST(
            DEPARTMENT_TYP(
                1
              , 'Accounting'
              , EMPLOYEE_LIST(
                    EMPLOYEE_TYP(1, 'Igor', 'Morenko', 'President', 5000),
                    EMPLOYEE_TYP(2, 'Alexander', 'Morenko', 'Manager', 3000)
                )
            )
        )
    )
);

COMMIT;
