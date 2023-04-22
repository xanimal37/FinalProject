-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema barterdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `barterdb` ;

-- -----------------------------------------------------
-- Schema barterdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `barterdb` DEFAULT CHARACTER SET utf8 ;
USE `barterdb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street1` VARCHAR(45) NULL,
  `street2` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(2) NULL,
  `zipcode` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ranking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ranking` ;

CREATE TABLE IF NOT EXISTS `ranking` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(150) NOT NULL,
  `enabled` TINYINT NOT NULL,
  `role` VARCHAR(45) NULL,
  `image_url` VARCHAR(2000) NULL,
  `email` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `address_id` INT NULL,
  `ranking_id` INT NULL,
  `biography` TEXT NULL,
  `availability` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  INDEX `fk_user_ranking1_idx` (`ranking_id` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_ranking1`
    FOREIGN KEY (`ranking_id`)
    REFERENCES `ranking` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `task_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `task_status` ;

CREATE TABLE IF NOT EXISTS `task_status` (
  `id` INT NOT NULL DEFAULT 1,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `task` ;

CREATE TABLE IF NOT EXISTS `task` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `estimated_hours` INT NULL,
  `materials_provided` TINYINT NULL,
  `schedule_date` DATETIME NULL,
  `start_date` DATETIME NULL,
  `complete_date` DATETIME NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `task_status_id` INT NOT NULL DEFAULT 1,
  `address_id` INT NULL,
  `user_id` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_task_task_status1_idx` (`task_status_id` ASC),
  INDEX `fk_task_address1_idx` (`address_id` ASC),
  INDEX `fk_task_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_task_task_status1`
    FOREIGN KEY (`task_status_id`)
    REFERENCES `task_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `skill` ;

CREATE TABLE IF NOT EXISTS `skill` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `skill_level`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `skill_level` ;

CREATE TABLE IF NOT EXISTS `skill_level` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `complaint`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `complaint` ;

CREATE TABLE IF NOT EXISTS `complaint` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `closed_date` DATETIME NULL,
  `user_id` INT NOT NULL,
  `active` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_complaint_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_complaint_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `accepted_task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `accepted_task` ;

