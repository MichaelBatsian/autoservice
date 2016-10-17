SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `autoservice_db` DEFAULT CHARACTER SET utf8 ;
USE `autoservice_db` ;

-- -----------------------------------------------------
-- Table `autoservice_db`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `autoservice_db`.`roles` ;

CREATE  TABLE IF NOT EXISTS `autoservice_db`.`roles` (
  `role_id` INT NOT NULL ,
  `role` VARCHAR(150) NULL ,
  PRIMARY KEY (`role_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoservice_db`.`users`
-- -----------------------------------------------------  
DROP TABLE IF EXISTS `autoservice_db`.`users` ;

CREATE  TABLE IF NOT EXISTS `autoservice_db`.`users` (
  `user_id` INT NOT NULL ,
  `login` VARCHAR(45) NULL ,
  `password` VARCHAR(50) NULL ,
  `role_id` INT NULL ,
  `blocked_status` ENUM('активен','заблокирован') DEFAULT 'активен',
  PRIMARY KEY (`user_id`) ,
  INDEX `role_id` (`role_id` ASC) ,
  CONSTRAINT `role_fk`
    FOREIGN KEY (`role_id` )
    REFERENCES `autoservice_db`.`roles` (`role_id` )
    ON DELETE NO ACTION
   )
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `autoservice_db`.`employee_position`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `autoservice_db`.`employee_position` ;

CREATE  TABLE IF NOT EXISTS `autoservice_db`.`employee_position` (
  `position_id` INT NOT NULL ,
  `position` VARCHAR(250) NULL ,
  PRIMARY KEY (`position_id`) )
 ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoservice_db`.`userdata`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `autoservice_db`.`usersdata` ;

CREATE  TABLE IF NOT EXISTS `autoservice_db`.`usersdata` (
  `userdata_id` INT NOT NULL ,
  `data_user_id` INT NOT NULL ,
  `fullname` VARCHAR(200) NULL ,
  `birthday`DATE NULL ,
  `adress` VARCHAR(200) NULL ,
  `phone` VARCHAR(100) NULL  NULL ,
  `gender` ENUM('мужской','женский') NULL,
  PRIMARY KEY (`userdata_id`) ,
  INDEX `data_user_id` (`data_user_id` ASC) ,
  CONSTRAINT `data_user_id`
    FOREIGN KEY (`data_user_id` )
    REFERENCES `autoservice_db`.`users` (`user_id` )
    ON DELETE NO ACTION
   )
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `autoservice_db`.`service_station`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `autoservice_db`.`service_station` ;

CREATE  TABLE IF NOT EXISTS `autoservice_db`.`service_station` (
  `station_id` INT NOT NULL ,
  `station_name` VARCHAR(150) NULL ,
  `location` VARCHAR(150) NULL ,
  PRIMARY KEY (`station_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoservice_db`.`services`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `autoservice_db`.`services` ;

CREATE  TABLE IF NOT EXISTS `autoservice_db`.`services` (
  `service_id` INT NOT NULL ,
  `service_type` VARCHAR(350) NULL ,
  `man_hours` INT NULL ,
  `price` INT NULL ,
  PRIMARY KEY (`service_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoservice_db`.`discounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `autoservice_db`.`discounts` ;

CREATE  TABLE IF NOT EXISTS `autoservice_db`.`discounts` (
  `discount_id` INT NOT NULL ,
  `discount` DECIMAL(3,2) NULL ,
  `totalsum` INT NULL ,
  PRIMARY KEY (`discount_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoservice_db`.`client_discount`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `autoservice_db`.`client_discount` ;

CREATE  TABLE IF NOT EXISTS `autoservice_db`.`client_discount` (
  `client_discount_id` INT NOT NULL ,
  `user_id` INT NULL ,
  `discount_id` INT NULL ,
  `client_totalsum` INT NULL ,
   PRIMARY KEY (`client_discount_id`) ,
  INDEX `user_id` (`user_id` ASC) ,
  INDEX `discount_id` (`discount_id` ASC) ,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `autoservice_db`.`users` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `discount_id`
    FOREIGN KEY (`discount_id` )
    REFERENCES `autoservice_db`.`discounts` (`discount_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;




-- -----------------------------------------------------
-- Table `autoservice_db`.`employee_list`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `autoservice_db`.`employee_list` ;

CREATE  TABLE IF NOT EXISTS `autoservice_db`.`employee_list` (
  `employee_id` INT NOT NULL ,
  `fk_userdata_id` INT NULL ,
  `fk_position_id` INT NULL ,
  `fk_station_id` INT NULL ,
  PRIMARY KEY (`employee_id`) ,
  INDEX `fk_userdata_id` (`fk_userdata_id` ASC) ,
  INDEX `fk_position_id` (`fk_position_id` ASC) ,
  INDEX `fk_station_id` (`fk_station_id` ASC) ,
	FOREIGN KEY (`fk_userdata_id` )
    REFERENCES `autoservice_db`.`usersdata` (`userdata_id`),
    FOREIGN KEY (`fk_position_id` )
    REFERENCES `autoservice_db`.`employee_position` (`position_id`),
    FOREIGN KEY (`fk_station_id` )
    REFERENCES `autoservice_db`.`service_station` (`station_id`)
    )
    ENGINE = InnoDB;
    
    
-- -----------------------------------------------------
-- Table `autoservice_db`.`time_table`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `autoservice_db`.`time_table` ;

CREATE  TABLE IF NOT EXISTS `autoservice_db`.`time_table` (
  `time_table_id` INT NOT NULL ,
  `employee_id` INT NULL ,
  `date` DATE NULL ,
  `time` TIME NULL ,
  `order_id` INT DEFAULT 1,
  PRIMARY KEY (`time_table_id`) ,
  INDEX `emploee_id` (`employee_id` ASC) ,
  CONSTRAINT `emploee_id`
    FOREIGN KEY (`employee_id` )
    REFERENCES `autoservice_db`.`employee_list` (`employee_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
     CONSTRAINT `order_id`
    FOREIGN KEY (`order_id` )
    REFERENCES `autoservice_db`.`orders` (`order_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoservice_db`.`invoice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `autoservice_db`.`invoice` ;

CREATE  TABLE IF NOT EXISTS `autoservice_db`.`invoice` (
  `invoice_id` INT NOT NULL ,
  `status` boolean default false ,
  PRIMARY KEY (`invoice_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoservice_db`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `autoservice_db`.`orders` ;

CREATE  TABLE IF NOT EXISTS `autoservice_db`.`orders` (
  `order_id` INT NOT NULL ,
  `fk_user_id` INT NULL ,
  `fk_service_station_id` INT NULL ,
  `status` enum('оплачен','не оплачен') DEFAULT 'не оплачен' ,
  `current_totalsum` INT DEFAULT 0 ,
  `discount_price` INT DEFAULT 0,
  `fk_invoice_id` INT NULL ,
  PRIMARY KEY (`order_id`) ,
  INDEX `fk_user_id` (`fk_user_id` ASC) ,
  INDEX `fk_service_station_id` (`fk_service_station_id` ASC) ,
  INDEX `fk_invoice_id` (`fk_invoice_id` ASC) ,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`fk_user_id` )
    REFERENCES `autoservice_db`.`users` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_station_id`
    FOREIGN KEY (`fk_service_station_id` )
    REFERENCES `autoservice_db`.`services_to_stations` (`service_station_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoice_id`
    FOREIGN KEY (`fk_invoice_id` )
    REFERENCES `autoservice_db`.`invoice` (`invoice_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoservice_db`.`forum`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `autoservice_db`.`forum` ;

CREATE  TABLE IF NOT EXISTS `autoservice_db`.`forum` (
  `topic_id` INT NOT NULL ,
  `topic` VARCHAR(255) NULL ,
  PRIMARY KEY (`topic_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoservice_db`.`messages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `autoservice_db`.`messages` ;

CREATE  TABLE IF NOT EXISTS `autoservice_db`.`messages` (
  `message_id` INT NOT NULL ,
  `fk_user_id_` INT NULL ,
  `message` TEXT NULL ,
  `fk_topic_id` INT NULL ,
  `message_datetime` DATETIME DEFAULT NOW(),
  PRIMARY KEY (`message_id`) ,
  INDEX `fk_user_id_` (`fk_user_id_` ASC) ,
  INDEX `fk_topic_id` (`fk_topic_id` ASC) ,
  CONSTRAINT `fk_user_id_`
    FOREIGN KEY (`fk_user_id_` )
    REFERENCES `autoservice_db`.`users` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_topic_id`
    FOREIGN KEY (`fk_topic_id` )
    REFERENCES `autoservice_db`.`forum` (`topic_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `autoservice_db`.`services_to_stations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `autoservice_db`.`services_to_stations` ;

CREATE  TABLE IF NOT EXISTS `autoservice_db`.`services_to_stations` (
  `service_station_id` INT NOT NULL ,
  `fk_station_id_` INT NOT NULL ,
  `fk_service_id_` INT NOT NULL ,
  PRIMARY KEY (`service_station_id`) ,
  INDEX `fk_station_id_` (`fk_station_id_` ASC) ,
  INDEX `fk_service_id_` (`fk_service_id_` ASC) ,
  CONSTRAINT `fk_station_id_`
    FOREIGN KEY (`fk_station_id_` )
    REFERENCES `autoservice_db`.`service_station` (`station_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_id_`
    FOREIGN KEY (`fk_service_id_` )
    REFERENCES `autoservice_db`.`services` (`service_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `autoservice_db`.`position_service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `autoservice_db`.`position_service` ;

CREATE  TABLE IF NOT EXISTS `autoservice_db`.`position_service` (
  `position_service_id` INT NOT NULL ,
  `fk_position_id_` INT NOT NULL ,
  `fk_serviceid_` INT NOT NULL ,
  PRIMARY KEY (`position_service_id`) ,
  INDEX `fk_position_id_` (`fk_position_id_` ASC) ,
  INDEX `fk_serviceid_` (`fk_serviceid_` ASC) ,
  CONSTRAINT `fk_position_id_`
    FOREIGN KEY (`fk_position_id_` )
    REFERENCES `autoservice_db`.`employee_position` (`position_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_serviceid_`
    FOREIGN KEY (`fk_serviceid_` )
    REFERENCES `autoservice_db`.`services` (`service_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



INSERT INTO `autoservice_db`.`roles` (`role_id`, `role`) VALUES (1, 'администратор');
INSERT INTO `autoservice_db`.`roles` (`role_id`, `role`) VALUES (2, 'менеджер');
INSERT INTO `autoservice_db`.`roles` (`role_id`, `role`) VALUES (3, 'коллцентр');
INSERT INTO `autoservice_db`.`roles` (`role_id`, `role`) VALUES (4, 'мастерская');
INSERT INTO `autoservice_db`.`roles` (`role_id`, `role`) VALUES (5, 'пользователь');

INSERT INTO `autoservice_db`.`users` (`user_id`, `login`,`password`,`role_id`) VALUES (1, 'Десяткин', 1234,1);
INSERT INTO `autoservice_db`.`users` (`user_id`, `login`,`password`,`role_id`) VALUES (2, 'Батян', 1234,2);
INSERT INTO `autoservice_db`.`users` (`user_id`, `login`,`password`,`role_id`) VALUES (3, 'Бермичева', 1234,3);
INSERT INTO `autoservice_db`.`users` (`user_id`, `login`,`password`,`role_id`) VALUES (4, 'Петров', 1234,4);
INSERT INTO `autoservice_db`.`users` (`user_id`, `login`,`password`,`role_id`) VALUES (5, 'Ярмаков', 1234,4);
INSERT INTO `autoservice_db`.`users` (`user_id`, `login`,`password`,`role_id`) VALUES (6, 'Иванов', 1234,4);
INSERT INTO `autoservice_db`.`users` (`user_id`, `login`,`password`,`role_id`) VALUES (7, 'Попан', 1234,4);
INSERT INTO `autoservice_db`.`users` (`user_id`, `login`,`password`,`role_id`) VALUES (8, 'Слабухо', 1234,4);
INSERT INTO `autoservice_db`.`users` (`user_id`, `login`,`password`,`role_id`) VALUES (9, 'Новик', 1234,4);
INSERT INTO `autoservice_db`.`users` (`user_id`, `login`,`password`,`role_id`) VALUES (10, 'first@one.com', 1,5);

INSERT INTO `autoservice_db`.`usersdata` (`userdata_id`, `data_user_id`,`fullname`,`birthday`,`adress`,`phone`,`gender`) VALUES (1,4,'Петров Петр Петрович','01-01-01','Минск','123 123','мужской');
INSERT INTO `autoservice_db`.`usersdata` (`userdata_id`, `data_user_id`,`fullname`,`birthday`,`adress`,`phone`,`gender`) VALUES (2,5,'Ярмаков Александр Петрович','01-01-01','Минск','123 123','мужской');
INSERT INTO `autoservice_db`.`usersdata` (`userdata_id`, `data_user_id`,`fullname`,`birthday`,`adress`,`phone`,`gender`) VALUES (3,6,'Иванов Иван Иванович','01-01-01','Минск','123 123','мужской');
INSERT INTO `autoservice_db`.`usersdata` (`userdata_id`, `data_user_id`,`fullname`,`birthday`,`adress`,`phone`,`gender`) VALUES (4,7,'Попан Константин Николаевич','01-01-01','Минск','123 123','мужской');
INSERT INTO `autoservice_db`.`usersdata` (`userdata_id`, `data_user_id`,`fullname`,`birthday`,`adress`,`phone`,`gender`) VALUES (5,8,'Слабухо Алекс Николаевич','01-01-01','Минск','123 123','мужской');
INSERT INTO `autoservice_db`.`usersdata` (`userdata_id`, `data_user_id`,`fullname`,`birthday`,`adress`,`phone`,`gender`) VALUES (6,9,'Новик Алексей Петрович','01-01-01','Минск','123 123','мужской');
INSERT INTO `autoservice_db`.`usersdata` (`userdata_id`, `data_user_id`,`fullname`,`birthday`,`adress`,`phone`,`gender`) VALUES (7,1,'Десяткин Евгений Эдуардович','01-01-01','Минск','123 123','мужской');
INSERT INTO `autoservice_db`.`usersdata` (`userdata_id`, `data_user_id`,`fullname`,`birthday`,`adress`,`phone`,`gender`) VALUES (8,3,'Бермичева Анна','01-01-01','Минск','123 123','мужской');
INSERT INTO `autoservice_db`.`usersdata` (`userdata_id`, `data_user_id`,`fullname`,`birthday`,`adress`,`phone`,`gender`) VALUES (9,2,'Батян Михаил Александрович','01-01-01','Минск','123 123','мужской');
INSERT INTO `autoservice_db`.`usersdata` (`userdata_id`, `data_user_id`,`fullname`,`birthday`,`adress`,`phone`,`gender`) VALUES (10,10,'Саныч','01-01-01','Минск','123 123','мужской');

INSERT INTO `autoservice_db`.`discounts` (`discount_id`, `discount`,`totalsum`) VALUES (1, 0.02, 0);
INSERT INTO `autoservice_db`.`discounts` (`discount_id`, `discount`,`totalsum`) VALUES (2, 0.05, 1500000);
INSERT INTO `autoservice_db`.`discounts` (`discount_id`, `discount`,`totalsum`) VALUES (3, 0.07, 2500000);
INSERT INTO `autoservice_db`.`discounts` (`discount_id`, `discount`,`totalsum`) VALUES (4, 0.1, 4000000);
INSERT INTO `autoservice_db`.`discounts` (`discount_id`, `discount`,`totalsum`) VALUES (5, 0.15, 6000000);

INSERT INTO `autoservice_db`.`client_discount` (`client_discount_id`, `user_id`,`discount_id`,`client_totalsum`) VALUES (1, 10,1, 0);


INSERT INTO `autoservice_db`.`service_station` (`station_id`, `station_name`,`location`) VALUES (1, 'Сухаревская', 'г. Минск, ул. Горецкого, 35');
INSERT INTO `autoservice_db`.`service_station` (`station_id`, `station_name`,`location`) VALUES (2, 'Кунцевщина', 'г. Минск, ул. Притыцкого, 62');
INSERT INTO `autoservice_db`.`service_station` (`station_id`, `station_name`,`location`) VALUES (3, 'Партизанский', 'г. Минск, пр. Партизанский,31');

INSERT INTO `autoservice_db`.`employee_position` (`position_id`, `position`) VALUES (1, 'менеджер сети');
INSERT INTO `autoservice_db`.`employee_position` (`position_id`, `position`) VALUES (2, 'администратор');
INSERT INTO `autoservice_db`.`employee_position` (`position_id`, `position`) VALUES (3, 'Мастер 1-категория');
INSERT INTO `autoservice_db`.`employee_position` (`position_id`, `position`) VALUES (4, 'Мастер 2-категория');
INSERT INTO `autoservice_db`.`employee_position` (`position_id`, `position`) VALUES (5, 'Специалист коллцентра');

INSERT INTO `autoservice_db`.`services` (`service_id`, `service_type`,`man_hours`,`price`) VALUES (1,'Шиномантаж',1,300000);
INSERT INTO `autoservice_db`.`services` (`service_id`, `service_type`,`man_hours`,`price`) VALUES (2,'Замена масла',1,300000);
INSERT INTO `autoservice_db`.`services` (`service_id`, `service_type`,`man_hours`,`price`) VALUES (3,'Замена ГРМ',3,1800000);
INSERT INTO `autoservice_db`.`services` (`service_id`, `service_type`,`man_hours`,`price`) VALUES (4,'Замена сцепления',2,1000000);
INSERT INTO `autoservice_db`.`services` (`service_id`, `service_type`,`man_hours`,`price`) VALUES (5,'Замена фильтров',1,100000);

INSERT INTO `autoservice_db`.`position_service` (`position_service_id`, `fk_position_id_`,`fk_serviceid_`) VALUES (2,3,2);
INSERT INTO `autoservice_db`.`position_service` (`position_service_id`, `fk_position_id_`,`fk_serviceid_`) VALUES (3,3,3);
INSERT INTO `autoservice_db`.`position_service` (`position_service_id`, `fk_position_id_`,`fk_serviceid_`) VALUES (4,3,4);
INSERT INTO `autoservice_db`.`position_service` (`position_service_id`, `fk_position_id_`,`fk_serviceid_`) VALUES (5,3,5);
INSERT INTO `autoservice_db`.`position_service` (`position_service_id`, `fk_position_id_`,`fk_serviceid_`) VALUES (6,4,2);
INSERT INTO `autoservice_db`.`position_service` (`position_service_id`, `fk_position_id_`,`fk_serviceid_`) VALUES (7,4,5);
INSERT INTO `autoservice_db`.`position_service` (`position_service_id`, `fk_position_id_`,`fk_serviceid_`) VALUES (1,4,1);

INSERT INTO `autoservice_db`.`services_to_stations` (`service_station_id`, `fk_station_id_`,`fk_service_id_`) VALUES (1,1,1);
INSERT INTO `autoservice_db`.`services_to_stations` (`service_station_id`, `fk_station_id_`,`fk_service_id_`) VALUES (2,1,2);
INSERT INTO `autoservice_db`.`services_to_stations` (`service_station_id`, `fk_station_id_`,`fk_service_id_`) VALUES (3,1,3);
INSERT INTO `autoservice_db`.`services_to_stations` (`service_station_id`, `fk_station_id_`,`fk_service_id_`) VALUES (4,2,4);
INSERT INTO `autoservice_db`.`services_to_stations` (`service_station_id`, `fk_station_id_`,`fk_service_id_`) VALUES (5,2,5);
INSERT INTO `autoservice_db`.`services_to_stations` (`service_station_id`, `fk_station_id_`,`fk_service_id_`) VALUES (6,2,1);
INSERT INTO `autoservice_db`.`services_to_stations` (`service_station_id`, `fk_station_id_`,`fk_service_id_`) VALUES (7,3,1);
INSERT INTO `autoservice_db`.`services_to_stations` (`service_station_id`, `fk_station_id_`,`fk_service_id_`) VALUES (8,3,2);
INSERT INTO `autoservice_db`.`services_to_stations` (`service_station_id`, `fk_station_id_`,`fk_service_id_`) VALUES (9,3,3);
INSERT INTO `autoservice_db`.`services_to_stations` (`service_station_id`, `fk_station_id_`,`fk_service_id_`) VALUES (10,3,4);
INSERT INTO `autoservice_db`.`services_to_stations` (`service_station_id`, `fk_station_id_`,`fk_service_id_`) VALUES (11,3,5);

INSERT INTO `autoservice_db`.`employee_list` (`employee_id`, `fk_userdata_id`,`fk_position_id`,`fk_station_id`) VALUES (1,1,3,1);
INSERT INTO `autoservice_db`.`employee_list` (`employee_id`, `fk_userdata_id`,`fk_position_id`,`fk_station_id`) VALUES (2,2,4,1);
INSERT INTO `autoservice_db`.`employee_list` (`employee_id`, `fk_userdata_id`,`fk_position_id`,`fk_station_id`) VALUES (3,3,3,2);
INSERT INTO `autoservice_db`.`employee_list` (`employee_id`, `fk_userdata_id`,`fk_position_id`,`fk_station_id`) VALUES (4,4,4,2);
INSERT INTO `autoservice_db`.`employee_list` (`employee_id`, `fk_userdata_id`,`fk_position_id`,`fk_station_id`) VALUES (5,5,3,3);
INSERT INTO `autoservice_db`.`employee_list` (`employee_id`, `fk_userdata_id`,`fk_position_id`,`fk_station_id`) VALUES (6,6,4,3);
INSERT INTO `autoservice_db`.`employee_list` (`employee_id`, `fk_userdata_id`,`fk_position_id`,`fk_station_id`) VALUES (7,7,2,1);
INSERT INTO `autoservice_db`.`employee_list` (`employee_id`, `fk_userdata_id`,`fk_position_id`,`fk_station_id`) VALUES (8,8,5,1);
INSERT INTO `autoservice_db`.`employee_list` (`employee_id`, `fk_userdata_id`,`fk_position_id`,`fk_station_id`) VALUES (9,9,1,1);

INSERT INTO `autoservice_db`.`forum` (`topic_id`, `topic`) VALUES (1,'Пожелания и отзывы');
INSERT INTO `autoservice_db`.`forum` (`topic_id`, `topic`) VALUES (2,'Задайте вопрос мастеру');

INSERT INTO `autoservice_db`.`orders` (`order_id`, `fk_user_id`,`fk_service_station_id`,`status`,`current_totalsum`,`fk_invoice_id`) VALUES (1,NULL,NULL,NULL,NULL,NULL);


INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (1,1,'2016-01-10','09:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (2,1,'2016-01-10','10:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (3,1,'2016-01-10','11:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (4,1,'2016-01-10','12:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (5,1,'2016-01-10','13:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (6,1,'2016-01-10','14:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (7,1,'2016-01-10','15:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (8,1,'2016-01-10','16:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (9,1,'2016-01-10','17:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (10,1,'2016-01-10','18:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (11,1,'2016-01-10','19:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (12,2,'2016-01-10','09:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (13,2,'2016-01-10','10:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (14,2,'2016-01-10','11:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (15,2,'2016-01-10','12:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (16,2,'2016-01-10','13:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (17,2,'2016-01-10','14:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (18,2,'2016-01-10','15:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (19,2,'2016-01-10','16:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (20,2,'2016-01-10','17:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (21,2,'2016-01-10','18:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (22,2,'2016-01-10','19:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (23,1,'2016-01-11','09:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (24,1,'2016-01-11','10:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (25,1,'2016-01-11','11:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (26,1,'2016-01-11','12:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (27,1,'2016-01-11','13:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (28,1,'2016-01-11','14:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (29,1,'2016-01-11','15:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (30,1,'2016-01-11','16:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (31,1,'2016-01-11','17:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (32,1,'2016-01-11','18:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (33,1,'2016-01-11','19:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (34,2,'2016-01-11','09:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (35,2,'2016-01-11','10:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (36,2,'2016-01-11','11:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (37,2,'2016-01-11','12:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (38,2,'2016-01-11','13:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (39,2,'2016-01-11','14:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (40,2,'2016-01-11','15:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (41,2,'2016-01-11','16:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (42,2,'2016-01-11','17:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (43,2,'2016-01-11','18:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (44,2,'2016-01-11','19:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (45,3,'2016-01-10','09:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (46,3,'2016-01-10','10:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (47,3,'2016-01-10','11:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (48,3,'2016-01-10','12:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (49,3,'2016-01-10','13:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (50,3,'2016-01-10','14:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (51,3,'2016-01-10','15:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (52,4,'2016-01-10','09:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (53,4,'2016-01-10','10:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (54,4,'2016-01-10','11:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (55,4,'2016-01-10','12:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (56,4,'2016-01-10','13:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (57,4,'2016-01-10','14:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (58,4,'2016-01-10','15:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (59,4,'2016-01-10','16:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (60,3,'2016-01-11','09:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (61,3,'2016-01-11','10:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (62,3,'2016-01-11','11:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (63,3,'2016-01-11','12:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (64,3,'2016-01-11','13:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (65,3,'2016-01-11','14:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (66,3,'2016-01-11','15:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (67,3,'2016-01-11','16:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (68,4,'2016-01-11','09:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (69,4,'2016-01-11','10:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (70,4,'2016-01-11','11:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (71,4,'2016-01-11','12:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (72,4,'2016-01-11','13:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (73,4,'2016-01-11','14:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (74,4,'2016-01-11','15:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (75,4,'2016-01-11','16:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (76,4,'2016-01-11','17:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (77,4,'2016-01-11','18:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (78,4,'2016-01-11','19:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (79,5,'2016-01-10','09:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (80,5,'2016-01-10','10:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (81,5,'2016-01-10','11:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (82,5,'2016-01-10','12:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (83,5,'2016-01-10','13:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (84,5,'2016-01-10','14:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (85,5,'2016-01-10','15:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (86,5,'2016-01-10','16:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (87,6,'2016-01-10','09:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (88,6,'2016-01-10','10:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (89,6,'2016-01-10','11:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (90,6,'2016-01-10','12:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (91,6,'2016-01-10','13:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (92,6,'2016-01-10','14:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (93,6,'2016-01-10','15:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (94,6,'2016-01-10','16:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (95,5,'2016-01-11','09:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (96,5,'2016-01-11','10:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (97,5,'2016-01-11','11:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (98,5,'2016-01-11','12:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (99,5,'2016-01-11','13:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (100,5,'2016-01-11','14:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (101,5,'2016-01-11','15:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (102,5,'2016-01-11','16:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (103,5,'2016-01-11','17:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (104,6,'2016-01-11','09:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (105,6,'2016-01-11','10:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (106,6,'2016-01-11','11:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (107,6,'2016-01-11','12:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (108,6,'2016-01-11','13:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (109,6,'2016-01-11','14:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (110,6,'2016-01-11','15:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (111,6,'2016-01-11','16:00',1);
INSERT INTO `autoservice_db`.`time_table` (`time_table_id`, `employee_id`,`date`,`time`,`order_id`) VALUES (112,6,'2016-01-11','17:00',1);
 
INSERT INTO `autoservice_db`.`invoice` (`invoice_id`, `status`) VALUES (1,false);

