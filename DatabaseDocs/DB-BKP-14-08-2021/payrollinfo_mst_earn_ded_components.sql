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
  `is_taxable` varchar(1) DEFAULT NULL,
  `earn_ded_tag_id` int DEFAULT NULL,
  PRIMARY KEY (`earn_ded_id`),
  UNIQUE KEY `earn_ded_code_UNIQUE` (`earn_ded_code`),
  UNIQUE KEY `earn_ded_name_UNIQUE` (`earn_ded_name`),
  KEY `FK_earn_ded_tag_idx` (`earn_ded_tag_id`),
  CONSTRAINT `FK_earn_ded_tag` FOREIGN KEY (`earn_ded_tag_id`) REFERENCES `mst_earn_ded_tag` (`earn_ded_tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_earn_ded_components`
--

LOCK TABLES `mst_earn_ded_components` WRITE;
/*!40000 ALTER TABLE `mst_earn_ded_components` DISABLE KEYS */;
INSERT INTO `mst_earn_ded_components` VALUES (1,'BS','Basic Salary','E',1,'Y',1),(2,'DA','Dearness Allowance','E',2,'Y',NULL),(3,'HRA','House Rent Allowance','E',3,'Y',6),(4,'PF','Provident Fund','D',1,'Y',4),(5,'ESI','ESI','D',2,'Y',2),(6,'LTA','Leave Travel Allowance','E',5,'Y',NULL),(8,'TC','Transport Charge','D',3,'Y',NULL),(9,'MA','Medical Allowance','E',6,'Y',NULL),(10,'PTAX','Professional Tax','D',4,'Y',3),(11,'CA','Commut. Allowance','E',4,'Y',NULL),(13,'ITAX','Income Tax','D',5,'Y',5),(14,'PP','Performance Pay','E',7,'Y',NULL),(15,'CY','CPB (CY)','E',8,'Y',NULL),(16,'SP','Special Pay - II','E',9,'Y',NULL),(17,'UP','Upkeep Pay','E',10,'Y',NULL),(18,'SBE','Statutory Bonus/Ex-gratia','E',11,'Y',NULL),(19,'SPP','Special Pay - III','E',12,'Y',NULL);
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

-- Dump completed on 2021-08-14 18:41:20
