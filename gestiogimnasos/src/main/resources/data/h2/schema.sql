-- https://www.h2database.com/html/grammar.html

CREATE TABLE GIMNASOS (

	ID				BIGINT			NOT NULL,
	NOM			VARCHAR(150)	,
	ADRECA		VARCHAR(250)	,
	PREU			DOUBLE			,
	TIPUS			VARCHAR(10)		,
	DATA_INAUGURACIO		DATE			,
	OBERT	BOOLEAN			,
	
	PRIMARY KEY (ID)

);