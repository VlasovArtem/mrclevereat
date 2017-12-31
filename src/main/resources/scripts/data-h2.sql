INSERT INTO user VALUES (1, 0, '2017-10-31', false, null, null, 30, '1987-10-10', 'test@mail.com', 'test_first', 'test_last', 180, '70617373776f7264', 120, 2, 1000, 80);
INSERT INTO recipe VALUES (1, 1, '2017-10-31', false, null, null, 2, 'test description', false, 10, 'test recipe 1', 300, 100, 10, 120, 'FOR_EVER', 300, 1);
INSERT INTO comment VALUES
  (1, 1, '2017-10-31', false, null, null, 'test comment', 1, 1),
  (2, 1, '2017-12-31', false, null, null, 'test comment 1', 1, 1);
INSERT INTO image VALUES
  (1, 1, '2017-12-31', false, null, null, '70617373776f7264', 1);
INSERT INTO meal VALUES
  (1, 1, '2017-12-30', false, null, null, 300, 50, 10, 160, 200, 1),
  (2, 1, '2017-12-31', false, null, null, 800, 230, 10, 100, 400, 1);
INSERT INTO product VALUES
  (1, 1, '2017-10-30', false, null, null, 'cucumber', 'cucumber', 16, 3.6, 0, 0.7, '11205'),
  (2, 1, '2017-10-30', false, null, null, 'tomato', 'tomato', 18, 3.8, 0, 0.9, '11529'),
  (3, 1, '2017-10-30', false, null, null, 'potato', 'potato', 77, 17, 0, 2, '11352'),
  (4, 1, '2017-10-30', false, null, null, 'carrot', 'carrot', 41, 9.58, 0.24, 0.93, '11124'),
  (5, 1, '2017-10-30', false, null, null, 'Chicken, broilers or fryers, meat only, raw ', 'Chicken meat only', 119, 0, 3.08, 21.39, '05011');
INSERT INTO meal_product VALUES
  (1, 1, '2017-10-30', false, null, null, 100, 1, 1),
  (2, 1, '2017-10-30', false, null, null, 90, 5, 2);
INSERT INTO recipe_product VALUES
  (1, 1, '2017-10-30', false, null, null, 100, 1, 1),
  (2, 1, '2017-10-30', false, null, null, 90, 2, 1),
  (3, 1, '2017-10-30', false, null, null, 120, 5, 1);
INSERT INTO weight_log VALUES
  (1, 1, '2017-10-30', false, null, null, 117, 1);