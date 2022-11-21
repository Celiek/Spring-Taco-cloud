create table if not exists Ingredient (
  id VARCHAR(4) not null PRIMARY KEY,
  name varchar(25) not null,
  type varchar(10) not null
);

create table if not exists Taco (
  id identity PRIMARY KEY NOT NULL,
  name varchar(50) not null,
  createdAt timestamp not null
);

create table if not exists Taco_Ingredients (
  taco bigint not null REFERENCES Ingredient(id),
  ingredient varchar(4) not null REFERENCES Taco(id)
);

create table if not exists Taco_Order (
	    id identity,
    	deliveryName varchar(50) not null,
    	deliveryStreet varchar(50) not null,
    	deliveryCity varchar(50) not null,
    	deliveryState varchar(2) not null,
    	deliveryZip varchar(10) not null,
    	ccNumber varchar(16) not null,
    	ccExpiration varchar(5) not null,
    	ccCVV varchar(3) not null,
        placedAt timestamp not null
);

create table if not exists Taco_Order_Tacos (
	tacoOrder bigint not null REFERENCES Taco_Order(id),
	taco bigint not null REFERENCES Taco(id)
);
