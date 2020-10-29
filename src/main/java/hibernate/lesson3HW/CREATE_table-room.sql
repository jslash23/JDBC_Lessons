CREATE TABLE Room (
id INT,
CONSTRAINT Room_pk PRIMARY KEY(id),
hotel_fk INT,
CONSTRAINT room_fk FOREIGN KEY (hotel_fk) REFERENCES Hotel (id),
numberOfGuests NUMBER,
price NUMBER NOT NULL,
breakfastIncluded NUMBER,
petsAllowed NUMBER,
dateAvailableFrom TIMESTAMP
);