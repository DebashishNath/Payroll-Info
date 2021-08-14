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
-- Table structure for table `trn_itax_slab`
--

DROP TABLE IF EXISTS `trn_itax_slab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trn_itax_slab` (
  `itax_slab_id` int NOT NULL AUTO_INCREMENT,
  `fin_year_id` int NOT NULL,
  `itax_start_range` decimal(10,0) NOT NULL,
  `itax_end_range` decimal(10,0) NOT NULL,
  `itax_rate_existing` decimal(10,0) NOT NULL,
  `itax_rate_new` decimal(10,0) NOT NULL,
  `itax_remarks` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`itax_slab_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trn_itax_slab`
--

LOCK TABLES `trn_itax_slab` WRITE;
/*!40000 ALTER TABLE `trn_itax_slab` DISABLE KEYS */;
INSERT INTO `trn_itax_slab` VALUES (1,1,0,249999,0,0,NULL),(2,1,250000,499999,5,5,NULL),(3,1,500000,750000,20,10,NULL),(4,1,750001,1000000,20,15,NULL),(5,1,1000001,1250000,30,20,NULL),(6,1,1250001,1500000,30,25,NULL),(7,1,1500001,175000000,30,30,NULL);
/*!40000 ALTER TABLE `trn_itax_slab` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-14 18:41:19
