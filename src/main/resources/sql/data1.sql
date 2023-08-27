INSERT INTO `donor` (`id`, `created`, `updated`, `visible`, `address`, `phone`, `contact`, `name`) values (1,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Чкалова 1/12В', '+79497158756', 'Виталий Валерьевич Шульман', 'ООО "Добро"');
INSERT INTO `donor` (`id`, `created`, `updated`, `visible`, `address`, `phone`, `contact`, `name`) values (2,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Победы 102Г', '+79497158756', 'Евсеев Витольд Федосеевич', '"CharityFinance"');
INSERT INTO `donor` (`id`, `created`, `updated`, `visible`, `address`, `phone`, `contact`, `name`) values (3,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Костюкова 87/87', '+79497158756', 'Терентьев Арсений Александрович', 'ООО "HumanRelief"');
INSERT INTO `donor` (`id`, `created`, `updated`, `visible`, `address`, `phone`, `contact`, `name`) values (4,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Преображенская 65/22', '+79497158756', 'Баранов Парамон Антонинович', 'ООО "БлаготворительнаяАналитика"');

INSERT INTO `product` (`id`, `created`, `updated`, `visible`, `count`, `description`, `name`, `size`,`type`, `weight`, `image`) values (1,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 15, 'Вода бутылированная по 0.5л, 12 шт в упаковке', 'Вода бутылированная', '35см * 25см * 20см', 'Не газированная', 6, 'вода.webp');
INSERT INTO `product` (`id`, `created`, `updated`, `visible`, `count`, `description`, `name`, `size`,`type`, `weight`, `image`) values (2,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 460, 'Каша Nestlé Молочная гречневая с курагой для продолжения прикорма 200г с пробиотиком BL', 'Детское питание', '11см * 10см', 'Каша', 0.2, 'каша.webp');
INSERT INTO `product` (`id`, `created`, `updated`, `visible`, `count`, `description`, `name`, `size`,`type`, `weight`, `image`) values (3,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 125, 'Консервы мясные Гродфуд "Говядина тушеная", первый сорт, 338 г', 'Тушенка', '20см * 5см * 12см', 'Тушенка из говядины',0.338, 'тушенка.webp');
INSERT INTO `product` (`id`, `created`, `updated`, `visible`, `count`, `description`, `name`, `size`,`type`, `weight`, `image`) values (4,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 190, 'Русский сахар сахарный песок, 1 кг', 'Сахар', '20см * 10см * 5см', 'Каша',1, 'сахар.webp');
INSERT INTO `product` (`id`, `created`, `updated`, `visible`, `count`, `description`, `name`, `size`,`type`, `weight`, `image`) values (5,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 27, 'Резиновые сапоги Двинэм', 'Резиновые сапоги', '39 RU - 45 RU', 'Обувь',0.7, 'сапоги.webp');


INSERT INTO `users` (`id`, `created`, `updated`, `visible`,`enabled`,  `role`, `first_name`, `last_name`, `birth_day`, `email`, `password`, `DTYPE`, `description`, `phone`, `adress`) VALUES (1,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,  true, 'ROLE_ADMIN', 'Иван', 'Корниенко',CURRENT_TIMESTAMP(), 'ivankor839@gmail.com', '$2a$10$QF4NuVRrnhm.6wZGGYBek.gxfqwU4LWkZ639plzaTOUdgFdH1IINi', 'ADMIN' , '', '','');
INSERT INTO `users` (`id`, `created`, `updated`, `visible`, `enabled`,`role`, `first_name`, `last_name`, `birth_day`, `email`, `password`, `DTYPE`, `description`, `phone`, `adress`) VALUES (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,  true, 'ROLE_PERSONAL', 'Леонид', 'Парсай-Салехи', CURRENT_TIMESTAMP(), 'leonkiller@gmail.com', '$2a$10$QF4NuVRrnhm.6wZGGYBek.gxfqwU4LWkZ639plzaTOUdgFdH1IINi','PERSONAL', 'Вода питьевая ', '+79511400843','Победы 85А/14');
INSERT INTO `users` (`id`, `created`, `updated`, `visible`, `enabled`, `role`, `first_name`, `last_name`, `birth_day`, `email`, `password`, `DTYPE`, `description`, `phone`, `adress`) VALUES (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,  true, 'ROLE_PERSONAL', 'Владислав', 'Гончаров', CURRENT_TIMESTAMP() , 'test@ukr.ua', '123', 'PERSONAL',  'Детское питание', '+79506576432','Шорса 17/6');
INSERT INTO `users` (`id`, `created`, `updated`, `visible`, `enabled`, `role`, `first_name`, `last_name`,`birth_day`, `email`, `password`, `DTYPE`, `description`, `phone`, `adress`) VALUES (4,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,  true,  'ROLE_ADMIN', 'admin', 'admin', null, 'admin', 'admin','ADMIN' , '', '','');

INSERT INTO `requirements` (`id`, `created`, `updated`, `visible`, `count`, `status`, `personal_id`, `product_id`) values (1,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 4, 'срочно доставить', 2, 4);
INSERT INTO `requirements` (`id`, `created`, `updated`, `visible`, `count`, `status`, `personal_id`, `product_id`) values (2,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 2, 'срочно доставить', 2, 5);
INSERT INTO `requirements` (`id`, `created`, `updated`, `visible`, `count`, `status`, `personal_id`, `product_id`) values (3,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 3, 'срочно доставить', 3, 1);
INSERT INTO `requirements` (`id`, `created`, `updated`, `visible`, `count`, `status`, `personal_id`, `product_id`) values (4,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 11, 'срочно доставить', 2, 3);
INSERT INTO `requirements` (`id`, `created`, `updated`, `visible`, `count`, `status`, `personal_id`, `product_id`) values (5,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 30, 'срочно доставить', 3, 2);

INSERT INTO `shipments` (`id`, `created`, `updated`, `visible`, `count`, `admin_id`, `personal_id`, `product_id`) values (1,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 4, 1, 2, 4);
INSERT INTO `shipments` (`id`, `created`, `updated`, `visible`, `count`, `admin_id`, `personal_id`, `product_id`) values (2,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 2, 1, 2, 5);
INSERT INTO `shipments` (`id`, `created`, `updated`, `visible`, `count`, `admin_id`, `personal_id`, `product_id`) values (3,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 3, 1, 3, 1);

INSERT INTO `supplies` (`id`, `created`, `updated`, `visible`, `count`, `donor_id`, `product_id`) values (1,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 15, 1, 1);
INSERT INTO `supplies` (`id`, `created`, `updated`, `visible`, `count`, `donor_id`, `product_id`) values (2,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 200, 3, 2);
INSERT INTO `supplies` (`id`, `created`, `updated`, `visible`, `count`, `donor_id`, `product_id`) values (3,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 260, 2, 2);
INSERT INTO `supplies` (`id`, `created`, `updated`, `visible`, `count`, `donor_id`, `product_id`) values (4,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 120, 4, 4);
INSERT INTO `supplies` (`id`, `created`, `updated`, `visible`, `count`, `donor_id`, `product_id`) values (5,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 70, 1, 4);
INSERT INTO `supplies` (`id`, `created`, `updated`, `visible`, `count`, `donor_id`, `product_id`) values (6,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 125, 3, 3);
INSERT INTO `supplies` (`id`, `created`, `updated`, `visible`, `count`, `donor_id`, `product_id`) values (7,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 27, 2, 5);
