INSERT INTO `shop`.`roles` (`name`) VALUES
('USER'),
('MANDATOR'),
('ADMIN');

INSERT INTO `shop`.`categories` (`name`) VALUES
('Машинки на радиоуправлении'),
('Джипы на радиоуправлении'),
('Транспорт инерционный');

INSERT INTO `shop`.`addresses` (`zip`, `state`, `city`, `street`, `phone`) VALUES
('610001','UKRAINE','KHARKIV','Kharkivska street 20, 2','0571234567'),
('650001','UKRAINE','ODESSA','Odesska street 100, 231','0631234567'),
('790001','UKRAINE','LVIV','Lvivska street 5/5, 23','0991234567');

INSERT INTO `shop`.`users` (`login`, `password`, `firstName`, `lastName`, `email`, `account`, `age`, `gender`) VALUES
('1','1','FirstName1','LastName1','email1@gmail.com',1111111111,'20',true),
('2','2','FirstName2','LastName2','email2@gmail.com',2222222222,'19',false),
('3','3','FirstName3','LastName3','email3@gmail.com',3333333333,'30',true);

INSERT INTO `shop`.`products` (`name`, `price`, `description`) VALUES
('Машинка Джип Тарзан на радиоуправлении 9003',450,'Радиоуправляемый внедорожник "Тарзан" - это модель в масштабе 1:16, полностью готовая к эксплуатации. Внедорожник Тарзан управляется с помощью пульта дистанционного управления, который имеет рычаговую форму управления.'),
('Машина Джип Пикап 6568-310 / 9000 на радиоуправлении',550,'Джип 6568-310/9000 – оригинальная модель внедорожника на радиоуправлении. Благодаря свободной компоновке, машину можно собирать в различных вариантах, так что это ещё и конструктор.'),
('Машинка инерционная два цвета Lamborghini JT0230',209,'Очень качественная уменьшенная копия Lamborghini. Два цвета - Оранжевый и Черный! Масштаб 1:24. В коробке 30*16,5*12,5СМ');