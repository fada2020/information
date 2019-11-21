/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 5.7.28 : Database - amsdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`amsdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `amsdb`;

/*Table structure for table `accessories` */

DROP TABLE IF EXISTS `accessories`;

CREATE TABLE `accessories` (
  `accessories_seq` int(11) NOT NULL AUTO_INCREMENT,
  `asset_seq` int(11) NOT NULL,
  `item_name` varchar(50) DEFAULT NULL,
  `item_spec` varchar(50) DEFAULT NULL,
  `item_quantity` int(11) DEFAULT '0',
  `remarks` varchar(200) DEFAULT NULL,
  `insert_id` varchar(20) NOT NULL,
  `insert_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_id` varchar(20) NOT NULL,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`accessories_seq`),
  KEY `accessories_FK` (`asset_seq`),
  CONSTRAINT `accessories_FK` FOREIGN KEY (`asset_seq`) REFERENCES `asset` (`asset_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='付属品マスタ';

/*Data for the table `accessories` */

insert  into `accessories`(`accessories_seq`,`asset_seq`,`item_name`,`item_spec`,`item_quantity`,`remarks`,`insert_id`,`insert_date`,`update_id`,`update_date`) values 
(1,3,'cable','cable',3,'','ais000000','2019-11-20 07:58:47','ais000000','2019-11-20 07:58:47');

/*Table structure for table `asset` */

DROP TABLE IF EXISTS `asset`;

