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
  `pay_sheet_values` varchar(4096) DEFAULT NULL,
  `pay_sheet_columns` varchar(4096) DEFAULT NULL,
  `emp_detail_values` varchar(2048) DEFAULT NULL,
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
INSERT INTO `trn_monthly_emp_salary_summary` VALUES (4,2021,1,1,'2021-04-01',54920.00,4050.00,50870.00,'[{\"amount\": 20500.00, \"earn_ded_id\": 1}, {\"amount\": 10250.00, \"earn_ded_id\": 3}, {\"amount\": 2460.00, \"earn_ded_id\": 4}, {\"amount\": 10.00, \"earn_ded_id\": 5}, {\"amount\": 2050.00, \"earn_ded_id\": 6}, {\"amount\": 1580.00, \"earn_ded_id\": 8}, {\"amount\": 1250.00, \"earn_ded_id\": 9}, {\"amount\": 1600.00, \"earn_ded_id\": 11}, {\"amount\": 2050.00, \"earn_ded_id\": 14}, {\"amount\": 4200.00, \"earn_ded_id\": 15}, {\"amount\": 3075.00, \"earn_ded_id\": 16}, {\"amount\": 3795.00, \"earn_ded_id\": 17}, {\"amount\": 4100.00, \"earn_ded_id\": 18}, {\"amount\": 2050.00, \"earn_ded_id\": 19}]','[{\"earn_ded_id\": 1, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"BS\", \"earn_ded_type\": \"E\", \"earn_ded_priority\": 1}, {\"earn_ded_id\": 2, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"DA\", \"earn_ded_type\": \"E\", \"earn_ded_priority\": 2}, {\"earn_ded_id\": 3, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"HRA\", \"earn_ded_type\": \"E\", \"earn_ded_priority\": 3}, {\"earn_ded_id\": 4, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"PF\", \"earn_ded_type\": \"D\", \"earn_ded_priority\": 1}, {\"earn_ded_id\": 5, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"ESI\", \"earn_ded_type\": \"D\", \"earn_ded_priority\": 2}, {\"earn_ded_id\": 6, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"LTA\", \"earn_ded_type\": \"E\", \"earn_ded_priority\": 5}, {\"earn_ded_id\": 8, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"TC\", \"earn_ded_type\": \"D\", \"earn_ded_priority\": 3}, {\"earn_ded_id\": 9, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"MA\", \"earn_ded_type\": \"E\", \"earn_ded_priority\": 6}, {\"earn_ded_id\": 10, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"PTAX\", \"earn_ded_type\": \"D\", \"earn_ded_priority\": 4}, {\"earn_ded_id\": 11, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"CA\", \"earn_ded_type\": \"E\", \"earn_ded_priority\": 4}, {\"earn_ded_id\": 13, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"ITAX\", \"earn_ded_type\": \"D\", \"earn_ded_priority\": 5}, {\"earn_ded_id\": 14, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"PP\", \"earn_ded_type\": \"E\", \"earn_ded_priority\": 7}, {\"earn_ded_id\": 15, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"CY\", \"earn_ded_type\": \"E\", \"earn_ded_priority\": 8}, {\"earn_ded_id\": 16, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"SP\", \"earn_ded_type\": \"E\", \"earn_ded_priority\": 9}, {\"earn_ded_id\": 17, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"UP\", \"earn_ded_type\": \"E\", \"earn_ded_priority\": 10}, {\"earn_ded_id\": 18, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"SBE\", \"earn_ded_type\": \"E\", \"earn_ded_priority\": 11}, {\"earn_ded_id\": 19, \"no_ded_cols\": 5, \"no_earn_cols\": 12, \"earn_ded_code\": \"SPP\", \"earn_ded_type\": \"E\", \"earn_ded_priority\": 12}]','[{\"emp_code\": \"E001\", \"emp_name\": \"Alok Chatterjee\"}]','');
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

-- Dump completed on 2021-08-14  1:24:19
