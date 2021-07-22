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
-- Table structure for table `mst_earn_ded_components`
--

DROP TABLE IF EXISTS `mst_earn_ded_components`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mst_earn_ded_components` (
  `earn_ded_id` int NOT NULL AUTO_INCREMENT,
  `earn_ded_code` varchar(5) NOT NULL,
  `earn_ded_name` varchar(25) NOT NULL,
  `earn_ded_type` varchar(1) NOT NULL,
  `earn_ded_priority` int NOT NULL,
  `earn_ded_tagname` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`earn_ded_id`),
  UNIQUE KEY `earn_ded_code_UNIQUE` (`earn_ded_code`),
  UNIQUE KEY `earn_ded_name_UNIQUE` (`earn_ded_name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_earn_ded_components`
--

LOCK TABLES `mst_earn_ded_components` WRITE;
/*!40000 ALTER TABLE `mst_earn_ded_components` DISABLE KEYS */;
INSERT INTO `mst_earn_ded_components` VALUES (1,'BS','Basic Salary','E',1,'BasicSalary'),(2,'DA','Dearness Allowance','E',2,NULL),(3,'HRA','House Rent Allowance','E',3,NULL),(4,'PF','Provident Fund','D',1,NULL),(5,'ESI','ESI','D',2,'ESI'),(6,'MA','Mobile Allowance','E',5,NULL),(8,'TC','Transport Charge','D',3,NULL),(9,'FA','Food Allowance','E',6,NULL),(10,'PTAX','Professional Tax','D',4,'PTAX'),(11,'SA','Spl Allowance','E',4,NULL);
/*!40000 ALTER TABLE `mst_earn_ded_components` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-23  1:51:24
