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
-- Table structure for table `trn_pay_sheet_field_codes`
--

DROP TABLE IF EXISTS `trn_pay_sheet_field_codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trn_pay_sheet_field_codes` (
  `ps_month` int DEFAULT NULL,
  `ps_year` int DEFAULT NULL,
  `no_earn_field_types` int DEFAULT NULL,
  `no_ded_field_types` int DEFAULT NULL,
  `earn_field_code1` varchar(5) DEFAULT NULL,
  `earn_field_code2` varchar(5) DEFAULT NULL,
  `earn_field_code3` varchar(5) DEFAULT NULL,
  `earn_field_code4` varchar(5) DEFAULT NULL,
  `earn_field_code5` varchar(5) DEFAULT NULL,
  `earn_field_code6` varchar(5) DEFAULT NULL,
  `earn_field_code7` varchar(5) DEFAULT NULL,
  `earn_field_code8` varchar(5) DEFAULT NULL,
  `earn_field_code9` varchar(5) DEFAULT NULL,
  `earn_field_code10` varchar(5) DEFAULT NULL,
  `earn_field_code11` varchar(5) DEFAULT NULL,
  `earn_field_code12` varchar(5) DEFAULT NULL,
  `earn_field_code13` varchar(5) DEFAULT NULL,
  `earn_field_code14` varchar(5) DEFAULT NULL,
  `earn_field_code15` varchar(5) DEFAULT NULL,
  `earn_field_code16` varchar(5) DEFAULT NULL,
  `earn_field_code17` varchar(5) DEFAULT NULL,
  `earn_field_code18` varchar(5) DEFAULT NULL,
  `earn_field_code19` varchar(5) DEFAULT NULL,
  `earn_field_code20` varchar(5) DEFAULT NULL,
  `ded_field_code1` varchar(5) DEFAULT NULL,
  `ded_field_code2` varchar(5) DEFAULT NULL,
  `ded_field_code3` varchar(5) DEFAULT NULL,
  `ded_field_code4` varchar(5) DEFAULT NULL,
  `ded_field_code5` varchar(5) DEFAULT NULL,
  `ded_field_code6` varchar(5) DEFAULT NULL,
  `ded_field_code7` varchar(5) DEFAULT NULL,
  `ded_field_code8` varchar(5) DEFAULT NULL,
  `ded_field_code9` varchar(5) DEFAULT NULL,
  `ded_field_code10` varchar(5) DEFAULT NULL,
  `ded_field_code11` varchar(5) DEFAULT NULL,
  `ded_field_code12` varchar(5) DEFAULT NULL,
  `ded_field_code13` varchar(5) DEFAULT NULL,
  `ded_field_code14` varchar(5) DEFAULT NULL,
  `ded_field_code15` varchar(5) DEFAULT NULL,
  `ded_field_code16` varchar(5) DEFAULT NULL,
  `ded_field_code17` varchar(5) DEFAULT NULL,
  `ded_field_code18` varchar(5) DEFAULT NULL,
  `ded_field_code19` varchar(5) DEFAULT NULL,
  `ded_field_code20` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trn_pay_sheet_field_codes`
--

LOCK TABLES `trn_pay_sheet_field_codes` WRITE;
/*!40000 ALTER TABLE `trn_pay_sheet_field_codes` DISABLE KEYS */;
INSERT INTO `trn_pay_sheet_field_codes` VALUES (6,2021,5,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `trn_pay_sheet_field_codes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-16 12:02:26
