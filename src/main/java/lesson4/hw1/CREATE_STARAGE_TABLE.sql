CREATE TABLE STORAGE
(ID INT,
CONSTRAINT storage_pk primary key (id),

formatsSupported NVARCHAR2(10) NOT NULL,
storageCountry NVARCHAR2 (20) NOT NULL,
storageMaxSize NUMBER (10) NOT NULL);