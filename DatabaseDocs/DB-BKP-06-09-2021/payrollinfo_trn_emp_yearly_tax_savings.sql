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
-- Table structure for table `trn_emp_yearly_tax_savings`
--

DROP TABLE IF EXISTS `trn_emp_yearly_tax_savings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trn_emp_yearly_tax_savings` (
  `emp_id` int NOT NULL,
  `fin_year_id` int NOT NULL,
  `tax_savings_head_id` int NOT NULL,
  `investment_amount` double DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`emp_id`,`fin_year_id`,`tax_savings_head_id`),
  KEY `FK_emp_yearly_tax_savings_fin_year_id_idx` (`fin_year_id`),
  KEY `FK_emp_yearly_tax_savings_tax_savings_head_id_idx` (`tax_savings_head_id`),
  CONSTRAINT `FK_emp_yearly_tax_savings_emp_id` FOREIGN KEY (`emp_id`) REFERENCES `mst_employee` (`emp_id`),
  CONSTRAINT `FK_emp_yearly_tax_savings_fin_year_id` FOREIGN KEY (`fin_year_id`) REFERENCES `mst_fin_year` (`fin_year_id`),
  CONSTRAINT `FK_emp_yearly_tax_savings_tax_savings_head_id` FOREIGN KEY (`tax_savings_head_id`) REFERENCES `mst_tax_savings_head` (`tax_savings_head_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trn_emp_yearly_tax_savings`
--

LOCK TABLES `trn_emp_yearly_tax_savings` WRITE;
/*!40000 ALTER TABLE `trn_emp_yearly_tax_savings` DISABLE KEYS */;
INSERT INTO `trn_emp_yearly_tax_savings` VALUES (1,1,1,150000,NULL),(19,1,1,150000,NULL);
/*!40000 ALTER TABLE `trn_emp_yearly_tax_savings` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-07 12:51:06