CREATE TABLE IF NOT EXISTS `accepted_task` (
  `acceptor_id` INT NOT NULL,
  `task_id` INT NOT NULL,
  `rating_by_requester` INT NULL,
  `rating_by_acceptor` INT NULL,
  `remarks_by_requester` TEXT NULL,
  `remarks_by_acceptor` TEXT NULL,
  `accept_date` DATETIME NULL,
  PRIMARY KEY (`acceptor_id`, `task_id`),
  INDEX `fk_user_has_task_task2_idx` (`task_id` ASC),
  INDEX `fk_user_has_task_user1_idx` (`acceptor_id` ASC),
  CONSTRAINT `fk_user_has_task_user1`
    FOREIGN KEY (`acceptor_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_task_task2`
    FOREIGN KEY (`task_id`)
    REFERENCES `task` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `content` TEXT NOT NULL,
  `user_id` INT NOT NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NULL,
  `post_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `in_reply_to_id` INT NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_post1_idx` (`post_id` ASC),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_comment_comment1_idx` (`in_reply_to_id` ASC),
  CONSTRAINT `fk_comment_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_comment1`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_friends`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_friends` ;

CREATE TABLE IF NOT EXISTS `user_has_friends` (
  `user_id` INT NOT NULL,
  `friend_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `friend_id`),
  INDEX `fk_user_has_user_user2_idx` (`friend_id` ASC),
  INDEX `fk_user_has_user_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user2`
    FOREIGN KEY (`friend_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notification_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `notification_type` ;

CREATE TABLE IF NOT EXISTS `notification_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `notification` ;

CREATE TABLE IF NOT EXISTS `notification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` TEXT NULL,
  `user_id` INT NOT NULL,
  `create_date` DATETIME NULL,
  `notification_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_notification_user1_idx` (`user_id` ASC),
  INDEX `fk_notification_notification_type1_idx` (`notification_type_id` ASC),
  CONSTRAINT `fk_notification_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_notification_type1`
    FOREIGN KEY (`notification_type_id`)
    REFERENCES `notification_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_skill` ;

CREATE TABLE IF NOT EXISTS `user_skill` (
  `user_id` INT NOT NULL,
  `skill_id` INT NOT NULL,
  `skill_level_id` INT NOT NULL,
  `certification` VARCHAR(45) NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`user_id`, `skill_id`),
  INDEX `fk_user_has_skill_skill1_idx` (`skill_id` ASC),
  INDEX `fk_user_has_skill_user1_idx` (`user_id` ASC),
  INDEX `fk_user_has_skill_skill_level1_idx` (`skill_level_id` ASC),
  CONSTRAINT `fk_user_has_skill_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_skill_skill1`
    FOREIGN KEY (`skill_id`)
    REFERENCES `skill` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_skill_skill_level1`
    FOREIGN KEY (`skill_level_id`)
    REFERENCES `skill_level` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `task_has_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `task_has_skill` ;

CREATE TABLE IF NOT EXISTS `task_has_skill` (
  `task_id` INT NOT NULL,
  `skill_id` INT NOT NULL,
  PRIMARY KEY (`task_id`, `skill_id`),
  INDEX `fk_task_has_skill_skill1_idx` (`skill_id` ASC),
  INDEX `fk_task_has_skill_task1_idx` (`task_id` ASC),
  CONSTRAINT `fk_task_has_skill_task1`
    FOREIGN KEY (`task_id`)
    REFERENCES `task` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_has_skill_skill1`
    FOREIGN KEY (`skill_id`)
    REFERENCES `skill` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `task_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `task_message` ;

CREATE TABLE IF NOT EXISTS `task_message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `content` TEXT NULL,
  `create_date` DATETIME NULL,
  `task_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_task_message_task1_idx` (`task_id` ASC),
  INDEX `fk_task_message_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_task_message_task1`
    FOREIGN KEY (`task_id`)
    REFERENCES `task` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_message_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS barter@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'barter'@'localhost' IDENTIFIED BY 'barter';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'barter'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zipcode`) VALUES (1, '123 Main St', NULL, 'Richmond', 'VA', 54321);
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zipcode`) VALUES (2, '37 SD St', NULL, 'Denver', 'CO', 12345);
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zipcode`) VALUES (3, '2132 First St.', NULL, 'Navarre', 'FL', 32566);
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zipcode`) VALUES (4, '1432 Acorn dr.', NULL, 'Dayton', 'OH', 63424);
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zipcode`) VALUES (5, '666 Satan Ct', NULL, 'Hell', 'CA', 66666);
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zipcode`) VALUES (7, '1600 Pennsylvannia Ave', NULL, 'Washington', 'DC', 20500);
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zipcode`) VALUES (8, 'Fort Leavenworth', 'Cell 234A', 'Leavenworth', 'KS', 66027);
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zipcode`) VALUES (9, 'Fort Leavenworth', 'Cell 235B', 'Leavenworth', 'KS', 66027);
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zipcode`) VALUES (10, '6640 Washington St.', NULL, 'Yountville', 'CA', 94599);
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zipcode`) VALUES (11, '421 Random St', NULL, 'Winchestertonfieldville', 'IA', 73824);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ranking`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `ranking` (`id`, `name`, `description`) VALUES (1, 'Poop', '0.0 - 0.9 Average');
INSERT INTO `ranking` (`id`, `name`, `description`) VALUES (2, 'Bronze', '1.0 - 2.5 Average');
INSERT INTO `ranking` (`id`, `name`, `description`) VALUES (3, 'Silver', '2.6-3.5 Average');
INSERT INTO `ranking` (`id`, `name`, `description`) VALUES (4, 'Gold', '3.6-4.5 Average');
INSERT INTO `ranking` (`id`, `name`, `description`) VALUES (5, 'Platinim', '4.5-5.0 Average');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `image_url`, `email`, `phone`, `create_date`, `update_date`, `firstname`, `lastname`, `address_id`, `ranking_id`, `biography`, `availability`) VALUES (1, 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'admin', NULL, 'admin@barter.com', '888-232-2311', '2013-07-03', NULL, 'George', 'Washington', 2, 5, 'admin', 'Weekends');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `image_url`, `email`, `phone`, `create_date`, `update_date`, `firstname`, `lastname`, `address_id`, `ranking_id`, `biography`, `availability`) VALUES (2, 'eflatto', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'user', NULL, 'eflatto@gmail.com', '432-321-6436', '2015-07-04', NULL, 'Edwin', 'Flatto', 1, 3, 'I like long walks on the beach', 'Tues, Thurs');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `image_url`, `email`, `phone`, `create_date`, `update_date`, `firstname`, `lastname`, `address_id`, `ranking_id`, `biography`, `availability`) VALUES (3, 'lzeeb', '$2a$10$mYVa48FXtaD7zEGW7QCNtOCPe4vUFYKgx0CUpqJZjtuqnLhPzZjVi', 1, 'user', NULL, 'lzeeb@aol.com', '890-357-1253', '2020-12-27', NULL, 'Lisa', 'Zeeb', 2, 2, 'If hockey was a person I would\'ve married it', 'Every day after 5pm');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `image_url`, `email`, `phone`, `create_date`, `update_date`, `firstname`, `lastname`, `address_id`, `ranking_id`, `biography`, `availability`) VALUES (4, 'rtisdale', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'user', NULL, 'rtizz@aol.com', '654-356- 2342', '2020-01-23', NULL, 'Rob', 'Tisdale', 3, 3, 'Try to watch a sunrise a least once a day', 'Weekends only');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `image_url`, `email`, `phone`, `create_date`, `update_date`, `firstname`, `lastname`, `address_id`, `ranking_id`, `biography`, `availability`) VALUES (5, 'jsmith', '$2a$10$mYVa48FXtaD7zEGW7QCNtOCPe4vUFYKgx0CUpqJZjtuqnLhPzZjVi', 1, 'user', NULL, 'jsmith@gmail.com', '890-876-2338', '2020-08-13', NULL, 'John', 'Smith', 7, 4, 'Bio Test', 'Evenings: Mon, Wed, Fri ');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `image_url`, `email`, `phone`, `create_date`, `update_date`, `firstname`, `lastname`, `address_id`, `ranking_id`, `biography`, `availability`) VALUES (6, 'dturner', '$2a$10$mYVa48FXtaD7zEGW7QCNtOCPe4vUFYKgx0CUpqJZjtuqnLhPzZjVi', 0, 'user', NULL, 'dturner@hotmail.com', '546-274-6675', '2020-01-09', NULL, 'Dan', 'Turner', 8, 2, 'Did you know that if you stacked all the elephants in the world from the ground to the moon, they would all die?', 'Unavailable until June');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `image_url`, `email`, `phone`, `create_date`, `update_date`, `firstname`, `lastname`, `address_id`, `ranking_id`, `biography`, `availability`) VALUES (7, 'neastwood', '$2a$10$mYVa48FXtaD7zEGW7QCNtOCPe4vUFYKgx0CUpqJZjtuqnLhPzZjVi', 1, 'user', NULL, 'neastwood@aol.com', '345-235-7427', '2020-05-23', NULL, 'Nancy', 'EastWood', 9, 1, 'Did you hear about the circus fire? it was in-tents!', 'Every other weekend');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `image_url`, `email`, `phone`, `create_date`, `update_date`, `firstname`, `lastname`, `address_id`, `ranking_id`, `biography`, `availability`) VALUES (8, 'kgreen', '$2a$10$mYVa48FXtaD7zEGW7QCNtOCPe4vUFYKgx0CUpqJZjtuqnLhPzZjVi', 1, 'user', NULL, 'kgreen@gmail.com', '498-242-0989', '2018-04-30', NULL, 'Karen', 'Greene', 9, 4, 'Im a real Karen, watch out', 'Mondays after 4pm');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `image_url`, `email`, `phone`, `create_date`, `update_date`, `firstname`, `lastname`, `address_id`, `ranking_id`, `biography`, `availability`) VALUES (9, 'snewsom', '$2a$10$mYVa48FXtaD7zEGW7QCNtOCPe4vUFYKgx0CUpqJZjtuqnLhPzZjVi', 0, 'user', NULL, 'snewsom@outlook.com', '586-212-3332', '2019-11-15', NULL, 'Sharron', 'Newsom', 10, 1, 'My basement has a questionable amount of beanie babies', 'Week day mornings after 8am');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `image_url`, `email`, `phone`, `create_date`, `update_date`, `firstname`, `lastname`, `address_id`, `ranking_id`, `biography`, `availability`) VALUES (10, 'hclancy', '$2a$10$mYVa48FXtaD7zEGW7QCNtOCPe4vUFYKgx0CUpqJZjtuqnLhPzZjVi', 0, 'user', NULL, 'hclancy@gmail.com', '498-242-0009', '2021-10-21', NULL, 'Hillary', 'Clancy', 8, 3, 'Sometimes I like to sleep on my roof for no reason', 'Mon, Tues, Wed.');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `image_url`, `email`, `phone`, `create_date`, `update_date`, `firstname`, `lastname`, `address_id`, `ranking_id`, `biography`, `availability`) VALUES (11, 'rpaulson', '$2a$10$mYVa48FXtaD7zEGW7QCNtOCPe4vUFYKgx0CUpqJZjtuqnLhPzZjVi', 1, 'user', NULL, 'rpaulson@hotmail.com', '850-368-5322', '2020-06-06', NULL, 'Robert', 'Paulson', 11, 2, 'Yes, I am that Robert Paulson', 'Weekends only');

