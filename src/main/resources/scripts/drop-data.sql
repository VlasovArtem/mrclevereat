drop table comment if exists;
drop table diet_data if exists;
drop table image if exists;
drop table meal if exists;
drop table meal_product if exists;
drop table product if exists;
drop table recipe if exists;
drop table user if exists;
drop table weight_log if exists;

ALTER TABLE comment DROP ALL CONSTRAINT;
ALTER TABLE diet_data DROP ALL CONSTRAINT;
ALTER TABLE image DROP ALL CONSTRAINT;
ALTER TABLE meal DROP ALL CONSTRAINT;
ALTER TABLE meal_product DROP ALL CONSTRAINT;
ALTER TABLE product DROP ALL CONSTRAINT;
ALTER TABLE recipe DROP ALL CONSTRAINT;
ALTER TABLE user DROP ALL CONSTRAINT;
ALTER TABLE weight_log DROP ALL CONSTRAINT;