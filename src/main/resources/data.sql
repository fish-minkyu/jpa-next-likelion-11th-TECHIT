INSERT INTO shop (name,description)
VALUES
  ('Tellus Phasellus LLP','Mauris magna. Duis dignissim tempor arcu. Vestibulum ut eros'),
  ('Eu Neque Pellentesque PC','eleifend. Cras sed leo.');

INSERT INTO item_category (name)
VALUES
  ('sit'),
  ('sem,'),
  ('Cum');

INSERT INTO item (shop_id,category_id,name,description,price)
VALUES
  (1,2,'neque','interdum enim non nisi. Aenean eget metus. In',117),
  (1,2,'turpis','quis diam luctus lobortis. Class aptent',199),
  (1,1,'consectetuer','Cras dolor',165),
  (1,2,'Donec','auctor non, feugiat nec, diam.',108),
  (2,3,'aliquet','neque. Sed eget lacus.',167),
  (1,2,'Donec','faucibus id, libero. Donec consectetuer',125),
  (1,1,'arcu','auctor odio',134),
  (1,3,'est,','sollicitudin commodo',195),
  (1,2,'in,','iaculis nec, eleifend non,',132),
  (2,1,'eu','dolor vitae dolor.',112);

INSERT INTO customer (name,phone,email)
VALUES
  ('Tate Andrews','1-734-326-1639','ac@aol.net'),
  ('Audra Carney','1-575-294-4580','lacinia.vitae@hotmail.net'),
  ('Julian Gilmore','(334) 923-7555','nunc.sed@aol.ca'),
  ('Harriet Tate','1-157-137-5173','mauris.nulla@outlook.couk'),
  ('Cruz Schneider','(447) 661-2283','suspendisse.sagittis@yahoo.org'),
  ('Coby Downs','1-428-261-6562','ultrices.posuere@hotmail.edu'),
  ('Rooney Ortiz','1-644-144-3708','et@aol.com'),
  ('Dora Valencia','1-947-653-3217','bibendum.fermentum.metus@google.org'),
  ('Ivor Lane','1-768-945-8979','elit@protonmail.couk'),
  ('Hu Brown','1-654-596-6147','adipiscing.lobortis.risus@aol.edu');
INSERT INTO customer (name,phone,email)
VALUES
  ('Alec Floyd','1-427-685-2842','facilisis@google.com'),
  ('Harrison Ruiz','1-930-748-6793','quis.urna.nunc@aol.com'),
  ('Hayes Mooney','1-187-473-3245','morbi.accumsan@google.org'),
  ('Malcolm King','(567) 838-8408','sociis.natoque@google.ca'),
  ('Delilah Phillips','(735) 620-7674','sed.auctor@protonmail.couk'),
  ('Alma Parks','(274) 676-8745','phasellus.in@google.ca'),
  ('Alea Kaufman','1-545-273-5636','et.malesuada@yahoo.org'),
  ('Sasha Riggs','1-114-116-8356','lorem@aol.couk'),
  ('Neve Stark','(977) 997-5571','ornare.tortor.at@outlook.edu'),
  ('Murphy Jefferson','(965) 183-6102','pede.nunc@protonmail.net');

INSERT INTO payment_method (customer_id,description,type,details)
VALUES
  (1,'natoque penatibus','npay','sapien molestie orci tincidunt adipiscing.'),
  (2,'amet, consectetuer adipiscing elit. Curabitur sed tortor.','kpay','fringilla cursus purus. Nullam scelerisque neque sed'),
  (3,'enim,','','nisi dictum augue malesuada malesuada.'),
  (4,'ligula elit, pretium et, rutrum','toss','id, blandit at, nisi. Cum sociis natoque penatibus'),
  (5,'feugiat non, lobortis quis, pede.','kpay','sed pede. Cum sociis natoque penatibus et magnis dis'),
  (6,'sodales nisi magna sed dui. Fusce aliquam, enim','','quam, elementum at,'),
  (7,'tristique senectus et netus et','toss','lobortis tellus justo'),
  (8,'nisi dictum augue malesuada malesuada. Integer id','kpay','lorem lorem, luctus ut, pellentesque eget, dictum'),
  (9,'parturient montes, nascetur ridiculus mus. Proin vel arcu eu','toss','Suspendisse dui.'),
  (10,'dolor sit amet, consectetuer adipiscing elit. Aliquam auctor, velit','npay','ligula. Nullam enim.');
