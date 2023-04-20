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
  `id` INT NOT NULL AUTO_INCREMENT,
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
  `task_status_id` INT NOT NULL,
  `address_id` INT NULL,
  `user_id` INT NOT NULL,
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

COMMIT;


-- -----------------------------------------------------
-- Data for table `ranking`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `ranking` (`id`, `name`, `description`) VALUES (1, 'Super Star', NULL);
INSERT INTO `ranking` (`id`, `name`, `description`) VALUES (2, 'Amazing', NULL);
INSERT INTO `ranking` (`id`, `name`, `description`) VALUES (3, 'Sufficient', NULL);
INSERT INTO `ranking` (`id`, `name`, `description`) VALUES (4, 'Sub-Par', NULL);
INSERT INTO `ranking` (`id`, `name`, `description`) VALUES (5, 'Poor', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `image_url`, `email`, `phone`, `create_date`, `update_date`, `firstname`, `lastname`, `address_id`, `ranking_id`, `biography`, `availability`) VALUES (1, 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'admin', NULL, NULL, NULL, '2013-07-03', NULL, 'George', 'Washington', 2, 5, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `image_url`, `email`, `phone`, `create_date`, `update_date`, `firstname`, `lastname`, `address_id`, `ranking_id`, `biography`, `availability`) VALUES (2, 'eflatto', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'user', NULL, NULL, NULL, '2015-07-04', NULL, 'Edwin', 'Flatto', 1, 3, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `image_url`, `email`, `phone`, `create_date`, `update_date`, `firstname`, `lastname`, `address_id`, `ranking_id`, `biography`, `availability`) VALUES (3, 'lzeeb', '$2a$10$mYVa48FXtaD7zEGW7QCNtOCPe4vUFYKgx0CUpqJZjtuqnLhPzZjVi', 1, 'user', NULL, NULL, NULL, '2020-01-23', NULL, 'Lisa', 'Zeeb', 2, 2, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `task_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `task_status` (`id`, `name`, `description`) VALUES (1, 'Pending', 'Task not yet accepted');
INSERT INTO `task_status` (`id`, `name`, `description`) VALUES (2, 'Accepted', 'User has volunteered for task');

COMMIT;


-- -----------------------------------------------------
-- Data for table `task`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `task` (`id`, `name`, `description`, `estimated_hours`, `materials_provided`, `schedule_date`, `start_date`, `complete_date`, `create_date`, `update_date`, `task_status_id`, `address_id`, `user_id`) VALUES (1, 'Bathroom Sink', NULL, 4, 0, '2023-03-25', '2023-03-27', '2023-04-04', '2023-03-23', '2023-04-04', 1, 1, 2);
INSERT INTO `task` (`id`, `name`, `description`, `estimated_hours`, `materials_provided`, `schedule_date`, `start_date`, `complete_date`, `create_date`, `update_date`, `task_status_id`, `address_id`, `user_id`) VALUES (2, 'Trim Trees', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, 3);

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

COMMIT;


-- -----------------------------------------------------
-- Data for table `skill_level`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `skill_level` (`id`, `name`, `description`) VALUES (1, 'Expert', NULL);
INSERT INTO `skill_level` (`id`, `name`, `description`) VALUES (2, 'Proficient', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `complaint`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `complaint` (`id`, `name`, `description`, `create_date`, `update_date`, `closed_date`, `user_id`, `active`) VALUES (1, 'Foul Behavior ', 'Zeeb was supposed to trim my tree but instead stole my miniature Donald Trump commemorative dinner plates', '2022-12-29', '2023-01-14', '2023-04-01', 2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `accepted_task`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `accepted_task` (`acceptor_id`, `task_id`, `rating_by_requester`, `rating_by_acceptor`, `remarks_by_requester`, `remarks_by_acceptor`, `accept_date`) VALUES (3, 1, 4.1, 3.5, NULL, NULL, '2023-02-11');
INSERT INTO `accepted_task` (`acceptor_id`, `task_id`, `rating_by_requester`, `rating_by_acceptor`, `remarks_by_requester`, `remarks_by_acceptor`, `accept_date`) VALUES (2, 2, 5.0, 4.4, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `post` (`id`, `title`, `content`, `user_id`, `create_date`, `update_date`) VALUES (1, 'Plumbing Question', 'Whats the best way to install an L joint with a flex host on an existing sprinkler system', 2, '2023-02-23', '2023-02-24');

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `comment` (`id`, `content`, `post_id`, `user_id`, `in_reply_to_id`, `create_date`, `update_date`) VALUES (1, 'Make sure your pipe fitting matched and you used threading tape', 1, 3, NULL, '2020-01-01 01:00:00', NULL);
INSERT INTO `comment` (`id`, `content`, `post_id`, `user_id`, `in_reply_to_id`, `create_date`, `update_date`) VALUES (2, 'Correct, this will ensure the fitting is secure', 1, 2, 1, '2020-01-02 01:00:00', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_friends`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (2, 3);
INSERT INTO `user_has_friends` (`user_id`, `friend_id`) VALUES (2, 1);

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

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (2, 2, 2, NULL, 'Apprentice Plumber with union');
INSERT INTO `user_skill` (`user_id`, `skill_id`, `skill_level_id`, `certification`, `description`) VALUES (3, 2, 2, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `task_has_skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `task_has_skill` (`task_id`, `skill_id`) VALUES (1, 1);
INSERT INTO `task_has_skill` (`task_id`, `skill_id`) VALUES (1, 2);
INSERT INTO `task_has_skill` (`task_id`, `skill_id`) VALUES (1, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `task_message`
-- -----------------------------------------------------
START TRANSACTION;
USE `barterdb`;
INSERT INTO `task_message` (`id`, `title`, `content`, `create_date`, `task_id`, `user_id`) VALUES (1, 'Meetup', 'Lets sync at xyz', '2023-04-12', 1, 2);

COMMIT;

