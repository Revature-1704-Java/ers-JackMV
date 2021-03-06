CREATE TABLE EMPLOYEE(
    Employee_ID NUMBER NOT NULL,
    Password VARCHAR2(128),
    Employee_Permissions VARCHAR2(20),
    PRIMARY KEY (Employee_ID)
);


CREATE TABLE REIMBURSEMENT(
    Employee_ID NUMBER NOT NULL,
    Reimbursement_ID NUMBER NOT NULL,
    Amount NUMBER NOT NULL,
    PRIMARY KEY (Reimbursement_ID),
    FOREIGN KEY (Employee_ID) REFERENCES EMPLOYEE (Employee_ID)
);

ALTER TABLE REIMBURSEMENT ADD Description VARCHAR2(256);
ALTER TABLE REIMBURSEMENT ADD ExpenseDate DATE;
ALTER TABLE REIMBURSEMENT MODIFY EXPENSEDATE VARCHAR2(100);

CREATE SEQUENCE SQ_REIMB_PK
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_NEW_REIMB
BEFORE INSERT ON REIMBURSEMENT
FOR EACH ROW
BEGIN
    SELECT SQ_REIMB_PK.NEXTVAL
    INTO :NEW.REIMBURSEMENT_ID FROM DUAL;
END;
/


INSERT INTO EMPLOYEE VALUES (1,'pass','user');
INSERT INTO EMPLOYEE VALUES (2,'pass','user');
INSERT INTO EMPLOYEE VALUES (3,'w0rd','user');
INSERT INTO EMPLOYEE VALUES (4,'w0rd','user');
INSERT INTO EMPLOYEE VALUES (5,'w0rd','user');
INSERT INTO EMPLOYEE VALUES (6,'p4ss','user');
INSERT INTO EMPLOYEE VALUES (7,'p4ss','user');
INSERT INTO EMPLOYEE VALUES (8,'p4ss','user');
INSERT INTO EMPLOYEE VALUES (9,'word','user');
INSERT INTO EMPLOYEE VALUES (10,'word','admin');

INSERT INTO REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, DESCRIPTION, EXPENSEDATE) VALUES (2, 120, 'asbsdf', '12/19/2017');

SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = 1;

Commit;