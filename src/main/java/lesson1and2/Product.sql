CREATE TABLE PRODUCT(
ID NUMBER NOT NULL ENABLE,
CONSTRAINT PRODUCT_PK PRIMARY KEY(ID),
NAME NVARCHAR2(1000) NOT NULL,
DESCRIPTION CLOB,
PRICE NUMBER NOT NULL
)