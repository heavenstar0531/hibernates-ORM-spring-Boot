INSERT INTO companies VALUES (1234567, 'LionSoft LLC', ('Russia', 'Tula', 'Lenina ave.', 102, 4, 300026)::address_typ, ARRAY[(1, 'Accounting', ARRAY[(1, 'Igor', 'Morenko', 'President', 5000)::employee_typ, (2, 'Alexander', 'Morenko', 'Manager', 3000)::employee_typ ])::department_typ ])
COMMIT
