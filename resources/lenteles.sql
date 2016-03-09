CREATE TABLE USERS
(
	ID           	INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	FIRSTNAME		VARCHAR(50),
	LASTNAME		VARCHAR(50),
	EMAIL			VARCHAR(50) NOT NULL,
	PASSWORD		VARCHAR(32000) NOT NULL,
	IS_APPROVED		INTEGER NOT NULL,
	POINTS			INTEGER NOT NULL,
	GROUP_NUMBER    INTEGER,
	IS_ARCHIVED		INTEGER DEFAULT 0 NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE RESERVATIONS
(
	ID            	INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	DATE_FROM		DATE NOT NULL,
	DATE_TO			DATE NOT NULL,
	USER_ID			INTEGER NOT NULL,
	SUMMERHOUSE_ID	INTEGER NOT NULL,
	IS_APPROVED		INTEGER NOT NULL,
	IS_ARCHIVED		INTEGER DEFAULT 0 NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (USER_ID) REFERENCES USERS (ID),
	FOREIGN KEY (SUMMERHOUSE_ID) REFERENCES SUMMERHOUSES (ID)
);

CREATE TABLE SUMMERHOUSES
(
	ID              INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	TITLE			VARCHAR(500),
	ADDRESS			VARCHAR(500),
	PRICE			INTEGER,
	DESCRIPTION		VARCHAR(2000),
	CAPACITY		INTEGER,
	DATE_FROM		DATE NOT NULL,
	DATE_TO			DATE NOT NULL,
	IS_ARCHIVED		INTEGER DEFAULT 0 NOT NULL,
	PRIMARY KEY (ID)
);



CREATE TABLE SERVICES
(
	ID             INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	TITLE			VARCHAR(500),
	PRICE			INTEGER,
	DESCRIPTION		VARCHAR(2000),
	IS_ARCHIVED		INTEGER DEFAULT 0 NOT NULL,
	PRIMARY KEY (ID)	
);

CREATE TABLE SUMMERHOUSE_SERVICES
(		
	SERVICE_ID	INTEGER NOT NULL,
	SUMMERHOUSE_ID	INTEGER NOT NULL,
	PRIMARY KEY(SERVICE_ID,SUMMERHOUSE_ID),
	FOREIGN KEY (SERVICE_ID) REFERENCES SERVICES (ID),
	FOREIGN KEY (SUMMERHOUSE_ID) REFERENCES SUMMERHOUSES (ID)
);