CREATE TYPE address_typ AS  (country VARCHAR(200), city VARCHAR(200), street VARCHAR(200), house VARCHAR(200), office VARCHAR(200), "ZIP" NUMERIC(6,0))
CREATE TYPE employee_typ AS ("NUM" NUMERIC, "FIRST_NAME" VARCHAR(200), "LAST_NAME" VARCHAR(200), "JOB" VARCHAR(200), "SALARY" NUMERIC)
CREATE TYPE department_typ AS ("ID" NUMERIC, "NAME" VARCHAR(200), "EMPLOYEES" employee_typ[])
CREATE TABLE companies (inn NUMERIC, name VARCHAR(200), address address_typ, departments department_typ[], CONSTRAINT companies_pk PRIMARY KEY (inn))
