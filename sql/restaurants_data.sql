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

/*Data for the table `restaurant` */

insert  into `restaurant`(`id`,`name`,`address`,`latitude`,`longitude`,`delivery_radius`) values (1,'Denali Pizza\r\n','725 North Central Avenue',33.444,-112.3152,1400);
insert  into `restaurant`(`id`,`name`,`address`,`latitude`,`longitude`,`delivery_radius`) values (2,'Papa Razzini\r\n','1825 E Guadalupe Rd #110',33.364,-111.9316,2000);
insert  into `restaurant`(`id`,`name`,`address`,`latitude`,`longitude`,`delivery_radius`) values (3,'Cafe Amsterdam','530 East Benson Boulevard',61.1877,-149.889,1500);
insert  into `restaurant`(`id`,`name`,`address`,`latitude`,`longitude`,`delivery_radius`) values (4,'China Wok','3736 Airport Boulevard',30.683,-88.2974,3000);

/*Data for the table `user_address_for_restaurant` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
