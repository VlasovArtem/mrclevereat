create table user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  created_by BIGINT NOT NULL,
  created_date BINARY(255),
  is_deleted BOOLEAN not null,
  modified_by BIGINT,
  modified_date BINARY(255),
  age INTEGER DEFAULT 0,
  birthday BINARY(255),
  email VARCHAR(255) NOT NULL UNIQUE,
  first_name VARCHAR(255),
  height SMALLINT,
  last_name VARCHAR(255),
  password BINARY(255) NOT NULL,
  weight DOUBLE,
  diet_data_id BIGINT
);

CREATE TABLE diet_data (
  id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  created_by BIGINT NOT NULL,
  created_date BINARY(255),
  is_deleted BOOLEAN not null,
  modified_by BIGINT,
  modified_date BINARY(255),
  activity_score TINYINT DEFAULT 1,
  grams_per_week INTEGER DEFAULT 1000,
  target_weight DOUBLE NOT NULL,
  owner_id BIGINT NOT NULL
);

create table recipe (
  id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  created_by BIGINT NOT NULL,
  created_date BINARY(255),
  is_deleted BOOLEAN not null,
  modified_by BIGINT,
  modified_date BINARY(255),
  complexity INTEGER,
  description VARCHAR(255),
  is_shared BOOLEAN DEFAULT FALSE,
  likes INTEGER DEFAULT 0,
  name VARCHAR(255),
  calories DOUBLE NOT NULL,
  carbohydrate DOUBLE NOT NULL,
  fat DOUBLE NOT NULL,
  protein DOUBLE NOT NULL,
  recipe_visibility VARCHAR(255),
  volume DOUBLE NOT NULL,
  owner_id BIGINT
);

CREATE TABLE comment (
  id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  created_by BIGINT NOT NULL,
  created_date BINARY(255),
  is_deleted BOOLEAN not null,
  modified_by BIGINT,
  modified_date BINARY(255),
  comment VARCHAR(255),
  owner_id BIGINT NOT NULL,
  recipe_id BIGINT NOT NULL
);

create table image (
  id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  created_by BIGINT NOT NULL,
  created_date BINARY(255),
  is_deleted BOOLEAN not null,
  modified_by BIGINT,
  modified_date BINARY(255),
  image BINARY(255),
  recipe_id BIGINT
);

create table meal (
  id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  created_by BIGINT NOT NULL,
  created_date BINARY(255),
  is_deleted BOOLEAN not null,
  modified_by BIGINT,
  modified_date BINARY(255),
  calories DOUBLE NOT NULL ,
  carbohydrate DOUBLE NOT NULL ,
  fat DOUBLE NOT NULL,
  protein DOUBLE NOT NULL,
  volume DOUBLE NOT NULL,
  owner_id BIGINT
);

create table product (
  id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  created_by BIGINT NOT NULL,
  created_date BINARY(255),
  is_deleted BOOLEAN not null,
  modified_by BIGINT,
  modified_date BINARY(255),
  description VARCHAR(255),
  name VARCHAR(255) NOT NULL UNIQUE,
  calories DOUBLE NOT NULL,
  carbohydrate DOUBLE NOT NULL,
  fat DOUBLE NOT NULL,
  protein DOUBLE NOT NULL,
  usda_number VARCHAR(255)
);

create table meal_product (
  id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  created_by BIGINT NOT NULL,
  created_date BINARY(255),
  is_deleted BOOLEAN not null,
  modified_by BIGINT,
  modified_date BINARY(255),
  volume DOUBLE NOT NULL,
  product_id BIGINT,
  meal_id BIGINT,
  recipe_id BIGINT
);

create table weight_log (
  id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  created_by BIGINT NOT NULL,
  created_date BINARY(255),
  is_deleted BOOLEAN not null,
  modified_by BIGINT,
  modified_date BINARY(255),
  weight DOUBLE NOT NULL ,
  diet_data_id BIGINT
);

alter table comment add constraint FKa2ug5h92rjf7fx792l15h2tr1 foreign key (owner_id) references user;
alter table comment add constraint FK9rxd7g6o4ghs8gsl1pry86ffc foreign key (recipe_id) references recipe;
alter table diet_data add constraint FK8uq63fxcpv7yemsjuih2fy3ei foreign key (owner_id) references user;
alter table image add constraint FKhrygoy3c1iq66isgm2so0p14x foreign key (recipe_id) references recipe;
alter table meal add constraint FK666vlyks49ly2dd6bjv356gcr foreign key (owner_id) references user;
alter table meal_product add constraint FKmc6xw2hiwsswj76kd1ug4rrne foreign key (product_id) references product;
alter table meal_product add constraint FKdrwyhir8fc0eq5hcvvptpbx70 foreign key (recipe_id) references recipe;
alter table meal_product add constraint FKrlos5xqxe0ghvmuu2hrlt0m31 foreign key (meal_id) references meal;
alter table user add constraint FKbnsm0gevbxkpmwnnmyqjgopl4 foreign key (diet_data_id) references diet_data;
alter table weight_log add constraint FKfwejuf54euxyvstmftnp5wd7j foreign key (diet_data_id) references diet_data;

create index NAME_IDX on product (name);
create index USDANUM_IDX on product (usda_number);
create index RCP_NAME_IDX on recipe (name);
create index EMAIL_IDX on user (email);