create table excursion
(
  idexcursion int auto_increment
    primary key,
  idport int not null,
  price mediumtext not null,
  excursionname varchar(100) not null
)
;

create index excursion_port_idport_fk
  on excursion (idport)
;

create table port
(
  idport int auto_increment
    primary key,
  portname varchar(100) not null,
  city varchar(100) not null,
  country varchar(100) not null
)
;

alter table excursion
  add constraint excursion_port_idport_fk
foreign key (idport) references port (idport)
;

create table protected_bank
(
  iduser int not null
    primary key,
  card mediumtext not null,
  money mediumtext not null,
  cvv int not null
)
;

create table route
(
  idroute int auto_increment
    primary key,
  idtour int not null,
  departure timestamp default CURRENT_TIMESTAMP not null,
  arrival timestamp default '0000-00-00 00:00:00' not null,
  idport int not null,
  routename varchar(150) not null,
  constraint route_port_idport_fk
  foreign key (idport) references port (idport)
)
  comment 'if idport = 0, then ship is benn at sea'
;

create index route_port_idport_fk
  on route (idport)
;

create index route_tour_idtour_fk
  on route (idtour)
;

create table ship
(
  idship int auto_increment
    primary key,
  shipname varchar(75) not null,
  capacity int not null
)
;

create table ticket
(
  idticket int auto_increment
    primary key,
  idtour int not null,
  iduser int null,
  arrival timestamp default '0000-00-00 00:00:00' not null,
  departure timestamp default '0000-00-00 00:00:00' not null,
  price mediumtext not null,
  type varchar(100) not null,
  amount_passengers int null
)
;

create index ticket_user_iduser_fk
  on ticket (iduser)
;

create index ticket_tour_idtour_fk
  on ticket (idtour)
;

create table tour
(
  idtour int auto_increment
    primary key,
  tourname varchar(200) not null,
  idship int not null,
  arrival timestamp null,
  departure timestamp default '0000-00-00 00:00:00' not null,
  region varchar(150) not null,
  constraint tour_ship_idship_fk
  foreign key (idship) references ship (idship)
)
;

create index tour_ship_idship_fk
  on tour (idship)
;

alter table route
  add constraint route_tour_idtour_fk
foreign key (idtour) references tour (idtour)
;

alter table ticket
  add constraint ticket_tour_idtour_fk
foreign key (idtour) references tour (idtour)
;

create table user
(
  iduser int auto_increment
    primary key,
  login varchar(75) not null,
  password varchar(150) not null,
  email varchar(100) not null,
  role varchar(10) default 'user' not null
)
;

create trigger user_account
after INSERT on user
for each row
  BEGIN
    INSERT INTO protected_bank VALUES (NEW.iduser, NEW.iduser * 1000, NEW.iduser * 100000 ,(NEW.iduser + 1000 - NEW.iduser * 10));
  END;

alter table ticket
  add constraint ticket_user_iduser_fk
foreign key (iduser) references user (iduser)
;

create table user_excursion
(
  iduser_excursion int auto_increment
    primary key,
  idexcursion int not null,
  idticket int not null
)
;

