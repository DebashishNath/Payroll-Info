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
-- Table structure for table `trn_monthly_emp_salary_summary`
--

DROP TABLE IF EXISTS `trn_monthly_emp_salary_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trn_monthly_emp_salary_summary` (
  `month` int NOT NULL,
  `year` int NOT NULL,
  `emp_id` int NOT NULL,
  `company_id` int DEFAULT NULL,
  `salary_date` date DEFAULT NULL,
  `total_earn_amount` decimal(10,2) DEFAULT NULL,
  `total_ded_amount` decimal(10,2) DEFAULT NULL,
  `net_amount` decimal(10,2) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`month`,`year`,`emp_id`),
  KEY `trn_monthly_emp_salary_summary_company_id_idx` (`company_id`),
  CONSTRAINT `trn_monthly_emp_salary_summary_company_id` FOREIGN KEY (`company_id`) REFERENCES `mst_company` (`company_id`),
  CONSTRAINT `trn_monthly_emp_salary_summary_emp_id` FOREIGN KEY (`month`, `year`, `emp_id`) REFERENCES `trn_monthly_emp_salary_details` (`month`, `year`, `emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trn_monthly_emp_salary_summary`
--

LOCK TABLES `trn_monthly_emp_salary_summary` WRITE;
/*!40000 ALTER TABLE `trn_monthly_emp_salary_summary` DISABLE KEYS */;
INSERT INTO `trn_monthly_emp_salary_summary` VALUES (3,2021,1,1,'2021-03-01',18500.00,1810.00,16690.00,''),(3,2021,2,1,'2021-03-01',17000.00,2300.00,14700.00,''),(3,2021,3,1,'2021-03-01',25000.00,4000.00,21000.00,''),(3,2021,4,1,'2021-03-01',58600.00,1500.00,57100.00,''),(3,2021,8,1,'2021-03-01',22500.00,NULL,22500.00,''),(3,2021,9,1,'2021-03-01',47000.00,NULL,47000.00,''),(3,2021,15,1,'2021-03-01',9000.00,NULL,9000.00,''),(3,2021,18,1,'2021-03-01',23000.00,2800.00,20200.00,''),(3,2021,20,1,'2021-03-01',18500.00,1600.00,16900.00,''),(3,2021,24,1,'2021-03-01',32000.00,2100.00,29900.00,'');
/*!40000 ALTER TABLE `trn_monthly_emp_salary_summary` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-16 12:02:25
