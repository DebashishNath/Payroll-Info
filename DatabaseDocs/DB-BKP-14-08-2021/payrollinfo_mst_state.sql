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
-- Table structure for table `mst_state`
--

DROP TABLE IF EXISTS `mst_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mst_state` (
  `state_id` int NOT NULL AUTO_INCREMENT,
  `state_code` varchar(5) NOT NULL,
  `state_name` varchar(25) NOT NULL,
  `country_id` int NOT NULL,
  PRIMARY KEY (`state_id`),
  UNIQUE KEY `state_code_UNIQUE` (`state_code`),
  UNIQUE KEY `state_name_UNIQUE` (`state_name`),
  KEY `mst_state_country_id_idx` (`country_id`),
  CONSTRAINT `mst_state_country_id` FOREIGN KEY (`country_id`) REFERENCES `mst_country` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_state`
--

LOCK TABLES `mst_state` WRITE;
/*!40000 ALTER TABLE `mst_state` DISABLE KEYS */;
INSERT INTO `mst_state` VALUES (1,'S001','West Bengal',1),(3,'S002','Bihar',1),(4,'S003','Madhya Pradesh',1),(6,'S004','Tamil Nadu',1),(7,'S005','Rajasthan',1),(9,'S006','Orissa',1),(10,'S007','Gujrat',1),(11,'S008','Chattisgarh',1);
/*!40000 ALTER TABLE `mst_state` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-14 18:41:17
