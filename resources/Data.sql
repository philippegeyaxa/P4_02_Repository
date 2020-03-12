/* Setting up PROD DB */
create database prod;
use prod;

create table parking(
PARKING_NUMBER int PRIMARY KEY,
AVAILABLE bool NOT NULL,
TYPE varchar(10) NOT NULL
);

create table ticket(
 ID int PRIMARY KEY AUTO_INCREMENT,
 PARKING_NUMBER int NOT NULL,
 VEHICLE_REG_NUMBER varchar(10) NOT NULL,
 PRICE double,
 IN_TIME DATETIME NOT NULL,
 OUT_TIME DATETIME,
 RECURRING bool NOT NULL,
 FOREIGN KEY (PARKING_NUMBER)
 REFERENCES parking(PARKING_NUMBER));

insert into parking(PARKING_NUMBER,AVAILABLE,TYPE) values(1,true,'CAR');
insert into parking(PARKING_NUMBER,AVAILABLE,TYPE) values(2,true,'CAR');
insert into parking(PARKING_NUMBER,AVAILABLE,TYPE) values(3,true,'CAR');
insert into parking(PARKING_NUMBER,AVAILABLE,TYPE) values(4,true,'BIKE');
insert into parking(PARKING_NUMBER,AVAILABLE,TYPE) values(5,true,'BIKE');
commit;

/* Setting up TEST DB */
create database test;
use test;

create table parking(
PARKING_NUMBER int PRIMARY KEY,
AVAILABLE bool NOT NULL,
TYPE varchar(10) NOT NULL
);

create table ticket(
 ID int PRIMARY KEY AUTO_INCREMENT,
 PARKING_NUMBER int NOT NULL,
 VEHICLE_REG_NUMBER varchar(10) NOT NULL,
 PRICE double,
 IN_TIME DATETIME NOT NULL,
 OUT_TIME DATETIME,
 RECURRING bool NOT NULL,
 FOREIGN KEY (PARKING_NUMBER)
 REFERENCES parking(PARKING_NUMBER));

insert into parking(PARKING_NUMBER,AVAILABLE,TYPE) values(1,true,'CAR');
insert into parking(PARKING_NUMBER,AVAILABLE,TYPE) values(2,true,'CAR');
insert into parking(PARKING_NUMBER,AVAILABLE,TYPE) values(3,true,'CAR');
insert into parking(PARKING_NUMBER,AVAILABLE,TYPE) values(4,true,'BIKE');
insert into parking(PARKING_NUMBER,AVAILABLE,TYPE) values(5,true,'BIKE');

create table reference_time(
REFERENCE_VALUE DATETIME NOT NULL
);
insert into reference_time(REFERENCE_VALUE) values ('2020-01-01 00:00:00');

commit;
