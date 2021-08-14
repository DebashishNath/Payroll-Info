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
-- Table structure for table `trn_emp_salary_structure`
--

DROP TABLE IF EXISTS `trn_emp_salary_structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trn_emp_salary_structure` (
  `emp_id` int NOT NULL,
  `earn_ded_id` int NOT NULL,
  `earn_ded_amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`emp_id`,`earn_ded_id`),
  KEY `FK_emp_salary_structure_earn_ded_id_idx` (`earn_ded_id`),
  CONSTRAINT `FK_emp_salary_structure_earn_ded_id` FOREIGN KEY (`earn_ded_id`) REFERENCES `mst_earn_ded_components` (`earn_ded_id`),
  CONSTRAINT `FK_emp_salary_structure_emp_id` FOREIGN KEY (`emp_id`) REFERENCES `mst_employee` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trn_emp_salary_structure`
--

LOCK TABLES `trn_emp_salary_structure` WRITE;
/*!40000 ALTER TABLE `trn_emp_salary_structure` DISABLE KEYS */;
INSERT INTO `trn_emp_salary_structure` VALUES (1,1,20500.00),(1,3,10250.00),(1,4,2460.00),(1,5,10.00),(1,6,2050.00),(1,8,1580.00),(1,9,1250.00),(1,11,1600.00),(1,13,2041.50),(1,14,2050.00),(1,15,4200.00),(1,16,3075.00),(1,17,3795.00),(1,18,4100.00),(1,19,2050.00),(2,1,30000.00),(2,2,24000.00),(2,3,9000.00),(2,4,3600.00),(2,10,150.00),(2,13,12600.00),(3,1,35000.00),(3,3,10000.00),(3,4,3600.00),(3,9,500.00),(3,10,150.00),(3,13,9100.00),(4,1,40000.00),(4,2,32000.00),(4,3,6000.00),(4,4,4800.00),(4,5,2000.00),(4,10,200.00),(4,11,2000.00),(4,13,16600.00),(4,16,3000.00),(6,1,25000.00),(6,2,6000.00),(6,3,4000.00),(6,4,1000.00),(6,5,600.00),(6,10,130.00),(6,13,1900.00),(6,14,1000.00),(6,16,2000.00),(8,1,30000.00),(8,2,5000.00),(8,3,3000.00),(8,4,3600.00),(8,10,130.00),(8,13,8800.00),(8,15,1000.00),(8,17,2000.00),(8,18,3000.00),(9,1,30000.00),(9,2,6000.00),(9,3,3600.00),(9,4,4000.00),(9,5,200.00),(9,6,1500.00),(9,10,130.00),(9,13,2055.00),(11,1,50000.00),(11,2,15000.00),(11,3,10000.00),(11,4,6000.00),(11,5,2600.00),(11,6,10000.00),(11,10,200.00),(11,13,28650.00),(11,14,3000.00),(11,15,2500.00),(11,16,5000.00),(12,1,45000.00),(12,2,17500.00),(12,3,3000.00),(12,4,6000.00),(12,10,200.00),(12,11,3000.00),(12,13,14560.00),(12,14,2800.00),(12,15,1500.00);
/*!40000 ALTER TABLE `trn_emp_salary_structure` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-15  0:46:37
