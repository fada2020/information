/*
SQLyog Community v13.1.5  (32 bit)
MySQL - 5.7.28 : Database - amsdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`amsdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `amsdb`;

/*Table structure for table `accessories` */

DROP TABLE IF EXISTS `accessories`;

CREATE TABLE `accessories` (
  `accessories_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '付属品連番',
  `asset_seq` int(11) NOT NULL COMMENT '資産連番',
  `item_name` varchar(50) DEFAULT NULL COMMENT '付属品名',
  `item_spec` varchar(50) DEFAULT NULL COMMENT '付属仕様',
  `item_quantity` int(11) DEFAULT '0' COMMENT '付属数量',
  `remarks` varchar(200) DEFAULT NULL COMMENT '備考',
  `insert_id` varchar(30) DEFAULT NULL COMMENT '登録者ID',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日',
  `update_id` varchar(30) DEFAULT NULL COMMENT '更新者ID',
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日',
  PRIMARY KEY (`accessories_seq`,`asset_seq`),
  KEY `IDX_01` (`accessories_seq`,`asset_seq`,`item_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='付属品マスタ								';

/*Data for the table `accessories` */

/*Table structure for table `asset` */

DROP TABLE IF EXISTS `asset`;

CREATE TABLE `asset` (
  `asset_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '連番',
  `asset_number` varchar(15) NOT NULL COMMENT '資産管理番号',
  `kubun_num` int(11) DEFAULT NULL COMMENT '区分番号',
  `purchase_date` varchar(10) DEFAULT NULL COMMENT '購入日',
  `warranty_period` int(2) DEFAULT NULL COMMENT '保証期間',
  `buy_to` varchar(30) DEFAULT NULL COMMENT '購入先',
  `purchase_price` int(11) DEFAULT NULL COMMENT '購入金額',
  `status_code` int(11) DEFAULT NULL COMMENT '状態コード',
  `maker_name` varchar(50) DEFAULT NULL COMMENT 'メーカ名',
  `model_name` varchar(50) DEFAULT NULL COMMENT 'モデル名',
  `serial_num` varchar(30) DEFAULT NULL COMMENT 'シリアル番号',
  `interface_col` varchar(30) DEFAULT NULL COMMENT 'I/F',
  `os_name` varchar(30) DEFAULT NULL COMMENT 'OS名',
  `os_product_key` varchar(30) DEFAULT NULL COMMENT 'OS製品キー',
  `cpu_processor` varchar(30) DEFAULT NULL COMMENT 'CPU',
  `operating_frequency` decimal(5,2) DEFAULT NULL COMMENT '動作周波数',
  `memory` decimal(5,2) DEFAULT NULL COMMENT 'メモリ',
  `memory_upgarde` decimal(5,2) DEFAULT NULL COMMENT 'メモリ増設',
  `hdd` decimal(5,2) DEFAULT NULL COMMENT 'HDD',
  `hdd_upgarde` decimal(5,2) DEFAULT NULL COMMENT 'HDD増設',
  `mac_lan` varchar(30) DEFAULT NULL COMMENT 'MAC_LAN',
  `mac_wireless` varchar(30) DEFAULT NULL COMMENT 'MAC_WIRELESS',
  `other` varchar(200) DEFAULT NULL COMMENT '備考',
  `security_bios` varchar(30) DEFAULT NULL COMMENT 'SECURITY_BIOS',
  `security_hdd` varchar(30) DEFAULT NULL COMMENT 'SECURITY_HDD',
  `admin_account_id` varchar(30) DEFAULT NULL COMMENT '管理者ID',
  `admin_account_pwd` varchar(30) DEFAULT NULL COMMENT '管理者PWD',
  `storage_location` varchar(30) DEFAULT NULL COMMENT '保管位置',
  `insert_id` varchar(30) NOT NULL COMMENT '登録者ID',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日',
  `update_id` varchar(30) NOT NULL COMMENT '更新者ID',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日',
  PRIMARY KEY (`asset_seq`),
  KEY `IDX_01` (`asset_seq`,`asset_number`,`maker_name`,`model_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='資産マスタ';

/*Data for the table `asset` */

/*Table structure for table `code_detail` */

DROP TABLE IF EXISTS `code_detail`;

CREATE TABLE `code_detail` (
  `code_master_id` varchar(3) NOT NULL COMMENT 'マスタコード',
  `code_detail_id` varchar(4) NOT NULL COMMENT 'サーブコード',
  `code_detail_name` varchar(100) NOT NULL,
  `item1` varchar(200) DEFAULT NULL,
  `item2` varchar(200) DEFAULT NULL,
  `item3` varchar(200) DEFAULT NULL,
  `item4` varchar(200) DEFAULT NULL,
  `item5` varchar(200) DEFAULT NULL,
  `order_by` int(11) unsigned zerofill DEFAULT '00000000000',
  `delete_flag` varchar(1) DEFAULT '0',
  PRIMARY KEY (`code_master_id`,`code_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='システムコード詳細';

/*Data for the table `code_detail` */

/*Table structure for table `code_master` */

DROP TABLE IF EXISTS `code_master`;

CREATE TABLE `code_master` (
  `code_master_id` varchar(3) NOT NULL COMMENT 'コードマスタ連番',
  `code_maste_name` varchar(100) DEFAULT NULL COMMENT 'コードマスタ名',
  `remart` varchar(300) DEFAULT NULL COMMENT '備考',
  `use_flag` int(1) DEFAULT NULL COMMENT '使用フラグ',
  PRIMARY KEY (`code_master_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='システムコードマスタ';

/*Data for the table `code_master` */

insert  into `code_master`(`code_master_id`,`code_maste_name`,`remart`,`use_flag`) values
('001','状態コード',NULL,0),
('002','製品名前',NULL,0),
('003','製品区分',NULL,0);

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `company_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '連番',
  `company_code` varchar(20) NOT NULL COMMENT '会社コード',
  `company_name` varchar(20) NOT NULL COMMENT '会社名',
  `use_flag` int(11) NOT NULL DEFAULT '0' COMMENT '削除フラグ',
  `insert_id` varchar(50) NOT NULL COMMENT '登録者ID',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日',
  `update_id` varchar(50) NOT NULL COMMENT '更新者ID',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日',
  PRIMARY KEY (`company_seq`),
  KEY `IDX_KEY_01` (`company_seq`,`company_code`,`company_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会社情報マスタ';

/*Data for the table `company` */

/*Table structure for table `emp` */

DROP TABLE IF EXISTS `emp`;

CREATE TABLE `emp` (
  `emp_id` varchar(20) NOT NULL COMMENT '社員ID',
  `emp_name` varchar(20) DEFAULT NULL COMMENT '社員名称',
  `organization_code` varchar(20) DEFAULT '00000000000000000000',
  `use_flag` varchar(1) DEFAULT '0',
  `create_user` varchar(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_user` varchar(20) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='社員情報マスタ';

/*Data for the table `emp` */

/*Table structure for table `maintenance_history` */

DROP TABLE IF EXISTS `maintenance_history`;

CREATE TABLE `maintenance_history` (
  `history_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '履歴連番',
  `asset_seq` int(11) NOT NULL COMMENT '資産管理連番',
  `implementation_date` date DEFAULT NULL COMMENT '実施日',
  `implementation_detail` varchar(200) DEFAULT '' COMMENT '実施内容',
  `rep_id` varchar(30) DEFAULT '' COMMENT '担当者ID',
  `approver_id` varchar(30) DEFAULT '' COMMENT '承認者ID',
  `insert_id` varchar(30) NOT NULL DEFAULT '' COMMENT '登録者ID',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日',
  `update_id` varchar(30) NOT NULL COMMENT '更新者ID',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日',
  PRIMARY KEY (`history_seq`,`asset_seq`),
  KEY `IDX_01` (`implementation_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保守履歴								';

/*Data for the table `maintenance_history` */

/*Table structure for table `rental_history` */

DROP TABLE IF EXISTS `rental_history`;

CREATE TABLE `rental_history` (
  `rental_history_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '履歴連番',
  `rental_seq` int(11) NOT NULL COMMENT '貸与連番',
  `asset_seq` int(11) NOT NULL COMMENT '資産管理番号',
  `rental_day` varchar(10) NOT NULL DEFAULT '' COMMENT '貸与日',
  `rental_user_id` varchar(30) NOT NULL DEFAULT '' COMMENT '貸与者ID',
  `purpose` varchar(50) NOT NULL DEFAULT '' COMMENT '貸与用途',
  `speciality` varchar(50) NOT NULL DEFAULT '' COMMENT '特記事項',
  `applicant_id` varchar(30) DEFAULT '' COMMENT '申請者ID',
  `return_day` varchar(10) DEFAULT '' COMMENT '返却日',
  `return_user_id` varchar(30) DEFAULT '' COMMENT '返却者ID',
  `return_period` varchar(10) DEFAULT '' COMMENT '返却期間',
  `status_code` int(11) DEFAULT NULL COMMENT '状態コード',
  `storage_location` varchar(30) DEFAULT NULL COMMENT '保管位置',
  `bp_name` varchar(30) DEFAULT NULL COMMENT 'BP社名',
  `insert_id` varchar(30) NOT NULL COMMENT '登録者ID',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日',
  `update_id` varchar(30) NOT NULL COMMENT '更新者ID',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日',
  PRIMARY KEY (`rental_history_seq`),
  KEY `IDX_01` (`rental_history_seq`,`rental_seq`,`asset_seq`,`rental_day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='貸与履歴								';

/*Data for the table `rental_history` */

/*Table structure for table `rental_master` */

DROP TABLE IF EXISTS `rental_master`;

CREATE TABLE `rental_master` (
  `rental_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '貸与番号',
  `asset_seq` int(11) NOT NULL COMMENT '資産管理番号',
  `purpose　` varchar(50) DEFAULT NULL COMMENT '用途',
  `speciality` varchar(50) DEFAULT NULL COMMENT '特記事項',
  `applicant_id` varchar(30) DEFAULT NULL COMMENT '申請者ID',
  `return_period` varchar(10) DEFAULT NULL COMMENT '返却期間',
  `bp_name` varchar(30) DEFAULT NULL COMMENT 'BP社名',
  `insert_id` varchar(30) NOT NULL COMMENT '登録者ID',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日',
  `update_id` varchar(30) NOT NULL COMMENT '更新者ID',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日',
  PRIMARY KEY (`rental_seq`,`asset_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='貸与									';

/*Data for the table `rental_master` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
