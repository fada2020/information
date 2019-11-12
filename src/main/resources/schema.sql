/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.1.38-MariaDB : Database - supplies
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`supplies` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `supplies`;

/*Table structure for table `accessories` */

DROP TABLE IF EXISTS `accessories`;

CREATE TABLE `accessories` (
  `ASSETNUMBER` varchar(15) NOT NULL,
  `NO` int(2) NOT NULL,
  `ITEMNAME` varchar(50) DEFAULT NULL,
  `TRICK` varchar(50) DEFAULT NULL,
  `QUANTITY` int(3) DEFAULT NULL,
  `REMARKS` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ASSETNUMBER`,`NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `accessories` */

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `C_NAME` varchar(20) NOT NULL,
  `C_CODE` varchar(20) NOT NULL,
  `C_NUM` int(3) NOT NULL,
  PRIMARY KEY (`C_NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `company` */

/*Table structure for table `itasset` */

CREATE TABLE `itasset` (
  `ASSETNUMBER` varchar(15) NOT NULL,
  `P_S_NUM` int(2) DEFAULT NULL,
  `PURCHASEDATE` date DEFAULT NULL,
  `WARRANTYPERIOD` int(2) DEFAULT NULL,
  `BUYTO` varchar(30) DEFAULT NULL,
  `PURCHASEPRICE` int(12) DEFAULT NULL,
  `S_NUMBER` int(2) DEFAULT NULL,
  `MAKER` varchar(30) DEFAULT NULL,
  `MODEL` varchar(30) DEFAULT NULL,
  `SERIALNUM` varchar(30) DEFAULT NULL,
  `INTERFACECOL` varchar(30) DEFAULT NULL,
  `OS_NAME` varchar(30) DEFAULT NULL,
  `OS_PRODUCTKEY` varchar(30) DEFAULT NULL,
  `CPU_PROCESSOR` varchar(30) DEFAULT NULL,
  `OPERATING_FREQUENCY` decimal(5,2) NOT NULL,
  `MEMORY` decimal(5,2) DEFAULT NULL,
  `MEMORY_UPGARDE` decimal(5,2) DEFAULT NULL,
  `HDD` decimal(5,2) NOT NULL,
  `HDD_UPGARDE` decimal(5,2) DEFAULT NULL,
  `MAC_LAN` varchar(30) DEFAULT NULL,
  `MAC_WIRELESS` varchar(30) DEFAULT NULL,
  `OTHER` varchar(200) DEFAULT NULL,
  `SECURITY_BIOS` varchar(30) DEFAULT NULL,
  `SECURITY_HDD` varchar(30) DEFAULT NULL,
  `ADMINACCOUNT_ID` varchar(30) DEFAULT NULL,
  `ADMINACCOUNT_PW` varchar(30) DEFAULT NULL,
  `STORAGELOCATION` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ASSETNUMBER`,`OPERATING_FREQUENCY`,`HDD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `itasset` */

insert  into `itasset`(`ASSETNUMBER`,`P_S_NUM`,`PURCHASEDATE`,`WARRANTYPERIOD`,`BUYTO`,`PURCHASEPRICE`,`S_NUMBER`,`MAKER`,`MODEL`,`SERIALNUM`,`INTERFACECOL`,`OS_NAME`,`OS_PRODUCTKEY`,`CPU_PROCESSOR`,`OPERATING_FREQUENCY`,`MEMORY`,`MEMORY_UPGARDE`,`HDD`,`HDD_UPGARDE`,`MAC_LAN`,`MAC_WIRELESS`,`OTHER`,`SECURITY_BIOS`,`SECURITY_HDD`,`ADMINACCOUNT_ID`,`ADMINACCOUNT_PW`,`STORAGELOCATION`) values
('AIS-DT-001',0,'2019-10-29',3,NULL,500000,0,'DELL','DELL4242','dee22222',NULL,NULL,NULL,NULL,0.00,NULL,NULL,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('AIS-DT-002',0,'2019-10-29',3,NULL,600000,0,'INTEL','INTEL222','ufw43422',NULL,NULL,NULL,NULL,0.00,NULL,NULL,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('AIS-DT-003',0,'2019-10-29',3,NULL,600000,0,'INTEL','INTEL222','ufw43422',NULL,NULL,NULL,NULL,0.00,NULL,NULL,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `maintenancehistory` */

DROP TABLE IF EXISTS `maintenancehistory`;

CREATE TABLE `maintenancehistory` (
  `ASSETNUMBER` varchar(15) DEFAULT NULL,
  `NO` int(2) DEFAULT NULL,
  `DATEOFIMPLEMENTATION` date DEFAULT NULL,
  `CONTENTOFIMPLEMENTATION` varchar(200) DEFAULT NULL,
  `RESPONSIBLEPERSON` varchar(30) DEFAULT NULL,
  `APPROVAL` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `maintenancehistory` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `P_C_NUM` int(4) NOT NULL,
  `P_S_NUM` int(5) NOT NULL,
  `P_NAME` varchar(20) NOT NULL,
  `P_CODE` varchar(20) NOT NULL,
  PRIMARY KEY (`P_S_NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`P_C_NUM`,`P_S_NUM`,`P_NAME`,`P_CODE`) values
(0,0,'パソコン','dt'),
(0,1,'マウス','dt'),
(0,2,'モニター','jm'),
(0,3,'ケーブル','jm');

/*Table structure for table `productkind` */

DROP TABLE IF EXISTS `productkind`;

CREATE TABLE `productkind` (
  `P＿C_NAME` varchar(20) NOT NULL,
  `P＿C_CODE` varchar(20) NOT NULL,
  `P_C_NUM` int(4) NOT NULL,
  PRIMARY KEY (`P_C_NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `productkind` */

/*Table structure for table `rental` */

CREATE TABLE `rental` (
  `RENT_DAY` date DEFAULT NULL,
  `RENT_USER` varchar(20) DEFAULT NULL,
  `PURPOSE` varchar(50) DEFAULT NULL,
  `SPECIALITY` varchar(50) DEFAULT NULL,
  `RENT_NUMBER` varchar(10) NOT NULL,
  `APPLICANT` varchar(20) DEFAULT NULL,
  `ASSETNUMBER` varchar(15) NOT NULL,
  `RETURN_PERIOD` datetime DEFAULT NULL,
  `BPPARTNER` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`RENT_NUMBER`,`ASSETNUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rental` */

insert  into `rental`(`RENT_DAY`,`RENT_USER`,`PURPOSE`,`SPECIALITY`,`RENT_NUMBER`,`APPLICANT`,`ASSETNUMBER`,`RETURN_PERIOD`,`BPPARTNER`) values
('2019-11-06','ハミンホ','勤務',NULL,'20191106_0','イヒョクジュ','ais-dt-001',NULL,NULL),
('2019-11-06','ソンジョンミン','勤務',NULL,'20191106_0','イヒョクジュ','ais-dt-002',NULL,NULL);

/*Table structure for table `rentalhistory` */

DROP TABLE IF EXISTS `rentalhistory`;

CREATE TABLE `rentalhistory` (
  `RENT_DAY` date DEFAULT NULL,
  `RENT_USER` varchar(20) DEFAULT NULL,
  `PURPOSE` varchar(50) DEFAULT NULL,
  `SPECIALITY` varchar(50) DEFAULT NULL,
  `RENT_NUMBER` varchar(10) NOT NULL,
  `APPLICANT` varchar(20) DEFAULT NULL,
  `ASSETNUMBER` varchar(15) NOT NULL,
  `RETURN_DAY` date DEFAULT NULL,
  `RETURN_USER` varchar(20) DEFAULT NULL,
  `RETURN_PERIOD` date DEFAULT NULL,
  `STATUS` int(2) DEFAULT NULL,
  `STORAGELOCATION` varchar(30) DEFAULT NULL,
  `BPPARTNER` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`RENT_NUMBER`,`ASSETNUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rentalhistory` */

/*Table structure for table `state` */

DROP TABLE IF EXISTS `state`;

CREATE TABLE `state` (
  `S_NUMBER` int(2) NOT NULL,
  `STATE` varchar(4) NOT NULL,
  PRIMARY KEY (`S_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `state` */

insert  into `state`(`S_NUMBER`,`STATE`) values
(0,'保管'),
(1,'貸与'),
(2,'故障'),
(3,'廃棄');

/*Table structure for table `PRODUCTKIND` */
CREATE TABLE PRODUCTKIND (
P_C_NUM	INT(4) PRIMARY KEY,
P＿C_NAME VARCHAR(20) NOT NULL,
P＿C_CODE VARCHAR(20) NOT NULL
);

/*Table structure for table `todo` */

DROP TABLE IF EXISTS `todo`;

CREATE TABLE `todo` (
  `id` decimal(10,0) NOT NULL,
  `title` text,
  `details` text,
  `finished` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `todo` */

insert  into `todo`(`id`,`title`,`details`,`finished`) values
(1,'飲み会','銀座 19:00',0),
(2,'飲み会','銀座 19:00',0),
(3,'飲み会','銀座 19:00',0),
(4,'飲み会','銀座 19:00',0),
(5,'飲み会','銀座 19:00',0),
(6,'飲み会','銀座 19:00',0),
(7,'飲み会','銀座 19:00',0),
(10,'飲み会','銀座 19:00',0),
(11,'飲み会','銀座 19:00',0),
(12,'飲み会','銀座 19:00',0),
(13,'飲み会','銀座 19:00',0),
(14,'飲み会','銀座 19:00',0),
(15,'飲み会','銀座 19:00',0),
(16,'飲み会','銀座 19:00',0),
(17,'飲み会','銀座 19:00',0),
(18,'飲み会','銀座 19:00',0),
(19,'飲み会','銀座 19:00',0),
(20,'飲み会','銀座 19:00',0),
(21,'飲み会','銀座 19:00',0),
(22,'飲み会','銀座 19:00',0),
(23,'飲み会','銀座 19:00',0),
(24,'飲み会','銀座 19:00',0),
(25,'飲み会','銀座 19:00',0),
(26,'飲み会','銀座 19:00',0),
(27,'飲み会','銀座 19:00',0),
(28,'飲み会','銀座 19:00',0),
(29,'飲み会','銀座 19:00',0),
(30,'飲み会','銀座 19:00',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
