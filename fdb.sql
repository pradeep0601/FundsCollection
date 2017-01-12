-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.12 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for fdb
CREATE DATABASE IF NOT EXISTS `fdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `fdb`;

-- Dumping structure for table fdb.albums
CREATE TABLE IF NOT EXISTS `albums` (
  `ALBUM_ID` bigint(20) NOT NULL,
  `ALBUM_RESOURCES_PATH` varchar(255) DEFAULT NULL,
  `ALBUM_THUMBNAIL` varchar(255) DEFAULT NULL,
  `ALBUM_DESCRIPTION` varchar(255) DEFAULT NULL,
  `ALBUM_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ALBUM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table fdb.albums: ~0 rows (approximately)
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;

-- Dumping structure for table fdb.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `FIRSTNAME` varchar(20) NOT NULL,
  `LASTNAME` varchar(30) DEFAULT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `CONTACT` bigint(20) DEFAULT NULL,
  `DOB` varchar(30) DEFAULT NULL,
  `GENDER` char(6) DEFAULT NULL,
  `USERNAME` bigint(20) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table fdb.employee: ~3 rows (approximately)
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`FIRSTNAME`, `LASTNAME`, `EMAIL`, `CONTACT`, `DOB`, `GENDER`, `USERNAME`, `PASSWORD`) VALUES
	('Mamta', 'Rani', 'mam@gmail.com', 9910654911, '03/04/2016', 'F', 38858, 'mamta123'),
	('Saurav', 'Srivastava', 'saurav@gmail.com', 9910654922, '06/01/2016', 'M', 38859, 'saurav123'),
	('Pradeep', 'Kumar', 'pradeepk.pdp@gmail.com', 9910654933, '06/01/2016', 'M', 38860, 'pradeep123');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

-- Dumping structure for table fdb.funds_record
CREATE TABLE IF NOT EXISTS `funds_record` (
  `record_id` int(11) NOT NULL,
  `actual_paid_amount` double DEFAULT NULL,
  `amount_remained` double DEFAULT NULL,
  `prev_balance` double DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `EMP_ID` bigint(20) DEFAULT NULL,
  `fund_id` int(11) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `FKjq6qxoks442pbrcasbr26bh1f` (`EMP_ID`),
  KEY `FKjntxsp5mvjking5ftm7f571or` (`fund_id`),
  CONSTRAINT `FKjntxsp5mvjking5ftm7f571or` FOREIGN KEY (`fund_id`) REFERENCES `fund_cycle` (`fund_id`),
  CONSTRAINT `FKjq6qxoks442pbrcasbr26bh1f` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table fdb.funds_record: ~5 rows (approximately)
/*!40000 ALTER TABLE `funds_record` DISABLE KEYS */;
INSERT INTO `funds_record` (`record_id`, `actual_paid_amount`, `amount_remained`, `prev_balance`, `total_amount`, `EMP_ID`, `fund_id`, `payment_date`) VALUES
	(18, 23, 0.6499999999999986, NULL, 23.65, 38860, 4, '2016-12-29'),
	(19, 123.65, -100, NULL, 23.65, 38859, 4, '2016-12-29'),
	(22, 20, 20.65, NULL, 40.65, 38860, 20, '2016-12-29'),
	(23, 50, -10, NULL, 40, 38858, 20, '2016-12-29'),
	(24, 0, -60, NULL, -60, 38859, 20, '2016-12-29');
/*!40000 ALTER TABLE `funds_record` ENABLE KEYS */;

-- Dumping structure for table fdb.fund_cycle
CREATE TABLE IF NOT EXISTS `fund_cycle` (
  `fund_id` int(11) NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `is_in_progress` bit(1) DEFAULT NULL,
  `share_per_person` double DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  PRIMARY KEY (`fund_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table fdb.fund_cycle: ~5 rows (approximately)
/*!40000 ALTER TABLE `fund_cycle` DISABLE KEYS */;
INSERT INTO `fund_cycle` (`fund_id`, `end_date`, `is_in_progress`, `share_per_person`, `start_date`) VALUES
	(2, '2016-12-09 00:00:00', b'0', 63.25, '2016-12-01 00:00:00'),
	(3, NULL, b'0', 300, '2016-12-22 00:00:00'),
	(4, '2016-12-14 00:00:00', b'0', 23.65, '2016-12-05 00:00:00'),
	(20, NULL, b'1', 40, '2016-12-28 00:00:00'),
	(25, NULL, b'1', 200, '2016-12-26 00:00:00');
/*!40000 ALTER TABLE `fund_cycle` ENABLE KEYS */;

-- Dumping structure for table fdb.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table fdb.hibernate_sequence: ~3 rows (approximately)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(26),
	(26),
	(26);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
