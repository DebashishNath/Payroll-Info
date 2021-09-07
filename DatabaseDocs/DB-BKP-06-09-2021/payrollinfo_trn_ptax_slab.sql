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
-- Table structure for table `trn_ptax_slab`
--

DROP TABLE IF EXISTS `trn_ptax_slab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trn_ptax_slab` (
  `ptax_slab_id` int NOT NULL AUTO_INCREMENT,
  `fin_year_id` int NOT NULL,
  `ptax_start_range` int NOT NULL,
  `ptax_end_range` int NOT NULL,
  `ptax_rate` int NOT NULL,
  `ptax_slab_desc` varchar(256) NOT NULL,
  PRIMARY KEY (`ptax_slab_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trn_ptax_slab`
--

LOCK TABLES `trn_ptax_slab` WRITE;
/*!40000 ALTER TABLE `trn_ptax_slab` DISABLE KEYS */;
INSERT INTO `trn_ptax_slab` VALUES (1,1,0,10000,0,'Not exceeding Rs.10,000/-'),(2,1,10000,15000,110,'Above Rs. 10,000/- but not exceeding Rs. 15,000/-'),(3,1,15000,25000,130,'Above Rs. 15,000/- but not exceeding Rs. 25,000/-'),(4,1,25000,40000,150,'Above Rs. 25,000/- but not exceeding Rs.40,000/-'),(5,1,40000,1000000,200,'Above Rs. 40,000/-');
/*!40000 ALTER TABLE `trn_ptax_slab` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-07 12:51:04