INSERT INTO payment_method (customer_id,description,type,details)
VALUES
  (11,'orci.','kpay','lacus. Etiam bibendum fermentum metus. Aenean sed pede nec'),
  (12,'nec enim. Nunc ut erat.','account','vestibulum, neque sed dictum eleifend,'),
  (13,'et tristique pellentesque, tellus sem mollis dui, in sodales elit','npay','nisi. Aenean eget metus.'),
  (14,'vitae semper egestas, urna justo faucibus','account','sit amet,'),
  (15,'libero at auctor ullamcorper, nisl arcu','account','sit amet, dapibus id, blandit at, nisi. Cum'),
  (16,'hendrerit consectetuer, cursus et, magna. Praesent','','lorem semper'),
  (17,'aliquet vel, vulputate','npay','Aenean eget metus.'),
  (18,'eget magna. Suspendisse tristique','npay','et magnis dis parturient montes,'),
  (19,'Cras eu tellus','toss','lorem vitae odio sagittis semper. Nam tempor diam dictum sapien.'),
  (20,'lectus rutrum urna, nec luctus felis purus ac tellus.','card','sagittis lobortis mauris. Suspendisse aliquet molestie tellus.');

INSERT INTO customer_order (customer_id,payment_id,address)
VALUES
  (1,1,'Mexico City'),
  (2,2,'Izmail'),
  (3,3,'Chandigarh'),
  (4,4,'Silvan'),
  (5,5,'Port Harcourt'),
  (6,6,'Cork'),
  (7,7,'Hastings'),
  (8,8,'Talisay'),
  (9,9,'Awka'),
  (10,10,'Santa Coloma de Gramenet');
INSERT INTO customer_order (customer_id,payment_id,address)
VALUES
  (11,11,'Puno'),
  (12,12,'São José'),
  (13,13,'Ranchi'),
  (14,14,'Würzburg'),
  (15,15,'Henderson'),
  (16,16,'Scena/Schenna'),
  (17,17,'Arica'),
  (18,18,'Mjölby'),
  (19,19,'Fauske'),
  (20,20,'Cork');
INSERT INTO customer_order (customer_id,payment_id,address)
VALUES
  (1,1,'Queenstown'),
  (2,2,'Jayapura'),
  (3,3,'Hà Giang'),
  (4,4,'Seogwipo'),
  (5,5,'Kuruman'),
  (6,6,'Nikopol'),
  (7,7,'Lakki Marwat'),
  (8,8,'Vigan'),
  (9,9,'Gore'),
  (10,10,'Radom');
INSERT INTO customer_order (customer_id,payment_id,address)
VALUES
  (11,11,'Leoben'),
  (12,12,'San José'),
  (13,13,'Kraków'),
  (14,14,'Queenstown'),
  (15,15,'Horlivka'),
  (16,16,'Kurgan'),
  (17,17,'Kayseri'),
  (18,18,'Stavoren'),
  (19,19,'Manokwari'),
  (20,20,'San Diego');
INSERT INTO customer_order (customer_id,payment_id,address)
VALUES
  (1,1,'Pavlohrad'),
  (2,2,'Scandriglia'),
  (3,3,'Ripacandida'),
  (4,4,'Jilin'),
  (5,5,'Stratford-upon-Avon'),
  (6,6,'Gangneung'),
  (7,7,'Canberra'),
  (8,8,'Pangkalpinang'),
  (9,9,'San Juan del Río'),
  (10,10,'Terme');
INSERT INTO customer_order (customer_id,payment_id,address)
VALUES
  (11,11,'João Pessoa'),
  (12,12,'Bengkulu'),
  (13,13,'Whitehorse'),
  (14,14,'Maiduguri'),
  (15,15,'Nijmegen'),
  (16,16,'New Galloway'),
  (17,17,'Rạch Giá'),
  (18,18,'Nicoya'),
  (19,19,'Vetlanda'),
  (20,20,'Motala');

INSERT INTO order_item (order_id,item_id,count)
VALUES
  (1,3,2),
  (2,2,3),
  (3,3,3),
  (4,4,4),
  (5,3,2),
  (6,2,3),
  (7,2,3),
  (8,4,2),
  (9,4,2),
  (10,2,4);
INSERT INTO order_item (order_id,item_id,count)
VALUES
  (11,3,4),
  (12,4,1),
  (13,2,3),
  (14,1,2),
  (15,3,3),
  (16,3,2),
  (17,4,3),
  (18,4,3),
  (19,2,2),
  (20,3,4);
INSERT INTO order_item (order_id,item_id,count)
VALUES
  (21,4,3),
  (22,2,3),
  (23,5,3),
  (24,2,3),
  (25,3,2),
  (26,1,1),
  (27,2,4),
  (28,2,4),
  (29,3,4),
  (30,1,3);
INSERT INTO order_item (order_id,item_id,count)
VALUES
  (31,5,2),
  (32,4,2),
  (33,3,2),
  (34,2,2),
  (35,1,1),
  (36,4,2),
  (37,2,4),
  (38,2,3),
  (39,3,3),
  (40,5,3);
