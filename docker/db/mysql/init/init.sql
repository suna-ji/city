CREATE TABLE TP_CITY (
	CITY_ID BIGINT NOT NULL AUTO_INCREMENT,
	CITY_NAME VARCHAR(40) NOT NULL UNIQUE,
	COUNTRY_ID INT,
	INSERT_DATE_TIME TIMESTAMP,
  UPDATE_DATE_TIME TIMESTAMP,
	PRIMARY KEY (CITY_ID)
);

CREATE TABLE TP_TRAVEL
(
	TRAVEL_ID BIGINT NOT NULL AUTO_INCREMENT,
	USER_ID VARCHAR(40) NOT NULL,
	TRAVEL_START_DATE_TIME TIMESTAMP,
	TRAVEL_END_DATE_TIME TIMESTAMP,
	TRAVEL_STYLE_ID INT,
	PRIMARY KEY (TRAVEL_ID)
);

CREATE TABLE TP_CITY_TRAVEL
(
	TRAVEL_ID BIGINT NOT NULL,
	CITY_ID BIGINT NOT NULL,
	FOREIGN KEY (TRAVEL_ID) REFERENCES TP_TRAVEL(TRAVEL_ID)
	ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (CITY_ID) REFERENCES TP_CITY (CITY_ID)
	ON UPDATE CASCADE ON DELETE CASCADE,
	UNIQUE (TRAVEL_ID, CITY_ID)
);

CREATE TABLE TP_USER
(
	USER_ID VARCHAR(40) NOT NULL,
	USER_NAME VARCHAR(40) NOT NULL,
	INSERT_DATE_TIME TIMESTAMP,
  UPDATE_DATE_TIME TIMESTAMP,
  PRIMARY KEY (USER_ID)
);

CREATE TABLE TP_USER_TRAVEL
(
	USER_ID VARCHAR(40) NOT NULL,
	TRAVEL_ID BIGINT NOT NULL,
	FOREIGN KEY (USER_ID) REFERENCES TP_USER(USER_ID)
	ON UPDATE CASCADE ON DELETE CASCADE,
	UNIQUE (USER_ID, TRAVEL_ID)
);

CREATE TABLE TP_USER_CITY
(
	USER_ID VARCHAR(40) NOT NULL,
	CITY_ID BIGINT NOT NULL,
	VIEWED_COUNT BIGINT,
	VIEWED_DATE_TIME TIMESTAMP,
	FOREIGN KEY (USER_ID) REFERENCES TP_USER(USER_ID)
	ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (CITY_ID) REFERENCES TP_CITY (CITY_ID)
	ON UPDATE CASCADE ON DELETE CASCADE,
	UNIQUE (USER_ID, CITY_ID)
);