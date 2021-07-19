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
-- Table structure for table `mst_holiday`
--

DROP TABLE IF EXISTS `mst_holiday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mst_holiday` (
  `holiday_id` int NOT NULL AUTO_INCREMENT,
  `holiday_code` varchar(5) NOT NULL,
  `holiday_name` varchar(20) NOT NULL,
  `holiday_date` date NOT NULL,
  PRIMARY KEY (`holiday_id`),
  UNIQUE KEY `Holiday_Code_UNIQUE` (`holiday_code`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_holiday`
--

LOCK TABLES `mst_holiday` WRITE;
/*!40000 ALTER TABLE `mst_holiday` DISABLE KEYS */;
INSERT INTO `mst_holiday` VALUES (1,'H1','Saturday','2021-03-06'),(2,'H2','Sunday','2021-03-07'),(3,'H3','Saturday','2021-03-13'),(4,'H4','Sunday','2021-03-14'),(5,'H5','Saturday','2021-03-20'),(6,'H6','Sunday','2021-03-21'),(7,'H7','Bihar Diwas','2021-03-22'),(8,'H8','Saturday','2021-03-27'),(9,'H9','Sunday','2021-03-28'),(10,'A1','Saturday','2021-04-03'),(11,'A2','Sunday','2021-04-04'),(12,'A3','Saturday','2021-04-10'),(13,'A4','Sunday','2021-04-11'),(14,'A5','Saturday','2021-04-17'),(15,'A6','Sunday','2021-04-18');
/*!40000 ALTER TABLE `mst_holiday` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-19 16:11:23
