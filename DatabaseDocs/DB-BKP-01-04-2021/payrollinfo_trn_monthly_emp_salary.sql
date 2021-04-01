-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: payrollinfo
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
-- Table structure for table `trn_monthly_emp_salary`
--

DROP TABLE IF EXISTS `trn_monthly_emp_salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trn_monthly_emp_salary` (
  `month` int NOT NULL,
  `year` int NOT NULL,
  `emp_id` int NOT NULL,
  `earn_ded_id` int NOT NULL,
  `salary_date` date NOT NULL,
  `earn_ded_amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`month`,`year`,`emp_id`,`earn_ded_id`),
  KEY `FK_monthly_emp_salary_emp_id_idx` (`emp_id`),
  KEY `FK_monthly_emp_salary_earn_ded_id_idx` (`earn_ded_id`),
  CONSTRAINT `FK_monthly_emp_salary_earn_ded_id` FOREIGN KEY (`earn_ded_id`) REFERENCES `trn_emp_salary_structure` (`earn_ded_id`),
  CONSTRAINT `FK_monthly_emp_salary_emp_id` FOREIGN KEY (`emp_id`) REFERENCES `trn_emp_salary_structure` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trn_monthly_emp_salary`
--

LOCK TABLES `trn_monthly_emp_salary` WRITE;
/*!40000 ALTER TABLE `trn_monthly_emp_salary` DISABLE KEYS */;
INSERT INTO `trn_monthly_emp_salary` VALUES (1,2021,1,1,'0000-00-00',10000.00),(1,2021,1,2,'0000-00-00',6000.00),(1,2021,1,3,'0000-00-00',3000.00),(1,2021,1,4,'0000-00-00',1200.00),(1,2021,1,5,'0000-00-00',1000.00),(1,2021,2,1,'2020-12-31',11000.00),(1,2021,3,1,'2020-12-31',11000.00),(2,2021,2,1,'0000-00-00',6000.00),(2,2021,2,2,'0000-00-00',5000.00),(2,2021,2,3,'0000-00-00',4000.00),(2,2021,2,4,'0000-00-00',1200.00),(2,2021,2,5,'0000-00-00',1300.00);
/*!40000 ALTER TABLE `trn_monthly_emp_salary` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-01  5:22:34