COMMIT;


-- -----------------------------------------------------
-- Data for table `task_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `task_status` (`id`, `name`, `description`) VALUES (1, 'Pending', 'Task not yet accepted');
INSERT INTO `task_status` (`id`, `name`, `description`) VALUES (2, 'Accepted', 'User has volunteered for task');
INSERT INTO `task_status` (`id`, `name`, `description`) VALUES (3, 'In Progress', 'Work is underway');
INSERT INTO `task_status` (`id`, `name`, `description`) VALUES (4, 'Complete', 'Acceptor finished task');
INSERT INTO `task_status` (`id`, `name`, `description`) VALUES (5, 'Verified', 'Work has been verified');
INSERT INTO `task_status` (`id`, `name`, `description`) VALUES (6, 'Filler1', NULL);
INSERT INTO `task_status` (`id`, `name`, `description`) VALUES (7, 'Filler2', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `task`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `task` (`id`, `name`, `description`, `estimated_hours`, `materials_provided`, `schedule_date`, `start_date`, `complete_date`, `create_date`, `update_date`, `task_status_id`, `address_id`, `user_id`) VALUES (1, 'Bathroom Sink', NULL, 4, 0, '2023-03-25', '2023-03-27', '2023-04-04', '2023-03-23', '2023-04-04', 5, 1, 2);
INSERT INTO `task` (`id`, `name`, `description`, `estimated_hours`, `materials_provided`, `schedule_date`, `start_date`, `complete_date`, `create_date`, `update_date`, `task_status_id`, `address_id`, `user_id`) VALUES (2, 'Trim Trees', NULL, 3, 0, '2023-04-22', NULL, NULL, '2023-04-18', '2023-04-22', 2, 2, 3);
INSERT INTO `task` (`id`, `name`, `description`, `estimated_hours`, `materials_provided`, `schedule_date`, `start_date`, `complete_date`, `create_date`, `update_date`, `task_status_id`, `address_id`, `user_id`) VALUES (3, 'Weed the front garden', 'My back went out and I could use some assistance', 1, 0, '2023-04-18', '2023-04-20', NULL, '2023-04-14', '2023-04-20', 3, 4, 4);
INSERT INTO `task` (`id`, `name`, `description`, `estimated_hours`, `materials_provided`, `schedule_date`, `start_date`, `complete_date`, `create_date`, `update_date`, `task_status_id`, `address_id`, `user_id`) VALUES (4, 'Clogged plumbing', 'Toilets and sinks not working', 2, 0, '2022-03-23', '2022-04-25', '2022-04-28', '2022-03-15', '2022-04-25', 4, 3, 5);
INSERT INTO `task` (`id`, `name`, `description`, `estimated_hours`, `materials_provided`, `schedule_date`, `start_date`, `complete_date`, `create_date`, `update_date`, `task_status_id`, `address_id`, `user_id`) VALUES (5, 'Workbench', 'Have extra lumber and would like assistance building a strurdy workbench for future projects', 4, 1, '2023-03-14', '2023-03-22', NULL, NULL, NULL, 3, 11, 8);
INSERT INTO `task` (`id`, `name`, `description`, `estimated_hours`, `materials_provided`, `schedule_date`, `start_date`, `complete_date`, `create_date`, `update_date`, `task_status_id`, `address_id`, `user_id`) VALUES (6, 'Wifi not working ', 'Service company said they couldnt be out for 2 weeks, hope someone can help', 1, 0, '2022-11-23', '2022-11-24', '2022-11-24', '2022-11-23', '2022-11-24', 5, 9, 6);
INSERT INTO `task` (`id`, `name`, `description`, `estimated_hours`, `materials_provided`, `schedule_date`, `start_date`, `complete_date`, `create_date`, `update_date`, `task_status_id`, `address_id`, `user_id`) VALUES (DEFAULT, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, DEFAULT, NULL, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (1, 'Carpentry', NULL);
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (2, 'Plumbing', NULL);
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (3, 'Painting', NULL);
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (4, 'IT/Networking', NULL);
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (5, 'Electrical', NULL);
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (6, 'Concrete/Masonry', NULL);
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (7, 'Cleaning', NULL);
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (8, 'Packing/Moving', NULL);
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (9, 'Welding', NULL);
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (10, 'Landscaping', NULL);
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (11, 'Automotive', NULL);
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (12, 'Appliance Repair', NULL);
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (13, 'Corruption', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `skill_level`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `skill_level` (`id`, `name`, `description`) VALUES (1, 'Novice', NULL);
INSERT INTO `skill_level` (`id`, `name`, `description`) VALUES (2, 'Beginner', NULL);
INSERT INTO `skill_level` (`id`, `name`, `description`) VALUES (3, 'Competent', NULL);
INSERT INTO `skill_level` (`id`, `name`, `description`) VALUES (4, 'Proficient', NULL);
INSERT INTO `skill_level` (`id`, `name`, `description`) VALUES (5, 'Expert', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `complaint`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `complaint` (`id`, `name`, `description`, `create_date`, `update_date`, `closed_date`, `user_id`, `active`) VALUES (1, 'Foul Behavior ', 'Zeeb was supposed to trim my tree but instead stole my miniature Donald Trump commemorative dinner plates', '2022-12-29', '2023-01-14', '2023-04-01', 2, 1);
INSERT INTO `complaint` (`id`, `name`, `description`, `create_date`, `update_date`, `closed_date`, `user_id`, `active`) VALUES (2, 'Account access', 'I cant seem to access my account. Can you help?', '2022-12-29', '2022-12-29', '2022-12-29', 3, 0);
INSERT INTO `complaint` (`id`, `name`, `description`, `create_date`, `update_date`, `closed_date`, `user_id`, `active`) VALUES (3, 'update Username', 'not sure why I cant change it on my end, but I need to update my username. Please change jsmith to jsmithers', '2023-01-02', NULL, NULL, 4, 1);
INSERT INTO `complaint` (`id`, `name`, `description`, `create_date`, `update_date`, `closed_date`, `user_id`, `active`) VALUES (4, 'Inappropriate Language', 'Mr. Rob Tisdale was performing a task at my house and cut himself and started swearing. My dogs heard it and are scarred, remove him immediately! Karen', '2023-03-02', '2023-03-02', '2023-03-02', 8, 0);
INSERT INTO `complaint` (`id`, `name`, `description`, `create_date`, `update_date`, `closed_date`, `user_id`, `active`) VALUES (5, 'Account Activation', 'I am still not able to access my accound and I have serves my probation time away', '2023-03-09', NULL, NULL, 9, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `accepted_task`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `accepted_task` (`acceptor_id`, `task_id`, `rating_by_requester`, `rating_by_acceptor`, `remarks_by_requester`, `remarks_by_acceptor`, `accept_date`) VALUES (3, 1, 4.1, 3.5, NULL, NULL, '2023-02-11');
INSERT INTO `accepted_task` (`acceptor_id`, `task_id`, `rating_by_requester`, `rating_by_acceptor`, `remarks_by_requester`, `remarks_by_acceptor`, `accept_date`) VALUES (2, 2, 5.0, 4.4, NULL, NULL, NULL);
INSERT INTO `accepted_task` (`acceptor_id`, `task_id`, `rating_by_requester`, `rating_by_acceptor`, `remarks_by_requester`, `remarks_by_acceptor`, `accept_date`) VALUES (4, 3, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `accepted_task` (`acceptor_id`, `task_id`, `rating_by_requester`, `rating_by_acceptor`, `remarks_by_requester`, `remarks_by_acceptor`, `accept_date`) VALUES (5, 4, 2.5, 3.8, 'Micro manage much???', 'Water Preasure not what it was but better. ', '2022-04-28');
INSERT INTO `accepted_task` (`acceptor_id`, `task_id`, `rating_by_requester`, `rating_by_acceptor`, `remarks_by_requester`, `remarks_by_acceptor`, `accept_date`) VALUES (4, 5, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `accepted_task` (`acceptor_id`, `task_id`, `rating_by_requester`, `rating_by_acceptor`, `remarks_by_requester`, `remarks_by_acceptor`, `accept_date`) VALUES (3, 6, 5.0, 5.0, 'Super friendly just needed to reset the router', 'They were a genius and got it done super quick', '2022-11-23');

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `post` (`id`, `title`, `content`, `user_id`, `create_date`, `update_date`, `enabled`) VALUES (1, 'Plumbing Question', 'Whats the best way to install an L joint with a flex host on an existing sprinkler system', 2, '2023-02-23', '2023-02-24', 1);
INSERT INTO `post` (`id`, `title`, `content`, `user_id`, `create_date`, `update_date`, `enabled`) VALUES (2, 'Sprinklers', 'How do I swap my Sprinkler head', 3, '2023-02-23', NULL, 1);
INSERT INTO `post` (`id`, `title`, `content`, `user_id`, `create_date`, `update_date`, `enabled`) VALUES (3, 'Fire Ant Treatment', 'What is the best home remedy without killing my grass', 3, '2023-02-24', NULL, 1);
INSERT INTO `post` (`id`, `title`, `content`, `user_id`, `create_date`, `update_date`, `enabled`) VALUES (4, 'Toilet bowl running', 'I cant seem to figure out why the toilet is always running ', 5, '2023-03-14', NULL, 1);
INSERT INTO `post` (`id`, `title`, `content`, `user_id`, `create_date`, `update_date`, `enabled`) VALUES (5, 'Can dogs eat blueberries?', 'My Corgi got into my blueberries and Im worried theyre poisonous', 4, '2023-03-15', NULL, 1);
INSERT INTO `post` (`id`, `title`, `content`, `user_id`, `create_date`, `update_date`, `enabled`) VALUES (6, 'Grinder needed!', 'Anyone in Leavenworth have a grinder I can borrow? Asking for a friend', 10, '2023-03-17', NULL, 1);
INSERT INTO `post` (`id`, `title`, `content`, `user_id`, `create_date`, `update_date`, `enabled`) VALUES (7, 'Sod needed', 'Looking for a reputable sod installation service near me if anyone can help', 11, '2023-04-17', NULL, 1);
INSERT INTO `post` (`id`, `title`, `content`, `user_id`, `create_date`, `update_date`, `enabled`) VALUES (8, 'Mount TV', 'What is the best mount for a 65\" flatscreen?', 2, '2023-04-18', NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `comment` (`id`, `content`, `post_id`, `user_id`, `in_reply_to_id`, `create_date`, `update_date`, `enabled`) VALUES (1, 'Make sure your pipe fitting matched and you used threading tape', 1, 3, NULL, '2020-01-01 01:00:00', NULL, 1);
INSERT INTO `comment` (`id`, `content`, `post_id`, `user_id`, `in_reply_to_id`, `create_date`, `update_date`, `enabled`) VALUES (2, 'Correct, this will ensure the fitting is secure', 1, 2, 1, '2020-01-02 01:00:00', NULL, 1);
INSERT INTO `comment` (`id`, `content`, `post_id`, `user_id`, `in_reply_to_id`, `create_date`, `update_date`, `enabled`) VALUES (3, 'Ensure the valve is properly seated..blah ', 2, 2, NULL, '2020-01-02', NULL, 1);
INSERT INTO `comment` (`id`, `content`, `post_id`, `user_id`, `in_reply_to_id`, `create_date`, `update_date`, `enabled`) VALUES (4, 'Just pee on them!', 3, 3, NULL, NULL, NULL, 1);
INSERT INTO `comment` (`id`, `content`, `post_id`, `user_id`, `in_reply_to_id`, `create_date`, `update_date`, `enabled`) VALUES (5, 'Ensure the rubber seal isnt eroded. If it is theyre like 3$ at the local store', 4, 4, NULL, '2020-03-14', NULL, 1);
INSERT INTO `comment` (`id`, `content`, `post_id`, `user_id`, `in_reply_to_id`, `create_date`, `update_date`, `enabled`) VALUES (6, 'Yeah I got one. I\'ll send it your way', 6, 6, NULL, '2023-03-17', NULL, 1);
INSERT INTO `comment` (`id`, `content`, `post_id`, `user_id`, `in_reply_to_id`, `create_date`, `update_date`, `enabled`) VALUES (7, 'Blueberries are fine for dogs', 5, 9, NULL, '2023-03-17', NULL, 1);
INSERT INTO `comment` (`id`, `content`, `post_id`, `user_id`, `in_reply_to_id`, `create_date`, `update_date`, `enabled`) VALUES (8, 'Yup! Mine eats them all the time. Good for them actually', 5, 3, 7, '2023-03-17', NULL, 1);
INSERT INTO `comment` (`id`, `content`, `post_id`, `user_id`, `in_reply_to_id`, `create_date`, `update_date`, `enabled`) VALUES (9, 'Mine cant seem to get enough carrots!', 5, 7, 8, '2023-03-18', NULL, 1);
INSERT INTO `comment` (`id`, `content`, `post_id`, `user_id`, `in_reply_to_id`, `create_date`, `update_date`, `enabled`) VALUES (10, 'I would order the titanium swivel. Good for heavy weight', 8, 10, NULL, '2023-03-20', NULL, 1);
INSERT INTO `comment` (`id`, `content`, `post_id`, `user_id`, `in_reply_to_id`, `create_date`, `update_date`, `enabled`) VALUES (11, 'What kind of grass are you looing for?', 7, 4, NULL, '2023-03-20', NULL, 1);
INSERT INTO `comment` (`id`, `content`, `post_id`, `user_id`, `in_reply_to_id`, `create_date`, `update_date`, `enabled`) VALUES (12, 'Centipede will do. Not looking to break the bank', 7, 11, 11, '2023-03-20', NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_friends`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (2, 3);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (2, 4);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (3, 2);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (2, 5);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (2, 6);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (3, 4);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (3, 5);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (4, 5);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (4, 11);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (4, 6);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (4, 10);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (5, 2);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (5, 3);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (5, 4);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (5, 6);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (5, 8);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (5, 9);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (6, 5);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (6, 8);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (6, 7);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (7, 10);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (7, 9);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (7, 11);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (7, 8);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (8, 6);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (8, 5);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (8, 11);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (9, 5);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (9, 6);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (10, 6);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (11, 4);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (11, 3);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (11, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `notification_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `notification_type` (`id`, `name`) VALUES (1, 'Informative');
INSERT INTO `notification_type` (`id`, `name`) VALUES (2, 'Post');
INSERT INTO `notification_type` (`id`, `name`) VALUES (3, 'Comment');
INSERT INTO `notification_type` (`id`, `name`) VALUES (4, 'Account');
INSERT INTO `notification_type` (`id`, `name`) VALUES (5, 'Administrative');
INSERT INTO `notification_type` (`id`, `name`) VALUES (6, 'Message');

COMMIT;


-- -----------------------------------------------------
-- Data for table `notification`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `notification` (`id`, `message`, `user_id`, `create_date`, `notification_type_id`) VALUES (1, 'You have a new update to your account', 3, '2023-03-23', 4);
INSERT INTO `notification` (`id`, `message`, `user_id`, `create_date`, `notification_type_id`) VALUES (2, 'You have a pending complaint under investigation pertaining to Task # 1232', 2, '2023-03-24', 5);
INSERT INTO `notification` (`id`, `message`, `user_id`, `create_date`, `notification_type_id`) VALUES (3, 'Someone left a comment on your post', 2, '2023-03-23', 3);
INSERT INTO `notification` (`id`, `message`, `user_id`, `create_date`, `notification_type_id`) VALUES (4, 'You have a new message', 3, '2023-04-22', 6);
INSERT INTO `notification` (`id`, `message`, `user_id`, `create_date`, `notification_type_id`) VALUES (5, 'You have a new message', 4, '2023-04-22', 6);
INSERT INTO `notification` (`id`, `message`, `user_id`, `create_date`, `notification_type_id`) VALUES (6, 'You have a new message', 5, '2023-04-22', 6);
INSERT INTO `notification` (`id`, `message`, `user_id`, `create_date`, `notification_type_id`) VALUES (7, 'You have a new message', 6, '2023-04-22', 6);
INSERT INTO `notification` (`id`, `message`, `user_id`, `create_date`, `notification_type_id`) VALUES (8, 'You have a new message', 7, '2023-04-22', 6);
INSERT INTO `notification` (`id`, `message`, `user_id`, `create_date`, `notification_type_id`) VALUES (9, 'You have a new message', 8, '2023-04-22', 6);
INSERT INTO `notification` (`id`, `message`, `user_id`, `create_date`, `notification_type_id`) VALUES (10, 'You have a new message', 9, '2023-04-22', 6);
INSERT INTO `notification` (`id`, `message`, `user_id`, `create_date`, `notification_type_id`) VALUES (11, 'Someone has a task you may be interested in', 3, '2023-04-22', 1);
INSERT INTO `notification` (`id`, `message`, `user_id`, `create_date`, `notification_type_id`) VALUES (12, 'Account role has been updated', 2, '2023-04-22', 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (2, 2, 2, NULL, 'Apprentice Plumber with union');
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (3, 2, 2, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (2, 1, 1, NULL, 'GODDAMN RON SWANSON');
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (3, 1, 2, NULL, 'Im kinda ok');
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (4, 1, 3, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (4, 4, 3, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (4, 10, 4, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (4, 8, 5, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (5, 9, 4, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (5, 11, 3, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (6, 7, 1, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (6, 13, 5, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (7, 13, 5, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (8, 13, 4, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (8, 6, 3, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (9, 13, 5, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (9, 8, 4, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (10, 13, 5, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (10, 4, 4, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (10, 7, 4, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (11, 12, 4, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (11, 11, 3, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (11, 3, 5, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `task_has_skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `task_has_skill` (`task_id`, `skill_id`) VALUES (1, 2);
INSERT INTO `task_has_skill` (`task_id`, `skill_id`) VALUES (1, 1);
INSERT INTO `task_has_skill` (`task_id`, `skill_id`) VALUES (2, 10);
INSERT INTO `task_has_skill` (`task_id`, `skill_id`) VALUES (3, 10);
INSERT INTO `task_has_skill` (`task_id`, `skill_id`) VALUES (4, 2);
INSERT INTO `task_has_skill` (`task_id`, `skill_id`) VALUES (5, 1);
INSERT INTO `task_has_skill` (`task_id`, `skill_id`) VALUES (6, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `task_message`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `task_message` (`id`, `title`, `content`, `create_date`, `task_id`, `user_id`) VALUES (1, 'Meetup', 'Lets sync at xyz', '2023-04-12', 1, 2);
INSERT INTO `task_message` (`id`, `title`, `content`, `create_date`, `task_id`, `user_id`) VALUES (2, 'Pets', 'Just so you know I have a dog outside, super friendly but might get in your way', '2023-04-22', 2, 3);
INSERT INTO `task_message` (`id`, `title`, `content`, `create_date`, `task_id`, `user_id`) VALUES (3, 'Clamps', 'Hey, any chance you have an extra pair of clamps? I cant find mine', '2023-03-20', 5, 8);

COMMIT;

