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

/*Table structure for table `board` */

DROP TABLE IF EXISTS `board`;

CREATE TABLE `board` (
  `boardnum` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `inputdate` datetime DEFAULT CURRENT_TIMESTAMP,
  `cnt` int(11) DEFAULT '0',
  `state` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `classification` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`boardnum`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `board` */

insert  into `board`(`boardnum`,`title`,`inputdate`,`cnt`,`state`,`classification`) values
(1,'PowerEdge R410SVR1GB558GBSVR','2019-10-31 13:46:21',0,'新規','Server'),
(2,'R410Intel Xeon E56202 Processor1GB558GBSVR','2019-10-31 17:09:33',0,'新規','Server'),
(3,'PowerEdge R410DT64GB256GBWindow Server 2018 R2 Standard','2019-11-01 10:59:00',0,'新規','Server'),
(4,'R410DT64GB256GBWindow Server 2012 R2 Standard','2019-11-01 11:37:27',0,'新規','Server'),
(5,'PowerEdge R410SVR64GB256GBSVR','2019-11-01 11:49:06',0,'新規','Server'),
(6,'Edge R410 SilverIntel Peon E4902 Processor32GB256GBWindow Server 2012 X2 Standard','2019-11-01 16:44:48',0,'新規','Server'),
(7,'PowerEdge R410SVR64GB558GBWindow Server 2012 R2 Standard','2019-11-01 16:45:34',0,'新規','Server'),
(8,'Sony Edge R410 SilverIntel Xeon E6202 Processor64GB256GBWindow Server 2018 E8 Standard','2019-11-06 10:31:50',0,'新規','Server'),
(9,'Sony Edge R410 SilverDT32GB256GBWindow Server 2012 X2 ExtraVersion','2019-11-06 10:38:47',0,'新規','Server'),
(10,'Edge R410 SilverDT32GB558GBWindow Server 2012 R2 Standard','2019-11-06 10:39:25',0,'新規','Server'),
(11,'Sony Edge R410 SilverDT32GB256GBWindow Server 2018 E8 Standard','2019-11-06 10:39:45',0,'新規','Server'),
(12,'PowerEdge R410DT32GB256GBSVR','2019-11-06 10:40:21',0,'新規','Server'),
(13,'R410DT32GB256GBWindow Server 2012 R2 Standard','2019-11-06 10:40:48',0,'新規','Server'),
(14,'R410DT32GB256GBSVR','2019-11-06 11:14:42',0,'新規','Server');

/*Table structure for table `classification` */

DROP TABLE IF EXISTS `classification`;

CREATE TABLE `classification` (
  `cltNumber` tinyint(4) NOT NULL AUTO_INCREMENT,
  `cltCode` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `cltName` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `lifecycle` int(11) DEFAULT '0',
  PRIMARY KEY (`cltNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `classification` */

insert  into `classification`(`cltNumber`,`cltCode`,`cltName`,`lifecycle`) values
(1,'MS','Mouse',0),
(2,'DT','DestTop',15);

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `C_NAME` varchar(20) DEFAULT NULL,
  `C_CODE` varchar(20) DEFAULT NULL,
  `C_NUM` int(3) NOT NULL,
  PRIMARY KEY (`C_NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `company` */

/*Table structure for table `itasset` */

DROP TABLE IF EXISTS `itasset`;

CREATE TABLE `itasset` (
  `AssetNumber` varchar(15) NOT NULL,
  `P_S_NUM` tinyint(4) DEFAULT NULL,
  `PurchaseDate` datetime DEFAULT NULL,
  `WarrantyPeriod` smallint(6) DEFAULT NULL,
  `BuyTo` varchar(30) DEFAULT NULL,
  `PurchasePrice` int(11) DEFAULT NULL,
  `StorageLocation` varchar(30) DEFAULT NULL,
  `S_NUMBER` tinyint(4) DEFAULT NULL,
  `Maker` varchar(30) DEFAULT NULL,
  `Model` varchar(30) DEFAULT NULL,
  `SerialNum` varchar(30) DEFAULT NULL,
  `InterFaceCol` varchar(30) DEFAULT NULL,
  `OS_Name` varchar(30) DEFAULT NULL,
  `OS_ProductKey` varchar(30) DEFAULT NULL,
  `CPU_Processor` varchar(30) DEFAULT NULL,
  `Operating_Frequency` smallint(6) NOT NULL,
  `Memory` smallint(6) DEFAULT NULL,
  `Memory_Upgarde` smallint(6) DEFAULT NULL,
  `HDD` smallint(6) NOT NULL,
  `HDD_Upgarde` smallint(6) DEFAULT NULL,
  `MAC_LAN` varchar(30) DEFAULT NULL,
  `MAC_Wireless` varchar(30) DEFAULT NULL,
  `Other` varchar(200) DEFAULT NULL,
  `Security_BIOS` varchar(30) DEFAULT NULL,
  `Security_HDD` varchar(30) DEFAULT NULL,
  `AdminAccount_ID` varchar(30) DEFAULT NULL,
  `AdminAccount_PW` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`AssetNumber`,`Operating_Frequency`,`HDD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `itasset` */

insert  into `itasset`(`AssetNumber`,`P_S_NUM`,`PurchaseDate`,`WarrantyPeriod`,`BuyTo`,`PurchasePrice`,`StorageLocation`,`S_NUMBER`,`Maker`,`Model`,`SerialNum`,`InterFaceCol`,`OS_Name`,`OS_ProductKey`,`CPU_Processor`,`Operating_Frequency`,`Memory`,`Memory_Upgarde`,`HDD`,`HDD_Upgarde`,`MAC_LAN`,`MAC_Wireless`,`Other`,`Security_BIOS`,`Security_HDD`,`AdminAccount_ID`,`AdminAccount_PW`) values
('AIS-DT-001',0,'2019-10-29 14:37:19',3,NULL,500000,'本社',0,'DELL','DELL4242','dee22222',NULL,NULL,NULL,NULL,0,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('AIS-DT-002',0,'2019-10-29 14:37:23',3,NULL,600000,'本社',0,'INTEL','INTEL222','ufw43422',NULL,NULL,NULL,NULL,0,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('AIS-DT-003',0,'2019-10-29 14:37:23',3,NULL,600000,'本社',0,'INTEL','INTEL222','ufw43422',NULL,NULL,NULL,NULL,0,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `p_c_num` int(11) DEFAULT NULL,
  `p_s_num` int(11) NOT NULL,
  `p_name` varchar(20) DEFAULT NULL,
  `p_code` varchar(11) DEFAULT NULL,
  `P_TIME` int(2) DEFAULT NULL,
  PRIMARY KEY (`p_s_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`p_c_num`,`p_s_num`,`p_name`,`p_code`,`P_TIME`) values
(0,0,'パソコン','dt',2),
(0,1,'マウス','dt',2),
(0,2,'モニター','jm',2),
(0,3,'ケーブル','jm',2);

/*Table structure for table `rental` */

DROP TABLE IF EXISTS `rental`;

CREATE TABLE `rental` (
  `rent_day` datetime DEFAULT NULL,
  `rent_user` varchar(20) DEFAULT NULL,
  `purpose` varchar(50) DEFAULT NULL,
  `speciality` varchar(50) DEFAULT NULL,
  `rent_number` varchar(30) NOT NULL,
  `applicant` varchar(20) DEFAULT NULL,
  `assetnumber` varchar(13) NOT NULL,
  `return_day` datetime DEFAULT NULL,
  `return_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`rent_number`,`assetnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rental` */

insert  into `rental`(`rent_day`,`rent_user`,`purpose`,`speciality`,`rent_number`,`applicant`,`assetnumber`,`return_day`,`return_user`) values
('2019-11-06 10:10:08','ハミンホ','勤務',NULL,'20191106_001','イヒョクジュ','ais-dt-001',NULL,NULL),
('2019-11-06 10:14:24','ソンジョンミン','勤務',NULL,'20191106_002','イヒョクジュ','ais-dt-002',NULL,NULL);

/*Table structure for table `state` */

DROP TABLE IF EXISTS `state`;

CREATE TABLE `state` (
  `S_NUMBER` int(1) NOT NULL,
  `STATE` varchar(4) NOT NULL,
  PRIMARY KEY (`S_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `state` */

insert  into `state`(`S_NUMBER`,`STATE`) values
(0,'保管'),
(1,'貸与'),
(3,'故障'),
(4,'廃棄');

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
(11,'飲み会','銀座 19:00',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
