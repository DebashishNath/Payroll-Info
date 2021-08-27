-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: payrollinfo
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `trn_print_pay_slip`
--

DROP TABLE IF EXISTS `trn_print_pay_slip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trn_print_pay_slip` (
  `user_id` int NOT NULL,
  `company_id` int NOT NULL,
  `company_name` varchar(25) DEFAULT NULL,
  `company_address1` varchar(45) DEFAULT NULL,
  `company_address2` varchar(45) DEFAULT NULL,
  `district_name` varchar(45) DEFAULT NULL,
  `company_pin` varchar(10) DEFAULT NULL,
  `company_contact_number` varchar(45) DEFAULT NULL,
  `company_email` varchar(45) DEFAULT NULL,
  `company_tan_no` varchar(45) DEFAULT NULL,
  `pay_year` int NOT NULL,
  `pay_month` int NOT NULL,
  `emp_id` int NOT NULL,
  `emp_code` varchar(10) DEFAULT NULL,
  `emp_name` varchar(45) DEFAULT NULL,
  `earn_ded_id` int NOT NULL,
  `earn_ded_code` varchar(5) DEFAULT NULL,
  `earn_ded_name` varchar(45) DEFAULT NULL,
  `earn_ded_type` varchar(1) DEFAULT NULL,
  `earn_ded_priority` int DEFAULT NULL,
  `earn_ded_amount` decimal(10,2) DEFAULT NULL,
  `salary_date` date DEFAULT NULL,
  `total_earn_amount` decimal(10,2) DEFAULT NULL,
  `total_ded_amount` decimal(10,2) DEFAULT NULL,
  `net_amount` decimal(10,2) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`company_id`,`emp_id`,`pay_year`,`pay_month`,`earn_ded_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trn_print_pay_slip`
--

LOCK TABLES `trn_print_pay_slip` WRITE;
/*!40000 ALTER TABLE `trn_print_pay_slip` DISABLE KEYS */;
INSERT INTO `trn_print_pay_slip` VALUES (171,1,'Descon Limited','X1/2/3, Block-EP, Sector-5, Bidhan Nagar','Salt Lake','Kolkata','700091','033 2357 4308','hrdes@gmail.com','TD001/95-97',2021,4,1,'E001','Alok Chatterjee',1,'BS','Basic Salary','E',1,20500.00,'2021-04-01',54920.00,6177.00,48743.00,''),(171,1,'Descon Limited','X1/2/3, Block-EP, Sector-5, Bidhan Nagar','Salt Lake','Kolkata','700091','033 2357 4308','hrdes@gmail.com','TD001/95-97',2021,4,1,'E001','Alok Chatterjee',3,'HRA','House Rent Allowance','E',3,10250.00,'2021-04-01',54920.00,6177.00,48743.00,''),(171,1,'Descon Limited','X1/2/3, Block-EP, Sector-5, Bidhan Nagar','Salt Lake','Kolkata','700091','033 2357 4308','hrdes@gmail.com','TD001/95-97',2021,4,1,'E001','Alok Chatterjee',4,'PF','Provident Fund','D',1,2460.00,'2021-04-01',54920.00,6177.00,48743.00,''),(171,1,'Descon Limited','X1/2/3, Block-EP, Sector-5, Bidhan Nagar','Salt Lake','Kolkata','700091','033 2357 4308','hrdes@gmail.com','TD001/95-97',2021,4,1,'E001','Alok Chatterjee',5,'ESI','ESI','D',2,100.00,'2021-04-01',54920.00,6177.00,48743.00,''),(171,1,'Descon Limited','X1/2/3, Block-EP, Sector-5, Bidhan Nagar','Salt Lake','Kolkata','700091','033 2357 4308','hrdes@gmail.com','TD001/95-97',2021,4,1,'E001','Alok Chatterjee',6,'LTA','Leave Travel Allowance','E',5,2050.00,'2021-04-01',54920.00,6177.00,48743.00,''),(171,1,'Descon Limited','X1/2/3, Block-EP, Sector-5, Bidhan Nagar','Salt Lake','Kolkata','700091','033 2357 4308','hrdes@gmail.com','TD001/95-97',2021,4,1,'E001','Alok Chatterjee',8,'TC','Transport Charge','D',3,1580.00,'2021-04-01',54920.00,6177.00,48743.00,''),(171,1,'Descon Limited','X1/2/3, Block-EP, Sector-5, Bidhan Nagar','Salt Lake','Kolkata','700091','033 2357 4308','hrdes@gmail.com','TD001/95-97',2021,4,1,'E001','Alok Chatterjee',9,'MA','Medical Allowance','E',6,1250.00,'2021-04-01',54920.00,6177.00,48743.00,''),(171,1,'Descon Limited','X1/2/3, Block-EP, Sector-5, Bidhan Nagar','Salt Lake','Kolkata','700091','033 2357 4308','hrdes@gmail.com','TD001/95-97',2021,4,1,'E001','Alok Chatterjee',11,'CA','Commut. Allowance','E',4,1600.00,'2021-04-01',54920.00,6177.00,48743.00,''),(171,1,'Descon Limited','X1/2/3, Block-EP, Sector-5, Bidhan Nagar','Salt Lake','Kolkata','700091','033 2357 4308','hrdes@gmail.com','TD001/95-97',2021,4,1,'E001','Alok Chatterjee',13,'ITAX','Income Tax','D',5,2037.00,'2021-04-01',54920.00,6177.00,48743.00,''),(171,1,'Descon Limited','X1/2/3, Block-EP, Sector-5, Bidhan Nagar','Salt Lake','Kolkata','700091','033 2357 4308','hrdes@gmail.com','TD001/95-97',2021,4,1,'E001','Alok Chatterjee',14,'PP','Performance Pay','E',7,2050.00,'2021-04-01',54920.00,6177.00,48743.00,''),(171,1,'Descon Limited','X1/2/3, Block-EP, Sector-5, Bidhan Nagar','Salt Lake','Kolkata','700091','033 2357 4308','hrdes@gmail.com','TD001/95-97',2021,4,1,'E001','Alok Chatterjee',15,'CY','CPB (CY)','E',8,4200.00,'2021-04-01',54920.00,6177.00,48743.00,''),(171,1,'Descon Limited','X1/2/3, Block-EP, Sector-5, Bidhan Nagar','Salt Lake','Kolkata','700091','033 2357 4308','hrdes@gmail.com','TD001/95-97',2021,4,1,'E001','Alok Chatterjee',16,'SP','Special Pay - II','E',9,3075.00,'2021-04-01',54920.00,6177.00,48743.00,''),(171,1,'Descon Limited','X1/2/3, Block-EP, Sector-5, Bidhan Nagar','Salt Lake','Kolkata','700091','033 2357 4308','hrdes@gmail.com','TD001/95-97',2021,4,1,'E001','Alok Chatterjee',17,'UP','Upkeep Pay','E',10,3795.00,'2021-04-01',54920.00,6177.00,48743.00,''),(171,1,'Descon Limited','X1/2/3, Block-EP, Sector-5, Bidhan Nagar','Salt Lake','Kolkata','700091','033 2357 4308','hrdes@gmail.com','TD001/95-97',2021,4,1,'E001','Alok Chatterjee',18,'SBE','Statutory Bonus/Ex-gratia','E',11,4100.00,'2021-04-01',54920.00,6177.00,48743.00,''),(171,1,'Descon Limited','X1/2/3, Block-EP, Sector-5, Bidhan Nagar','Salt Lake','Kolkata','700091','033 2357 4308','hrdes@gmail.com','TD001/95-97',2021,4,1,'E001','Alok Chatterjee',19,'SPP','Special Pay - III','E',12,2050.00,'2021-04-01',54920.00,6177.00,48743.00,'');
/*!40000 ALTER TABLE `trn_print_pay_slip` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-27 21:04:49
