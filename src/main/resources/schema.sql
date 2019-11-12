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

/*Table structure for table `PRODUCTKIND` */
CREATE TABLE PRODUCTKIND (
P_C_NUM	INT(4) PRIMARY KEY,
P＿C_NAME VARCHAR(20) NOT NULL,
P＿C_CODE VARCHAR(20) NOT NULL
);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
