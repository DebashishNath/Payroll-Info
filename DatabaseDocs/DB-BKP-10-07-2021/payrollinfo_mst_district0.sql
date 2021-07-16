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
-- Table structure for table `mst_district`
--

DROP TABLE IF EXISTS `mst_district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mst_district` (
  `district_id` int NOT NULL AUTO_INCREMENT,
  `district_code` varchar(5) NOT NULL,
  `district_name` varchar(25) NOT NULL,
  `state_id` int NOT NULL,
  PRIMARY KEY (`district_id`,`district_code`),
  UNIQUE KEY `district_code_UNIQUE` (`district_code`),
  UNIQUE KEY `district_name_UNIQUE` (`district_name`),
  KEY `mst_district_state_id_idx` (`state_id`),
  CONSTRAINT `mst_district_state_id` FOREIGN KEY (`state_id`) REFERENCES `mst_state` (`state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_district`
--

LOCK TABLES `mst_district` WRITE;
/*!40000 ALTER TABLE `mst_district` DISABLE KEYS */;
INSERT INTO `mst_district` VALUES (1,'DC001','Kolkata',1),(3,'DC002','Howrah',1),(6,'DC003','Purba Medinipur',1),(8,'DC004','Paschim Medinipur',1),(11,'DC005','Bhagalpur',3),(12,'DC006','Banka',3),(13,'DC007','Aurangabad',3),(14,'DC008','Ajmer',7),(15,'DC009','Alwar',7);
/*!40000 ALTER TABLE `mst_district` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-16 12:02:28