CREATE TABLE `asset` (
  `asset_seq` int(11) NOT NULL AUTO_INCREMENT,
  `asset_number` varchar(15) NOT NULL,
  `kubun_code` varchar(3) DEFAULT NULL,
  `purchase_date` varchar(10) DEFAULT NULL,
  `warranty_period` int(2) DEFAULT NULL,
  `buy_to` varchar(30) DEFAULT NULL,
  `purchase_price` int(11) DEFAULT NULL,
  `status_code` varchar(3) DEFAULT NULL,
  `maker_name` varchar(50) DEFAULT NULL,
  `model_name` varchar(50) DEFAULT NULL,
  `serial_num` varchar(30) DEFAULT NULL,
  `interface_col` varchar(30) DEFAULT NULL,
  `os_name` varchar(30) DEFAULT NULL,
  `os_product_key` varchar(30) DEFAULT NULL,
  `cpu_processor` varchar(30) DEFAULT NULL,
  `operating_frequency` decimal(7,2) DEFAULT NULL,
  `memory` decimal(7,2) DEFAULT NULL,
  `memory_upgarde` decimal(7,2) DEFAULT NULL,
  `hdd` decimal(7,2) DEFAULT NULL,
  `hdd_upgarde` decimal(7,2) DEFAULT NULL,
  `mac_lan` varchar(30) DEFAULT NULL,
  `mac_wireless` varchar(30) DEFAULT NULL,
  `other` varchar(200) DEFAULT NULL,
  `security_bios` varchar(30) DEFAULT NULL,
  `security_hdd` varchar(30) DEFAULT NULL,
  `admin_account_id` varchar(30) DEFAULT NULL,
  `admin_account_pwd` varchar(30) DEFAULT NULL,
  `storage_location` varchar(30) DEFAULT NULL,
  `insert_id` varchar(20) NOT NULL,
  `insert_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_id` varchar(20) NOT NULL,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`asset_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COMMENT='IT資産マスタ';

/*Data for the table `asset` */

insert  into `asset`(`asset_seq`,`asset_number`,`kubun_code`,`purchase_date`,`warranty_period`,`buy_to`,`purchase_price`,`status_code`,`maker_name`,`model_name`,`serial_num`,`interface_col`,`os_name`,`os_product_key`,`cpu_processor`,`operating_frequency`,`memory`,`memory_upgarde`,`hdd`,`hdd_upgarde`,`mac_lan`,`mac_wireless`,`other`,`security_bios`,`security_hdd`,`admin_account_id`,`admin_account_pwd`,`storage_location`,`insert_id`,`insert_date`,`update_id`,`update_date`) values 
(3,'AIS-SVR-001','01','20191120',0,'Market',900000,'02','DELL','DELL SERVER','22452-342222','','CentOS','1431121-23222','intel dual processor',3.30,12.00,0.00,552.00,0.00,'','','','','','ais','ais#123456','本社','ais000000','2019-11-20 07:41:19','ais000000','2019-11-21 00:10:27'),
(4,'AIS-DT-001','02','20191120',0,'Market',90000,'02','DELL','DELL PRO','22452-342222','','Window 10','1431121-23222','intel dual processor',2.20,12.00,0.00,1024.00,0.00,'','','','','','ais','ais!123456','本社','ais000000','2019-11-20 07:45:40','ais000000','2019-11-21 02:35:25'),
(5,'AIS-NB-001','03','20191120',0,'Market',80000,'01','DELL','DELL Note PRO','22452-342222','','Window 10','1431121-23222','intel dual processor',1.20,12.00,0.00,2048.00,0.00,'','','','','','ais','ais#123456','本社','ais000000','2019-11-20 07:46:27','ais000000','2019-11-21 02:33:17'),
(6,'AIS-TBL-001','04','20191120',0,'Market',70000,'01','DELL','DELL Tablet','22452-342222','','Window 10','1431121-23222','intel dual processor',1.20,12.00,0.00,512.00,0.00,'','','','','','ais','ais#123456','本社','ais000000','2019-11-20 07:48:53','ais000000','2019-11-20 07:48:53'),
(7,'AIS-DP-001','06','20191120',0,'Market',30000,'01','DELL','DELL Moniter','22452-342222','','','','',0.00,0.00,0.00,0.00,0.00,'','','','','','','','本社','ais000000','2019-11-20 07:49:30','ais000000','2019-11-20 07:49:30'),
(8,'AIS-KBD-001','07','20191120',0,'Market',1000,'01','ELECOM','ELECOM','442-32223','','','','',0.00,0.00,0.00,0.00,0.00,'','','','','','','','本社','ais000000','2019-11-20 07:50:15','ais000000','2019-11-20 07:50:15'),
(9,'AIS-DT-002','02','20191120',0,'Market',200000,'01','DELL','DELL PRO','22452-342222','','Window 10','1431121-23222','intel dual processor',4.20,32.00,0.00,4096.00,0.00,'','','','','','ais','ais#123456','本社','ais000000','2019-11-20 07:51:21','ais000000','2019-11-21 02:34:15'),
(10,'AIS-OS-001','11','20191120',0,'Market',30000,'01','Microsoft','window 10','mf-329399','','','','',0.00,0.00,0.00,0.00,0.00,'','','','','','','','本社','ais000000','2019-11-20 07:52:12','ais000000','2019-11-20 07:52:12'),
(11,'AIS-OA-001','12','20191120',0,'Market',10000,'01','Microsoft','office 2010','22452-342222','','','','',0.00,0.00,0.00,0.00,0.00,'','','','','','','','本社','ais000000','2019-11-20 07:52:50','ais000000','2019-11-20 07:52:50'),
(12,'AIS-VR-001','14','20191120',0,'Market',10000,'01','AnLab','V3','ee22222','','','','',0.00,0.00,0.00,0.00,0.00,'','','','','','','','本社','ais000000','2019-11-20 07:53:36','ais000000','2019-11-20 07:53:36'),
(13,'AIS-MS-001','08','20191120',0,'Market',2000,'01','ELECOM','ELECOM','ee22222','','','','',0.00,0.00,0.00,0.00,0.00,'','','','','','','','本社','ais000000','2019-11-20 07:54:30','ais000000','2019-11-20 07:54:30'),
(14,'AIS-SVR-002','01','20191121',0,'',0,'02','','','','','','','',0.00,0.00,0.00,0.00,0.00,'','','','','','','','','ais000000','2019-11-20 23:35:21','ais000000','2019-11-21 04:30:51'),
(15,'AIS-SVR-003','01','20191121',0,'',0,'02','','','','','','','',0.00,0.00,0.00,0.00,0.00,'','','','','','','','','ais000000','2019-11-20 23:37:14','ais000000','2019-11-21 04:30:53');

/*Table structure for table `code_detail` */

DROP TABLE IF EXISTS `code_detail`;

CREATE TABLE `code_detail` (
  `code_master_id` varchar(3) NOT NULL COMMENT 'マスタコード',
  `code_detail_id` varchar(4) NOT NULL COMMENT 'サーブコード',
  `code_detail_name` varchar(100) NOT NULL COMMENT 'コード詳細名',
  `item1` varchar(200) DEFAULT NULL COMMENT 'アイテム1',
  `item2` varchar(200) DEFAULT NULL COMMENT 'アイテム2',
  `item3` varchar(200) DEFAULT NULL COMMENT 'アイテム3',
  `item4` varchar(200) DEFAULT NULL COMMENT 'アイテム4',
  `item5` varchar(200) DEFAULT NULL COMMENT 'アイテム5',
  `use_flag` varchar(1) DEFAULT '0' COMMENT '削除フラグ',
  PRIMARY KEY (`code_master_id`,`code_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='システムコード詳細';

/*Data for the table `code_detail` */

insert  into `code_detail`(`code_master_id`,`code_detail_id`,`code_detail_name`,`item1`,`item2`,`item3`,`item4`,`item5`,`use_flag`) values 
('001','01','保管',NULL,NULL,NULL,NULL,NULL,'0'),
('001','02','貸与',NULL,NULL,NULL,NULL,NULL,'0'),
('001','03','故障',NULL,NULL,NULL,NULL,NULL,'0'),
('001','04','廃棄',NULL,NULL,NULL,NULL,NULL,'0'),
('002','01','Server','01','SVR','3',NULL,NULL,'0'),
('002','02','Desktop','01','DT','2',NULL,NULL,'0'),
('002','03','Notebook','01','NB','1',NULL,NULL,'0'),
('002','04','Tablet','01','TBL','1',NULL,NULL,'0'),
('002','05','Mobile','01','MD','0',NULL,NULL,'0'),
('002','06','Monitor','01','DP','1',NULL,NULL,'0'),
('002','07','Keyboard','01','KBD','1',NULL,NULL,'0'),
('002','08','Mouse','01','MS','1',NULL,NULL,'0'),
('002','09','PC Lock Cable','01','LC','0',NULL,NULL,'0'),
('002','10','その他の装置','01','ETC','0',NULL,NULL,'0'),
('002','11','OS','02','OS','1',NULL,NULL,'0'),
('002','12','文書作成用 SW','02','OA','1',NULL,NULL,'0'),
('002','13','Utility','02','UTL','0',NULL,NULL,'0'),
('002','14','Virus 対策 SW','02','VR','1',NULL,NULL,'0'),
('003','01','HW',NULL,NULL,NULL,NULL,NULL,'0'),
('003','02','SW',NULL,NULL,NULL,NULL,NULL,'0');

/*Table structure for table `code_master` */

DROP TABLE IF EXISTS `code_master`;

CREATE TABLE `code_master` (
  `code_master_id` varchar(3) NOT NULL COMMENT 'コードマスタ連番',
  `code_master_name` varchar(100) NOT NULL COMMENT 'コードマスタ名',
  `remarks` varchar(300) DEFAULT NULL COMMENT '備考',
  `use_flag` varchar(100) NOT NULL DEFAULT '0' COMMENT '使用フラグ',
  PRIMARY KEY (`code_master_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='システムコードマスタ';

/*Data for the table `code_master` */

insert  into `code_master`(`code_master_id`,`code_master_name`,`remarks`,`use_flag`) values 
('001','status_code','状態コード','0'),
('002','kubun_code','区分コード','0'),
('003','kubun_kind','区分分類','0');

/*Table structure for table `emp` */

DROP TABLE IF EXISTS `emp`;

CREATE TABLE `emp` (
  `emp_id` varchar(20) NOT NULL COMMENT '社員ID',
  `emp_name` varchar(100) NOT NULL COMMENT '社員名称',
  `passwd` varchar(50) NOT NULL COMMENT 'パスワード',
  `organization_code` varchar(20) DEFAULT NULL COMMENT '組織コード',
  `use_flag` varchar(1) DEFAULT '0' COMMENT '削除フラグ',
  `create_user` varchar(20) NOT NULL COMMENT '登録者ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日',
  `update_user` varchar(20) NOT NULL COMMENT '更新者ID',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日',
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='社員マスタ';

/*Data for the table `emp` */

insert  into `emp`(`emp_id`,`emp_name`,`passwd`,`organization_code`,`use_flag`,`create_user`,`create_date`,`update_user`,`update_date`) values 
('ais000000','システム管理者','ais123456','0000000000','0','system','2019-11-20 05:59:55','system','2019-11-20 05:59:55'),
('ais000001','ユーザ','ais123456','0000000000','0','system','2019-11-20 06:01:14','system','2019-11-20 06:01:14');

/*Table structure for table `maintenance_history` */

DROP TABLE IF EXISTS `maintenance_history`;

CREATE TABLE `maintenance_history` (
  `history_seq` int(11) NOT NULL AUTO_INCREMENT,
  `asset_seq` int(11) NOT NULL,
  `implementation_date` varchar(10) DEFAULT NULL,
  `implementation_detail` varchar(200) DEFAULT NULL,
  `rep_id` varchar(20) DEFAULT NULL,
  `approver_id` varchar(20) DEFAULT NULL,
  `insert_id` varchar(20) NOT NULL,
  `insert_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_id` varchar(20) NOT NULL,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`history_seq`),
  KEY `maintenance_hisory_FK` (`asset_seq`),
  CONSTRAINT `maintenance_hisory_FK` FOREIGN KEY (`asset_seq`) REFERENCES `asset` (`asset_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `maintenance_history` */

insert  into `maintenance_history`(`history_seq`,`asset_seq`,`implementation_date`,`implementation_detail`,`rep_id`,`approver_id`,`insert_id`,`insert_date`,`update_id`,`update_date`) values 
(1,3,'20191111','Mainboard交替','木村','本田','ais000000','2019-11-20 08:03:11','ais000000','2019-11-20 08:03:11');

/*Table structure for table `rental` */

DROP TABLE IF EXISTS `rental`;

CREATE TABLE `rental` (
  `rental_seq` int(11) NOT NULL AUTO_INCREMENT,
  `asset_seq` int(11) NOT NULL,
  `rental_day` varchar(10) NOT NULL,
  `rental_user_id` varchar(20) NOT NULL,
  `purpose` varchar(50) NOT NULL,
  `speciality` varchar(50) NOT NULL,
  `applicant_id` varchar(20) DEFAULT NULL,
  `return_day` varchar(10) DEFAULT NULL,
  `return_user_id` varchar(20) DEFAULT NULL,
  `return_period` varchar(10) DEFAULT NULL,
  `status_code` varchar(3) DEFAULT NULL,
  `storage_location` varchar(30) DEFAULT NULL,
  `bp_name` varchar(100) DEFAULT NULL,
  `insert_id` varchar(20) NOT NULL,
  `insert_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_id` varchar(20) NOT NULL,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `rental_no` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`rental_seq`),
  KEY `rental_FK` (`asset_seq`),
  CONSTRAINT `rental_FK` FOREIGN KEY (`asset_seq`) REFERENCES `asset` (`asset_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

/*Data for the table `rental` */

insert  into `rental`(`rental_seq`,`asset_seq`,`rental_day`,`rental_user_id`,`purpose`,`speciality`,`applicant_id`,`return_day`,`return_user_id`,`return_period`,`status_code`,`storage_location`,`bp_name`,`insert_id`,`insert_date`,`update_id`,`update_date`,`rental_no`) values 
(2,9,'20191120','asdf','勤務','asdf','ais000000','20191121','ais000000','20191120','01','自社','asdf','ais000000','2019-11-20 07:51:58','ais000000','2019-11-21 02:40:33','20191120002'),
(3,9,'20191120','dfasdf','勤務','asdfsadf','ais000000','20191121','ais000000','20191120','01','自社','asdfasdfs','ais000000','2019-11-20 08:17:52','ais000000','2019-11-21 02:40:34','20191120003'),
(4,3,'20191120','asdf','勤務','asdf','ais000000','20191121','ais000000','20191120','01','自社','asdfsadf','ais000000','2019-11-20 08:18:06','ais000000','2019-11-21 02:40:36','20191120004'),
(5,9,'20191120','asdf','勤務','asddf','ais000000','20191121','ais000000','20191120','01','自社','asdfasdf','ais000000','2019-11-20 08:18:17','ais000000','2019-11-21 02:40:37','20191120005'),
(6,3,'20191120','asdf','勤務','asdf','ais000000','20191121','ais000000','20191120','01','自社','asdfasdf','ais000000','2019-11-20 08:39:42','ais000000','2019-11-21 02:40:38','20191120006'),
(8,9,'20191121','asdfsadf','勤務','asdfasf','ais000000','20191121','ais000000','20191121','01','自社','asdf','ais000000','2019-11-20 23:53:36','ais000000','2019-11-21 02:40:41','20191120008'),
(9,3,'20191121','asdf','勤務','asdf','ais000000','20191121','ais000000','20191121','01','自社','asdfasdf','ais000000','2019-11-20 23:53:55','ais000000','2019-11-21 02:40:42','20191120009'),
(10,15,'20191121','asdf','勤務','asdf','ais000000','20191121','ais000000','20191121','01','自社','asdfasfd','ais000000','2019-11-21 00:06:24','ais000000','2019-11-21 02:40:44','20191121001'),
(11,3,'20191121','asdf','勤務','asdf','ais000000',NULL,NULL,'20191121','02','自社','asdf','ais000000','2019-11-21 00:10:29','ais000000','2019-11-21 02:40:45','20191121002'),
(12,14,'20191121','asdf','勤務','asdf','ais000000',NULL,NULL,'20191107','02','自社',NULL,'ais000000','2019-11-21 01:33:06','ais000000','2019-11-21 02:40:46','20191121003'),
(13,15,'20191121','asdf','勤務','asdf','ais000000',NULL,NULL,'20191121','02','自社','asdfasdf','ais000000','2019-11-21 01:34:32','ais000000','2019-11-21 02:40:47','20191121004'),
(14,9,'20191121','','勤務','asdf','ais000000','20191121','ais000000','20191121','01','自社','asdfasdf','ais000000','2019-11-21 02:20:45','ais000000','2019-11-21 02:40:49','20191121005');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
