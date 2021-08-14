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
-- Table structure for table `trn_ptax_monthly`
--

DROP TABLE IF EXISTS `trn_ptax_monthly`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trn_ptax_monthly` (
  `month` int NOT NULL,
  `year` int NOT NULL,
  `ptax_slab_id` int NOT NULL,
  `no_employees` int NOT NULL,
  `ptax_amount` int NOT NULL,
  PRIMARY KEY (`month`,`ptax_slab_id`,`year`),
  KEY `FK_trn_ptax_monthly_ptax_slab_id_idx` (`ptax_slab_id`),
  CONSTRAINT `FK_trn_ptax_monthly_ptax_slab_id` FOREIGN KEY (`ptax_slab_id`) REFERENCES `trn_ptax_slab` (`ptax_slab_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trn_ptax_monthly`
--

LOCK TABLES `trn_ptax_monthly` WRITE;
/*!40000 ALTER TABLE `trn_ptax_monthly` DISABLE KEYS */;
INSERT INTO `trn_ptax_monthly` VALUES (4,2021,1,0,0),(4,2021,2,3,330),(4,2021,3,2,260),(4,2021,4,1,150),(4,2021,5,4,800),(6,2021,1,0,0),(6,2021,2,3,330),(6,2021,3,2,260),(6,2021,4,1,150),(6,2021,5,4,800),(7,2021,1,0,0),(7,2021,2,3,330),(7,2021,3,2,260),(7,2021,4,1,150),(7,2021,5,4,800);
/*!40000 ALTER TABLE `trn_ptax_monthly` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-14 18:41:18