INSERT INTO order_item (order_id,item_id,count)
VALUES
  (41,3,2),
  (42,3,4),
  (43,3,2),
  (44,4,3),
  (45,3,3),
  (46,5,3),
  (47,1,2),
  (48,2,4),
  (49,4,4),
  (50,4,2);
INSERT INTO order_item (order_id,item_id,count)
VALUES
  (51,1,4),
  (52,4,2),
  (53,3,3),
  (54,4,3),
  (55,4,2),
  (56,4,2),
  (57,3,2),
  (58,2,1),
  (59,5,3),
  (60,2,2);

INSERT INTO order_item (order_id,item_id,count)
VALUES
  (1,8,2),
  (2,7,4),
  (3,7,3),
  (4,9,2),
  (5,6,4),
  (6,7,3),
  (7,8,3),
  (8,7,3),
  (9,7,2),
  (10,7,1);
INSERT INTO order_item (order_id,item_id,count)
VALUES
  (11,9,3),
  (12,9,3),
  (13,7,3),
  (14,7,2),
  (15,7,2),
  (16,7,4),
  (17,7,3),
  (18,8,2),
  (19,9,2),
  (20,7,3);
INSERT INTO order_item (order_id,item_id,count)
VALUES
  (21,10,4),
  (22,9,3),
  (23,10,2),
  (24,9,3),
  (25,8,1),
  (26,7,2),
  (27,8,3),
  (28,9,4),
  (29,8,3),
  (30,7,2);
INSERT INTO order_item (order_id,item_id,count)
VALUES
  (31,6,3),
  (32,8,2),
  (33,8,2),
  (34,9,1),
  (35,8,3),
  (36,7,1),
  (37,8,2),
  (38,9,1),
  (39,7,2),
  (40,6,3);
INSERT INTO order_item (order_id,item_id,count)
VALUES
  (41,8,4),
  (42,7,3),
  (43,10,3),
  (44,8,4),
  (45,10,4),
  (46,9,2),
  (47,9,2),
  (48,7,4),
  (49,8,2),
  (50,10,4);
INSERT INTO order_item (order_id,item_id,count)
VALUES
  (51,7,2),
  (52,6,1),
  (53,10,2),
  (54,10,4),
  (55,9,4),
  (56,9,4),
  (57,7,3),
  (58,9,1),
  (59,9,4),
  (60,9,2);

UPDATE customer_order
SET total_price = cal_total_price FROM
  (SELECT order_id, SUM(oii.count * oii.price) as cal_total_price
   FROM customer_order co INNER JOIN (SELECT * FROM
     order_item oi INNER JOIN item i
                              ON oi.item_id = i.id
   ) oii ON co.id = oii.order_id
   GROUP BY order_id)
WHERE id = order_id;

UPDATE item
SET stock = sleft FROM(
  SELECT item_id, sum(oi.count) as bought, 70 - sum(oi.count) as sleft
  FROM order_item oi INNER JOIN item i
                                ON oi.item_id = i.id
  GROUP BY item_id
                  )
WHERE item.id = item_id;


INSERT INTO instructor (name)
VALUES
  ('Vivien Moran'),
  ('Tanisha Gordon'),
  ('Sharon Harrell'),
  ('Christopher Mcclain'),
  ('Edan Brian'),
  ('Plato Best'),
  ('Uriah Nguyen'),
  ('Jesse Romero'),
  ('Nehru Huff'),
  ('Jolene Roach');


INSERT INTO student (name,age,phone,email,advisor_id)
VALUES
  ('Deirdre Mclean',36,'010-2453-6183','proin.velit@yahoo.couk',null),
  ('Rebecca Ayala',37,'010-8356-8511','in.tempus@protonmail.org',3),
  ('Griffin Joyner',31,'010-5568-0941','in.ornare@icloud.net',4),
  ('Karina Reynolds',38,'010-7756-2174','id.sapien@outlook.net',3),
  ('Ryan Burgess',36,'010-4341-0548','ultrices.iaculis.odio@protonmail.net',9),
  ('Beck Mack',23,'010-5758-9647','in.magna@yahoo.couk',6),
  ('Rooney Beasley',32,'010-2702-3193','lobortis@google.edu',9),
  ('Kelly Nguyen',33,'010-4211-0843','commodo@aol.org',7),
  ('Demetria Barton',23,'010-7823-0136','pellentesque.habitant.morbi@aol.net',7),
  ('Hedy Hardy',24,'010-1763-3441','eu.ligula@protonmail.net',10);
