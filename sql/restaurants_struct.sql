/*
SQLyog Ultimate v10.42 
MySQL - 5.1.61-community-log : Database - restaurants_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `restaurants_db`;

/*Table structure for table `restaurant` */

DROP TABLE IF EXISTS `restaurant`;

CREATE TABLE `restaurant` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'Restaurant unique identiifier.',
  `name` varchar(100) NOT NULL COMMENT 'Restaurant name.',
  `address` varchar(255) NOT NULL COMMENT 'Restaurant address.',
  `latitude` double NOT NULL COMMENT 'Restaurant physical Location on Globe.',
  `longitude` double NOT NULL COMMENT 'Restaurant physical Location on Globe.',
  `delivery_radius` double DEFAULT NULL COMMENT 'Restaurant devivery radius',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_NAME` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `user_address_for_restaurant` */

DROP TABLE IF EXISTS `user_address_for_restaurant`;

CREATE TABLE `user_address_for_restaurant` (
  `user_id` int(20) NOT NULL COMMENT 'User unique identifier.',
  `restaurant_id` int(20) NOT NULL COMMENT 'Reference to restaurant.',
  `user_address` varchar(255) NOT NULL COMMENT 'User address string.',
  `latitude` double NOT NULL COMMENT 'User entered address physycal location.',
  `longitude` double NOT NULL COMMENT 'User entered address physical location.',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`,`restaurant_id`,`user_address`),
  KEY `FK_user_address_to_restaurant` (`restaurant_id`),
  CONSTRAINT `FK_user_address_to_restaurant` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
