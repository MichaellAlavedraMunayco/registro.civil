CREATE TABLE person (
  id number(10) UNIQUE PRIMARY KEY,
  dni varchar2(8) DEFAULT "00000000" NOT NULL,
  fullname varchar2(255) DEFAULT "unknown" NOT NULL,
  email varchar2(50) DEFAULT "unknown@unknown" NOT NULL,
  birthdate varchar2(10) DEFAULT "00/00/0000" NOT NULL,
  age varchar2(2) DEFAULT "00" NOT NULL,
  gender varchar2(10) DEFAULT "unsigned" NOT NULL,
  PRIMARY KEY (id)
);
-- Generate ID using sequence and trigger
CREATE SEQUENCE person_seq START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER person_seq_tr BEFORE
INSERT ON person FOR EACH ROW
  WHEN (NEW.id IS NULL) BEGIN
SELECT person_seq.NEXTVAL INTO :NEW.id
FROM DUAL;
END;
--
CREATE TABLE contact (
  id number(10) UNIQUE PRIMARY KEY,
  address varchar2(255) DEFAULT "unknown" NOT NULL,
  homephone varchar2(10) DEFAULT "000 000" NOT NULL,
  phone varchar2(15) DEFAULT "00 000 000 000" NOT NULL,
  personId number(10) NOT NULL
);
-- Generate ID using sequence and trigger
CREATE SEQUENCE contact_seq START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER contact_seq_tr BEFORE
INSERT ON contact FOR EACH ROW
  WHEN (NEW.id IS NULL) BEGIN
SELECT contact_seq.NEXTVAL INTO :NEW.id
FROM DUAL;
END;
--
ALTER TABLE contact
ADD FOREIGN KEY (personId) REFERENCES person (id);
CREATE UNIQUE INDEX person_index_0 ON person (id);