INSERT INTO student (name,age,phone,email,advisor_id)
VALUES
  ('Risa West',25,'010-9586-4657','in.faucibus@protonmail.com',4),
  ('Dillon Cardenas',25,'010-1681-6456','augue.ut@aol.edu',4),
  ('Isaiah Kirby',30,'010-6346-4650','vitae@protonmail.edu',3),
  ('Kareem Flowers',38,'010-9613-6581','a.auctor@outlook.edu',null),
  ('Tiger Hardin',25,'010-9275-9244','et.magnis@yahoo.net',5),
  ('Orson French',39,'010-1013-8332','enim.commodo.hendrerit@outlook.edu',6),
  ('Isaiah Schroeder',21,'010-0604-1786','orci.lacus@protonmail.ca',4),
  ('Jaime Fischer',38,'010-8928-6221','primis.in.faucibus@icloud.couk',5),
  ('Jin Strong',37,'010-2588-2713','hendrerit.donec.porttitor@hotmail.org',8),
  ('Lars Whitaker',21,'010-3347-5135','in.consequat@yahoo.edu',4);
INSERT INTO student (name,age,phone,email,advisor_id)
VALUES
  ('Cairo Baxter',38,'010-1866-1418','magna.cras.convallis@yahoo.com',3),
  ('Wynne Lucas',27,'010-8272-2451','varius.ultrices@google.com',3),
  ('Ian Rowland',34,'010-5366-1711','proin.non@yahoo.net',5),
  ('Hoyt Nieves',38,'010-1779-3633','molestie.sed@outlook.couk',7),
  ('Ethan Francis',23,'010-6152-7722','at@icloud.edu',3),
  ('George Coffey',31,'010-5144-8473','rutrum.eu.ultrices@yahoo.com',10),
  ('Derek Burton',38,'010-3163-4466','lectus.pede@icloud.com',7),
  ('Cullen Cardenas',38,'010-3278-0155','arcu.imperdiet.ullamcorper@hotmail.edu',10),
  ('Zelenia Mcleod',32,'010-0493-6954','lorem.donec@google.edu',4),
  ('Logan Jimenez',37,'010-3858-1106','sapien.aenean@outlook.couk',null);

INSERT INTO lecture (start_time,end_time,name,day,instructor_id)
VALUES
  (9,15,'nec','tue',6),
  (10,13,'Cum sociis','mon',5),
  (11,15,'Sed','thu',5),
  (11,14,'Phasellus in','mon',4),
  (10,14,'Sed','wed',1),
  (10,14,'at','thu',2),
  (10,15,'penatibus et','fri',9),
  (10,13,'aliquam','wed',5),
  (11,13,'vitae','fri',6),
  (11,14,'dolor.','wed',5);
INSERT INTO lecture (start_time,end_time,name,day,instructor_id)
VALUES
  (15,16,'enim','sat',8),
  (13,15,'mollis.','fri',7),
  (12,16,'urna','tue',5),
  (12,18,'purus, accumsan','tue',2),
  (14,16,'tempus mauris','fri',1),
  (13,15,'lobortis','fri',2),
  (13,18,'nisl arcu','fri',9),
  (13,16,'ultrices','wed',4),
  (14,15,'adipiscing','fri',8),
  (12,15,'libero.','mon',2);
INSERT INTO lecture (start_time,end_time,name,day,instructor_id)
VALUES
  (11,15,'euismod','mon',4),
  (9,14,'Cras eu','tue',8),
  (10,15,'pharetra.','sat',9),
  (11,14,'sit','thu',2),
  (11,14,'orci, consectetuer','tue',1),
  (10,13,'mi.','mon',6),
  (11,15,'egestas','mon',6),
  (10,14,'ligula','thu',6),
  (10,13,'arcu imperdiet','thu',5),
  (11,15,'pede blandit','tue',9);

INSERT INTO attending_lectures (student_id,lecture_id)
VALUES
  (11,29),
  (25,6),
  (23,21),
  (14,27),
  (6,3),
  (23,16),
  (12,4),
  (13,30),
  (4,5),
  (15,23);
INSERT INTO attending_lectures (student_id,lecture_id)
VALUES
  (6,7),
  (10,17),
  (29,19),
  (14,11),
  (17,21),
  (16,4),
  (17,1),
  (21,10),
  (28,2),
  (27,26);
INSERT INTO attending_lectures (student_id,lecture_id)
VALUES
  (24,3),
  (6,24),
  (8,29),
  (18,25),
  (15,5),
  (14,18),
  (8,4),
  (15,25),
  (9,12),
  (16,18);
INSERT INTO attending_lectures (student_id,lecture_id)
VALUES
  (14,25),
  (13,25),
  (23,12),
  (18,23),
  (17,20),
  (9,28),
  (6,6),
  (9,5),
  (11,23),
  (2,26);
INSERT INTO attending_lectures (student_id,lecture_id)
VALUES
  (25,20),
  (15,28),
  (20,8),
  (13,14),
  (15,8),
  (8,21),
  (8,20),
  (3,7),
  (14,10),
  (24,5);